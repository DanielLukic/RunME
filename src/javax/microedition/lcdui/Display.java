package javax.microedition.lcdui;

import javax.microedition.midlet.MIDlet;
import java.util.HashMap;

public abstract class Display
    {
    protected Display()
        {
        }

    // RunME API

    public static Displayable getDisplayable( final MIDlet aMidlet )
        {
        return getDisplay( aMidlet ).getCurrent();
        }

    public static void register( final MIDlet aMidlet, final Display aDisplay )
        {
        if ( myDisplays.containsKey( aMidlet ) ) throw new IllegalStateException();
        myDisplays.put( aMidlet, aDisplay );
        }

    public static void unregister( final MIDlet aMidlet )
        {
        if ( !myDisplays.containsKey( aMidlet ) ) throw new IllegalStateException();
        myDisplays.remove( aMidlet );
        }

    // J2ME API

    public static Display getDisplay( final MIDlet aMidlet )
        {
        if ( !myDisplays.containsKey( aMidlet ) ) throw new IllegalStateException();
        return myDisplays.get( aMidlet );
        }

    public void setCurrent( final Alert aAlert, final Displayable aDisplayable )
        {
        aAlert.showNativeDialog();
        }

    public void setCurrent( final Displayable aDisplayable )
        {
        if ( myDisplayable != null ) myDisplayable.hide();
        myDisplayable = aDisplayable;
        if ( myDisplayable != null ) myDisplayable.show();
        }

    public final Displayable getCurrent()
        {
        return myDisplayable;
        }


    private Displayable myDisplayable;

    private static final HashMap<MIDlet, Display> myDisplays = new HashMap<MIDlet, Display>();
    }
