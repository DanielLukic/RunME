package javax.microedition.lcdui;

import java.util.HashMap;

import javax.microedition.midlet.MIDlet;



/**
 * TODO: Describe this!
 */
public abstract class Display
{
    public static final void register( final MIDlet aMIDlet, final Display aDisplay )
    {
        myDisplays.put( aMIDlet, aDisplay );
    }

    public static final Display getDisplay( final MIDlet aMIDlet )
    {
        final Display display = myDisplays.get( aMIDlet );
        if ( display == null ) throw new RuntimeException( "NYI" );
        return display;
    }

    public abstract void setCurrent( final Displayable aDisplayable );

    // Implementation

    protected Display()
    {
    }

    private static final HashMap<MIDlet, Display> myDisplays = new HashMap<MIDlet, Display>();
}
