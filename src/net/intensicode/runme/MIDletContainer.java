package net.intensicode.runme;

import net.intensicode.runme.util.Log;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;


public final class MIDletContainer implements KeyListener
    {
    public static final MIDlet createMIDlet( final Class aMIDletClass )
        {
        if ( aMIDletClass == null ) throw new NullPointerException();

        try
            {
            final Object instance = aMIDletClass.newInstance();
            return (MIDlet) instance;
            }
        catch ( final Throwable t )
            {
            throw new RuntimeException( "Failed creating MIDlet", t );
            }
        }

    public MIDletContainer( final MIDlet aMIDlet, final MIDletDisplay aDisplay )
        {
        myMIDlet = aMIDlet;
        myDisplay = aDisplay;
        Display.register( aMIDlet, myDisplay );
        }

    public final void start()
        {
        call( myMIDlet, "startApp" );
        }

    public final void pause()
        {
        call( myMIDlet, "pauseApp" );
        }

    public final void destroy()
        {
        call( myMIDlet, "destroyApp" );
        }

    // From KeyListener

    public final void keyTyped( final KeyEvent aKeyEvent )
        {
        }

    public final void keyPressed( final KeyEvent aKeyEvent )
        {
        final int keyCode = aKeyEvent.getKeyCode();
        if ( myLastPressedKeyCode == keyCode ) return;
        myLastPressedKeyCode = keyCode;

        final int meCode = getMeCode( keyCode );
        final Displayable displayable = myDisplay.displayable;
        if ( displayable != null ) displayable.keyPressed( meCode );
        }

    public final void keyReleased( final KeyEvent aKeyEvent )
        {
        final int keyCode = aKeyEvent.getKeyCode();
        myLastPressedKeyCode = 0;

        final int meCode = getMeCode( keyCode );
        final Displayable displayable = myDisplay.displayable;
        if ( displayable != null ) displayable.keyReleased( meCode );
        }

    // Implementation

    private static final int getMeCode( final int aKeyCode )
        {
        switch ( aKeyCode )
            {
            case KeyEvent.VK_F1:
                return -6;
            case KeyEvent.VK_F2:
                return -7;
            default:
                return aKeyCode;
            }
        }

    private static final void call( final Object aObject, final String aMethodName )
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

    public void setVisible( final boolean aVisibleFlag )
        {
        if ( myDisplay == null || myDisplay.displayable == null ) return;

        if ( aVisibleFlag ) myDisplay.displayable.showNotify();
        else myDisplay.displayable.hideNotify();
        }


    private MIDlet myMIDlet;

    private int myLastPressedKeyCode;

    private final MIDletDisplay myDisplay;

    private static final Log LOG = Log.create();
    }
