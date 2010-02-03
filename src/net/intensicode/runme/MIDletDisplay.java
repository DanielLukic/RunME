package net.intensicode.runme;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

public final class MIDletDisplay extends Display
    {
    public MIDletDisplay( final MIDlet aMidlet, final DisplayBuffer aDisplayBuffer )
        {
        myDisplayBuffer = aDisplayBuffer;
        register( aMidlet, this );
        }

    // From Display

    public final void setCurrent( final Displayable aDisplayable )
        {
        if ( aDisplayable != null ) aDisplayable.displayBuffer = myDisplayBuffer;
        super.setCurrent( aDisplayable );
        }

    private final DisplayBuffer myDisplayBuffer;
    }
