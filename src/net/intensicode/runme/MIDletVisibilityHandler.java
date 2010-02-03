package net.intensicode.runme;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import java.awt.event.*;

public final class MIDletVisibilityHandler implements FocusListener
    {
    public MIDletVisibilityHandler( final MIDlet aMidlet )
        {
        if ( aMidlet == null ) throw new IllegalArgumentException();
        myMidlet = aMidlet;
        }

    // From FocusListener

    public final void focusGained( final FocusEvent e )
        {
        final Displayable displayable = Display.getDisplayable( myMidlet );
        if ( displayable != null ) displayable.show();
        }

    public final void focusLost( final FocusEvent e )
        {
        final Displayable displayable = Display.getDisplayable( myMidlet );
        if ( displayable != null ) displayable.hide();
        }

    private final MIDlet myMidlet;
    }
