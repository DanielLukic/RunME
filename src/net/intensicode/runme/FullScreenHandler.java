package net.intensicode.runme;

import java.awt.event.*;

public final class FullScreenHandler implements KeyListener
    {
    public FullScreenHandler( final FullScreenManager aFullScreenManager )
        {
        myFullScreenManager = aFullScreenManager;
        }

    // From KeyListener

    public final void keyPressed( final KeyEvent aKeyEvent )
        {
        if ( aKeyEvent.getKeyCode() != KeyEvent.VK_ENTER ) return;
        if ( aKeyEvent.getModifiersEx() != KeyEvent.ALT_DOWN_MASK ) return;
        myFullScreenManager.toggleFullScreenMode();
        }

    public final void keyReleased( final KeyEvent aKeyEvent )
        {
        }

    public final void keyTyped( final KeyEvent aKeyEvent )
        {
        }

    private final FullScreenManager myFullScreenManager;
    }
