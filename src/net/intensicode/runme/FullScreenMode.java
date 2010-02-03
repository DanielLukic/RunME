package net.intensicode.runme;

public final class FullScreenMode implements FrameDisplayMode
    {
    public FullScreenMode( final DisplayContext aDisplayContext )
        {
        myDisplayContext = aDisplayContext;
        }

    public final void activate()
        {
        myDisplayContext.hideTitleAndBorder();
        myDisplayContext.makeFullScreen();
        myDisplayContext.showAndFocus();
        }

    public final void deactivate()
        {
        myDisplayContext.unmakeFullScreen();
        }

    private final DisplayContext myDisplayContext;
    }
