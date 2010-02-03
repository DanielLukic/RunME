package javax.microedition.media;

import net.intensicode.runme.util.Log;

import javax.sound.sampled.*;
import java.io.*;

public final class ClipPlayer implements Player
    {
    public ClipPlayer( final InputStream aInputStream ) throws MediaException
        {
        if ( aInputStream == null ) throw new NullPointerException();

        for ( final AudioFileFormat.Type type : AudioSystem.getAudioFileTypes() )
            {
            LOG.debug( "Audio type: {}", type );
            }

        final Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        for ( final Mixer.Info info : mixerInfos )
            {
            LOG.debug( "Mixer info: {}", info );
            }

        final Mixer mixer = AudioSystem.getMixer( mixerInfos[ 0 ] );
        final Line.Info[] lineInfos = mixer.getSourceLineInfo();
        for ( final Line.Info info : lineInfos )
            {
            LOG.debug( "Line info: {}", info );
            }

        try
            {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream( aInputStream );
            final AudioInputStream audioStream = AudioSystem.getAudioInputStream( bufferedInputStream );

            final Clip clip = AudioSystem.getClip();
            clip.open( audioStream );

            myClip = clip;

            for ( final javax.sound.sampled.Control c : myClip.getControls() )
                {
                if ( c.getType().toString().equals( "Master Gain" ) )
                    {
                    myVolumeControl = new DirectVolumeControl( (FloatControl) c );
                    }
                }
            }
        catch ( final Throwable t )
            {
            //#if DEBUG
            Log.error( "failed creating audio clip", t );
            //#endif
            throw new MediaException( t.toString() );
            }
        }

    // From Controllable

    public final Control getControl( final String aString )
        {
        if ( aString.equals( "VolumeControl" ) ) return myVolumeControl;
        return null;
        }

    public final Control[] getControls()
        {
        return new Control[]{ myVolumeControl };
        }

    // From Player

    public int getState()
        {
        // Blah..
        if ( myClip.isRunning() ) return Player.STARTED;
        if ( myClip.isActive() ) return Player.REALIZED;
        if ( myClip.isOpen() ) return Player.PREFETCHED;
        return Player.CLOSED;
        }

    public final void setLoopCount( final int aLoopCount )
        {
        myLoopCount = aLoopCount;
        }

    public synchronized final long setMediaTime( final long aMediaTime )
        {
        throw new RuntimeException( "nyi" );
        }

    public final void realize()
        {
        throw new RuntimeException( "nyi" );
        }

    public final void prefetch()
        {
        throw new RuntimeException( "nyi" );
        }

    public synchronized final void start()
        {
        myClip.start();
        }

    public synchronized final void stop()
        {
        if ( myClip.isRunning() ) myClip.stop();
        }

    public synchronized final void close()
        {
        if ( myClip.isOpen() ) myClip.close();
        }

    public final void deallocate()
        {
        throw new RuntimeException( "nyi" );
        }

    public final void addPlayerListener( final PlayerListener aPlayerListener )
        {
        throw new RuntimeException( "nyi" );
        }

    public final void removePlayerListener( final PlayerListener aPlayerListener )
        {
        throw new RuntimeException( "nyi" );
        }


    private int myLoopCount = 1;

    private int myMediaPosition = 0;

    private DirectVolumeControl myVolumeControl;


    private final Clip myClip;

    private static final Log LOG = Log.create();
    }
