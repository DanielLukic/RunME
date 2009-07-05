package javax.microedition.lcdui;

/**
 * TODO: Describe this!
 */
public abstract class Displayable
    {
    public final void attach( final DisplayContext aListener )
        {
        myContext = aListener;
        }

    public final void detach()
        {
        myContext = null;
        }

    public final void setFullScreenMode( final boolean aFullScreen )
        {
        }

    public int getWidth()
        {
        if ( myContext == null ) return 240;
        return myContext.displayWidth();
        }

    public int getHeight()
        {
        if ( myContext == null ) return 320;
        return myContext.displayHeight();
        }

    public void showNotify()
        {
        }

    public void hideNotify()
        {
        }

    public void keyPressed( final int aKeyCode )
        {
        }

    public void keyReleased( final int aKeyCode )
        {
        }

    public String getKeyName( final int aKeyCode )
        {
        return Integer.toString( aKeyCode );
        }

    public void repaint()
        {
        if ( myContext == null ) return;

        final Graphics graphics = myContext.displayGraphics();
        paint( graphics );
        if ( myContext != null ) myContext.onRepaintDone();
        }

    // Protected Interface

    protected abstract void paint( final Graphics aGraphics );



    private DisplayContext myContext;
    }
