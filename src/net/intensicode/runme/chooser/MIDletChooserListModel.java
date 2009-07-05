package net.intensicode.runme.chooser;

import javax.swing.*;
import java.util.ArrayList;

public final class MIDletChooserListModel extends AbstractListModel
    {
    public MIDletChooserListModel( final ArrayList<MIDletEntry> aMIDlets )
        {
        myMIDlets = aMIDlets;
        }

    public final int getSize()
        {
        return myMIDlets.size();
        }

    public final Object getElementAt( final int aIndex )
        {
        return myMIDlets.get( aIndex );
        }

    private final ArrayList<MIDletEntry> myMIDlets;
    }
