package javax.microedition.midlet;

import net.intensicode.runme.MIDletFrame;

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

    // Protected Interface

    protected MIDlet()
        {
        }

    protected abstract void startApp() throws MIDletStateChangeException;

    protected abstract void pauseApp() throws MIDletStateChangeException;

    protected abstract void destroyApp( boolean unconditional ) throws MIDletStateChangeException;
    }
