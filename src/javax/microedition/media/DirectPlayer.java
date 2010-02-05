package javax.microedition.media;

import net.intensicode.runme.util.Log;

import javax.sound.sampled.*;
import java.io.*;

public final class DirectPlayer implements Player, Runnable
    {
    public DirectPlayer( final InputStream aInputStream ) throws MediaException
        {
        if ( aInputStream == null ) throw new NullPointerException();

        try
            {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream( aInputStream );
            final AudioInputStream audioStream = AudioSystem.getAudioInputStream( bufferedInputStream );
            final AudioFormat format = audioStream.getFormat();
            LOG.debug( "Audio format: {}", format );

            mySoundData = ResourceLoader.loadStream( audioStream );

            myDataLine = AudioSystem.getSourceDataLine( format );
            myDataLine.open( format );

            final int sourceFrameSize = myDataLine.getFormat().getFrameSize();
            myFrameSize = ( sourceFrameSize == AudioSystem.NOT_SPECIFIED ) ? 4 : sourceFrameSize;

            for ( final javax.sound.sampled.Control c : myDataLine.getControls() )
                {
                if ( c.getType().toString().equals( "Master Gain" ) )
                    {
                    myVolumeControl = new DirectVolumeControl( (FloatControl) c );
                    }
                }

            myThread = new Thread( this );
            myThread.setDaemon( true );
            }
        catch ( final LineUnavailableException e )
            {
            throw new MediaException( "no sound device available" ); // AudioManager.ERROR_NO_SOUND_DEVICE_AVAILABLE
            }
        catch ( final Throwable t )
            {
            //#if DEBUG
            t.printStackTrace();
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
        if ( myDataLine.isRunning() ) return Player.STARTED;
        if ( myDataLine.isActive() ) return Player.REALIZED;
        if ( myDataLine.isOpen() ) return Player.PREFETCHED;
        return Player.CLOSED;
        }

    public final void setLoopCount( final int aLoopCount )
        {
        myLoopCount = aLoopCount;
        }

    public synchronized final long setMediaTime( final long aMediaTime )
        {
        if ( aMediaTime != 0 ) throw new IllegalArgumentException();

        final long normalizedTime = ( aMediaTime / myFrameSize ) * myFrameSize;
        final long mediaTime = Math.max( 0, Math.min( mySoundData.length, normalizedTime ) );
        return myMediaPosition = (int) mediaTime;
        }

    public final void realize()
        {
        }

    public final void prefetch()
        {
        }

    public synchronized final void start()
        {
        prefetch();

        if ( myMediaPosition == mySoundData.length ) myMediaPosition = 0;
        if ( !myThread.isAlive() ) myThread.start();

        if ( !myDataLine.isRunning() )
            {
            myDataLine.start();
            notify();
            }
        }

    public synchronized final void stop()
        {
        if ( myDataLine.isRunning() ) myDataLine.stop();
        }

    public synchronized final void close()
        {
        if ( myDataLine.isOpen() ) myDataLine.close();
        }

    public final void deallocate()
        {
        }

    public final void addPlayerListener( final PlayerListener aPlayerListener )
        {
        }

    public final void removePlayerListener( final PlayerListener aPlayerListener )
        {
        }

    // From Runnable

    public final void run()
        {
        while ( true )
            {
            synchronized ( this )
                {
                while ( myMediaPosition >= mySoundData.length )
                    {
                    try
                        {
                        this.wait();
                        }
                    catch ( final InterruptedException e )
                        {
                        break;
                        }
                    }
                }

            start();
            streamSoundData();
            stop();

            if ( myLoopCount > 0 ) myLoopCount--;
            if ( myLoopCount != 0 ) myMediaPosition = 0;
            }
        }

    // Implementation

    private void streamSoundData()
        {
        final int mediaLength = mySoundData.length;
        while ( myMediaPosition < mediaLength )
            {
            final int quarterSize = ( myDataLine.getBufferSize() / 2 / myFrameSize ) * myFrameSize;
            final int writeSize = Math.min( quarterSize, mediaLength - myMediaPosition );
            myMediaPosition += myDataLine.write( mySoundData, myMediaPosition, writeSize );
            Thread.yield();
            }
        myDataLine.drain();
        }


    private int myLoopCount = 1;

    private int myMediaPosition = 0;


    private final Thread myThread;

    private DirectVolumeControl myVolumeControl;


    private final int myFrameSize;

    private final byte[] mySoundData;

    private final SourceDataLine myDataLine;

    private static final Log LOG = Log.create();
    }
