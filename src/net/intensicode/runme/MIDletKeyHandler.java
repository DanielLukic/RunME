package net.intensicode.runme;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import java.awt.event.*;

public final class MIDletKeyHandler implements KeyListener
    {
    public MIDletKeyHandler( final MIDlet aMIDlet )
        {
        myMIDlet = aMIDlet;
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

        final Displayable displayable = Display.getDisplayable( myMIDlet );
        if ( displayable != null ) displayable.keyPressed( getMeCode( keyCode ) );
        }

    public final void keyReleased( final KeyEvent aKeyEvent )
        {
        final int keyCode = aKeyEvent.getKeyCode();
        myLastPressedKeyCode = 0;

        final Displayable displayable = Display.getDisplayable( myMIDlet );
        if ( displayable != null ) displayable.keyReleased( getMeCode( keyCode ) );
        }

    // Implementation

    private static int getMeCode( final int aKeyCode )
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


    private int myLastPressedKeyCode;

    private final MIDlet myMIDlet;
    }
