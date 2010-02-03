package net.intensicode.runme;

import net.intensicode.runme.util.Log;

import javax.microedition.midlet.MIDlet;
import java.lang.reflect.Method;

public final class MIDletContainer
    {
    public MIDletContainer( final MIDlet aMIDlet )
        {
        myMIDlet = aMIDlet;
        }

    public final void start()
        {
        callMidletMethod( myMIDlet, "startApp" );
        }

    public final void pause()
        {
        callMidletMethod( myMIDlet, "pauseApp" );
        }

    public final void destroy()
        {
        callMidletMethod( myMIDlet, "destroyApp" );
        }

    // Implementation

    private static void callMidletMethod( final Object aObject, final String aMethodName )
        {
        try
            {
            final Method method = aObject.getClass().getMethod( aMethodName );
            method.invoke( aObject );
            LOG.debug( "Called {} with {}", aObject, aMethodName );
            }
        catch ( final Throwable t )
            {
            throw new RuntimeException( "NYI", t );
            }
        }


    private final MIDlet myMIDlet;

    private static final Log LOG = Log.create();
    }
