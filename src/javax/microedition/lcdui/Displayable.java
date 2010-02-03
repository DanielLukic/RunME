package javax.microedition.lcdui;

import net.intensicode.runme.DisplayBuffer;

import java.awt.Dimension;

public abstract class Displayable
    {
    public static DisplayBuffer displayBuffer;

    public static Dimension displaySize;


    // RunME API

    public synchronized final void show()
        {
        if ( myVisibleFlag ) return;
        myVisibleFlag = true;
        showNotify();
        }

    public synchronized final void hide()
        {
        if ( !myVisibleFlag ) return;
        myVisibleFlag = false;
        hideNotify();
        }

    // J2ME API

    public final void setFullScreenMode( final boolean aFullScreen )
        {
        // We are always in full screen mode..
        }

    public int getWidth()
        {
        return displaySize.width;
        }

    public int getHeight()
        {
        return displaySize.height;
        }

    public boolean isShown()
        {
        return myVisibleFlag;
        }

    public void showNotify()
        {
        if ( !myVisibleFlag ) throw new IllegalStateException();
        }

    public void hideNotify()
        {
        if ( myVisibleFlag ) throw new IllegalStateException();
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

    // Protected Interface

    protected abstract void paint( final Graphics aGraphics );


    private boolean myVisibleFlag;
    }
