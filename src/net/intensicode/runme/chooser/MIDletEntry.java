package net.intensicode.runme.chooser;

import java.io.File;

public final class MIDletEntry
    {
    public MIDletEntry( final File aFile )
        {
        myFile = aFile;
        }

    // From Object

    public final String toString()
        {
        return myFile.getName();
        }

    public final String getPathName()
        {
        return myFile.getPath();
        }

    private final File myFile;
    }
