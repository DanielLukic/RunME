package net.intensicode.runme;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.DisplayContext;
import javax.microedition.lcdui.Displayable;


public final class MIDletDisplay extends Display
    {
    Displayable displayable;

    final int width;

    final int height;


    public MIDletDisplay( final DisplayContext aContext, final int aWidth, final int aHeight )
        {
        myContext = aContext;
        width = aWidth;
        height = aHeight;
        }

    // From Display

    public final void setCurrent( final Displayable aDisplayable )
        {
        if ( displayable != null ) displayable.detach();
        displayable = aDisplayable;
        if ( displayable != null ) displayable.attach( myContext );
        }


    private final DisplayContext myContext;
    }
