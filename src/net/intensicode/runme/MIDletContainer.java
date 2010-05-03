package net.intensicode.runme;

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
            }
        catch ( final Throwable t )
            {
            throw new RuntimeException( "NYI", t );
            }
        }


    private final MIDlet myMIDlet;
    }
