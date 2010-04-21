package javax.microedition.midlet;

import net.intensicode.runme.MIDletFrame;
import net.intensicode.runme.util.Log;

import java.io.IOException;

public abstract class MIDlet
    {
    public MIDletFrame frame;

    public void notifyDestroyed()
        {
        if ( frame != null ) frame.setVisible( false );
        if ( frame != null ) frame.dispose();
        }

    public void notifyPaused()
        {
        }

    public final boolean platformRequest( final String aURL )
        {
        try
            {
            Runtime.getRuntime().exec( "firefox " + aURL );
            return true;
            }
        catch ( final IOException e )
            {
            Log.error( e );
            return false;
            }
        }

    // Protected Interface

    protected MIDlet()
        {
        }

    protected abstract void startApp() throws MIDletStateChangeException;

    protected abstract void pauseApp() throws MIDletStateChangeException;

    protected abstract void destroyApp( boolean unconditional ) throws MIDletStateChangeException;
    }
