package javax.microedition.lcdui;

import net.intensicode.runme.util.Log;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public final class Alert extends Displayable
    {
    public static boolean someDialogIsShowing;

    public static final int FOREVER = 0;


    public Alert( final String aTitle )
        {
        myTitle = aTitle;
        }

    public final void setType( final AlertType aType )
        {
        myType = aType;
        }

    public final void setCommandListener( final CommandListener aListener )
        {
        myCommandListener = aListener;
        }

    public final void setString( final String aMessage )
        {
        myMessage = aMessage;
        }

    public final void setTimeout( final int aTimeOut )
        {
        myTimeOut = aTimeOut;
        }

    public final void addCommand( final Command aCommand )
        {
        myCommands.add( aCommand );
        }

    public final void showNativeDialog()
        {
        if ( someDialogIsShowing ) return;
        someDialogIsShowing = true;

        final Displayable displayable = this;
        new Thread()
        {
        public void run()
            {
            final Object[] options = getOptions();
            final Object initial = options[ options.length - 1 ];
            final int selectedIndex = JOptionPane.showOptionDialog( null, myMessage, myTitle, JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, initial );
            Log.debug( "option dialog: " + selectedIndex );
            final Object selected = selectedIndex == -1 ? initial : options[ selectedIndex ];
            myCommandListener.commandAction( (Command) selected, displayable );

            someDialogIsShowing = false;
            }
        }.start();
        }

    // From Displayable

    protected void paint( final Graphics aGraphics )
        {
        }

    // Implementation

    private final Object[] getOptions()
        {
        final Object[] options = new Object[myCommands.size()];
        myCommands.toArray( options );
        return options;
        }

    private int myTimeOut;

    private String myMessage;

    private AlertType myType;

    private final String myTitle;

    private CommandListener myCommandListener;

    private final ArrayList<Command> myCommands = new ArrayList<Command>();
    }
