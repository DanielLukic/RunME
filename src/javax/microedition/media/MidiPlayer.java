package javax.microedition.media;

import javax.sound.midi.*;
import java.io.*;


public final class MidiPlayer implements Player
    {
    public MidiPlayer( final InputStream aInputStream, final String aContentType ) throws MediaException
        {
        if ( aInputStream == null || aContentType == null ) throw new MediaException();
        myInputStream = aInputStream;
        }

    // From Controllable

    public final Control getControl( final String aString )
        {
        return null;
        }

    public final Control[] getControls()
        {
        return new Control[0];
        }

    // From Player

    public int getState()
        {
        return myState;
        }

    public final void setLoopCount( final int aLoopCount )
        {
        if ( mySequencer != null )
            {
            mySequencer.setLoopCount( aLoopCount );
            }
        myLoopCount = aLoopCount;
        }

    public final long setMediaTime( final long aMediaTime ) throws MediaException
        {
        return aMediaTime;
        }

    public void realize()
        {
        }

    public void prefetch() throws MediaException
        {
        if ( myState >= PREFETCHED ) return;

        realize();
        try
            {
            final byte[] data = ResourceLoader.loadStream( myInputStream );
            myState = PREFETCHED;

            final Synthesizer synthesizer = MidiSystem.getSynthesizer();
            final MidiChannel[] channels = synthesizer.getChannels();
            for ( int idx = 0; idx < channels.length; idx++ )
                {
                channels[ idx ].controlChange( 7, 0 );
                }

            mySequencer = MidiSystem.getSequencer( true );
            mySequencer.setSequence( MidiSystem.getSequence( new ByteArrayInputStream( data ) ) );
            mySequencer.open();
            mySequencer.setLoopCount( myLoopCount );
            }
        catch ( final Throwable t )
            {
            throw new MediaException( t.toString() );
            }
        }

    public void start()
        {
        if ( myState >= STARTED ) return;

        if ( mySequencer != null ) mySequencer.setTickPosition( 0 );
        if ( mySequencer != null ) mySequencer.start();
        myState = STARTED;
        }

    public void stop()
        {
        if ( myState != STARTED ) return;

        if ( mySequencer != null ) mySequencer.stop();
        myState = PREFETCHED;
        }

    public void close()
        {
        if ( myState == CLOSED ) return;

        if ( mySequencer != null ) mySequencer.close();
        mySequencer = null;
        myState = CLOSED;
        }

    public final void deallocate()
        {
        stop();
        close();
        }

    public final void addPlayerListener( final PlayerListener aPlayerListener )
        {
        }

    public final void removePlayerListener( final PlayerListener aPlayerListener )
        {
        }


    private Sequencer mySequencer;

    private int myState = CLOSED;

    private int myLoopCount = Sequencer.LOOP_CONTINUOUSLY;


    private final InputStream myInputStream;
    }
