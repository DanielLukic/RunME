package net.intensicode.runme.chooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class QuitAction extends AbstractAction
    {
    public QuitAction()
        {
        putValue( Action.NAME, "Quit" );
        putValue( Action.MNEMONIC_KEY, KeyEvent.VK_Q );
        putValue( Action.SHORT_DESCRIPTION, "Quit MIDlet chooser" );
        }

    // From Action

    public final void actionPerformed( final ActionEvent aEvent )
        {
        System.exit( 0 );
        }
    }
