package net.intensicode.runme;

import java.awt.*;
import java.awt.image.VolatileImage;

public final class MIDletDisplayBuffer implements DisplayBuffer
    {
    public MIDletDisplayBuffer( final SystemContext aSystemContext, final Dimension aEmulatedDisplaySize )
        {
        mySystemContext = aSystemContext;
        myEmulatedDisplaySize = aEmulatedDisplaySize;
        }

    // From DisplayBuffer

    public final int width()
        {
        return myEmulatedDisplaySize.width;
        }

    public final int height()
        {
        return myEmulatedDisplaySize.height;
        }

    public final javax.microedition.lcdui.Graphics beginFrame()
        {
        disposeGraphicsAndBufferIfIncompatible();
        createNewBufferIfNecessary();
        createNewGraphicsIfNecessary();
        return myBufferGraphics;
        }

    public final void endFrame()
        {
        final DisplayContext displayContext = getDisplayContext();
        displayContext.repaint();
        }

    public final void renderInto( final Graphics aGraphics, final int aX, final int aY, final int aWidth, final int aHeight )
        {
        if ( myBufferImage == null ) return;
        aGraphics.drawImage( myBufferImage, aX, aY, aWidth, aHeight, null );
        }

    // Implementation

    private DisplayContext getDisplayContext()
        {
        return mySystemContext.displayContext;
        }

    private GraphicsConfiguration getGraphicsConfiguration()
        {
        final DisplayContext provider = getDisplayContext();
        return provider.getGraphicsConfiguration();
        }

    private void disposeGraphicsAndBufferIfIncompatible()
        {
        if ( myBufferImage == null ) return;

        final GraphicsConfiguration configuration = getGraphicsConfiguration();
        if ( myBufferImage.validate( configuration ) != VolatileImage.IMAGE_INCOMPATIBLE ) return;

        myBufferImage = null;
        }

    private void createNewBufferIfNecessary()
        {
        if ( myBufferImage != null ) return;

        final GraphicsConfiguration configuration = getGraphicsConfiguration();
        myBufferImage = configuration.createCompatibleVolatileImage( myEmulatedDisplaySize.width, myEmulatedDisplaySize.height );
        }

    private void createNewGraphicsIfNecessary()
        {
        if ( myBufferGraphics != null ) return;

        myBufferGraphics = new javax.microedition.lcdui.Graphics( myBufferImage );
        }


    private VolatileImage myBufferImage;

    private javax.microedition.lcdui.Graphics myBufferGraphics;

    private final SystemContext mySystemContext;

    private final Dimension myEmulatedDisplaySize;
    }
