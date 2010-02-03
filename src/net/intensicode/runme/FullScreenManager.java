package net.intensicode.runme;

public final class FullScreenManager
    {
    public FullScreenManager( final SystemContext aSystemContext )
        {
        mySystemContext = aSystemContext;
        }

    public final void toggleFullScreenMode()
        {
        final boolean isInFullScreenMode = getDisplayContext().isFullScreen();
        setFullScreenMode( !isInFullScreenMode );
        }

    public final void setFullScreenMode( final boolean aFullScreenFlag )
        {
        getDisplayContext().hideAndDispose();
        if ( aFullScreenFlag )
            {
            getWindowedMode().deactivate();
            getFullScreenMode().activate();
            }
        else
            {
            getFullScreenMode().deactivate();
            getWindowedMode().activate();
            }
        }

    // Implementation

    private DisplayContext getDisplayContext()
        {
        return mySystemContext.displayContext;
        }

    private WindowedMode getWindowedMode()
        {
        if ( myWindowedMode != null ) return myWindowedMode;
        return myWindowedMode = new WindowedMode( getDisplayContext(), mySystemContext.displayBuffer );
        }

    private FrameDisplayMode getFullScreenMode()
        {
        if ( myFullScreenMode instanceof FullScreenMode ) return myFullScreenMode;
        return myFullScreenMode = new FullScreenMode( getDisplayContext() );
        }


    private WindowedMode myWindowedMode;

    private FrameDisplayMode myFullScreenMode;

    private final SystemContext mySystemContext;
    }
