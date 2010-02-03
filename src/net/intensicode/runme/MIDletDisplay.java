package net.intensicode.runme;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public final class MIDletDisplay extends Display
    {
    public MIDletDisplay( final MIDlet aMidlet )
        {
        register( aMidlet, this );
        }
    }
