package net.intensicode.runme;

import java.awt.*;

public final class WindowedMode implements FrameDisplayMode
    {
    public WindowedMode( final DisplayContext aDisplayContext, final DisplayBuffer aDisplayBuffer )
        {
        myDisplayContext = aDisplayContext;
        myDisplayBuffer = aDisplayBuffer;
        }

    // From FrameDisplayMode

    public final void activate()
        {
        myDisplayContext.showTitleAndBorder();
        centerFrame();
        myDisplayContext.showAndFocus();
        }

    public final void deactivate()
        {
        }

    // Implementation

    private void centerFrame()
        {
        final GraphicsDevice device = myDisplayContext.getGraphicsDevice();
        final DisplayMode mode = device.getDisplayMode();
        final int availableWidth = mode.getWidth() * 80 / 100;
        final int availableHeight = mode.getHeight() * 80 / 100;
        final int midletWidth = myDisplayBuffer.width();
        final int midletHeight = myDisplayBuffer.height();
        final int xFactor = availableWidth / midletWidth;
        final int yFactor = availableHeight / midletHeight;
        final int factor = Math.min( xFactor, yFactor );
        final int width = midletWidth * factor;
        final int height = midletHeight * factor;
        myDisplayContext.centerAndResizeTo( width, height );
        }


    private final DisplayBuffer myDisplayBuffer;

    private final DisplayContext myDisplayContext;
    }
