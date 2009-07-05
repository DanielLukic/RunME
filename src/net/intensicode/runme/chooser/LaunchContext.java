package net.intensicode.runme.chooser;

public interface LaunchContext
    {
    MIDletEntry getSelectedEntry();

    void disposeChooser();
    }
