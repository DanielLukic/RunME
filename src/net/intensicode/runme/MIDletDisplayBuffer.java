package net.intensicode.runme;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.IOException;

public final class MIDletDisplayBuffer implements DisplayBuffer
    {
    public static final boolean capture = false;

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
        if ( capture ) captureBufferImage();
        }

    private void captureBufferImage()
        {
        try
            {
            createCaptureFolderIfNecessary();
            createCaptureImageIfNecessary();

            myCaptureCounter++;
            if ( myCaptureCounter % 4 != 0 ) return;

            captureImage();
            writeCapturedImage();
            }
        catch ( final Exception e )
            {
            e.printStackTrace();
            }
        }

    private void createCaptureFolderIfNecessary()
        {
        if ( myCaptureFolder != null ) return;
        myCaptureFolder = new File( "/home/dl/Desktop/runme" );
        myCaptureFolder.mkdirs();
        }

    private void createCaptureImageIfNecessary()
        {
        if ( myCaptureImage != null ) return;
        myCaptureImage = new BufferedImage( width(), height(), BufferedImage.TYPE_INT_ARGB );
        myCaptureGraphics = myCaptureImage.createGraphics();
        }

    private void captureImage()
        {
        myCaptureGraphics.drawImage( myBufferImage, 0, 0, null );
        }

    private void writeCapturedImage() throws IOException
        {
        final File captureFile = new File( myCaptureFolder, String.format( "%04d.png", myCaptureCounter ) );
        ImageIO.write( myCaptureImage, "png", captureFile );
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


    private File myCaptureFolder;

    private int myCaptureCounter;

    private VolatileImage myBufferImage;

    private BufferedImage myCaptureImage;

    private Graphics2D myCaptureGraphics;

    private javax.microedition.lcdui.Graphics myBufferGraphics;

    private final SystemContext mySystemContext;

    private final Dimension myEmulatedDisplaySize;
    }
