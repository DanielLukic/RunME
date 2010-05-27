package javax.microedition.lcdui;

public class Command
    {
    public Command( final String aLabel, final int aType, final int aPriority )
        {
        myLabel = aLabel;
        myType = aType;
        myPriority = aPriority;
        }

    public String toString()
        {
        return myLabel;
        }

    private final int myType;

    private final int myPriority;

    private final String myLabel;
    }
