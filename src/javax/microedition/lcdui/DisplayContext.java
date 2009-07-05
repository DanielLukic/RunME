package javax.microedition.lcdui;

/**
 * TODO: Describe this!
 */
public interface DisplayContext
    {
    int displayWidth();

    int displayHeight();

    void onRepaintDone();

    Graphics displayGraphics();
    }
