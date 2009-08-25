package javax.microedition.lcdui;

import net.intensicode.runme.util.Log;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


public final class Image
    {
    public static GraphicsConfiguration target_gc;


    public static final Image createImage( final byte[] aData, final int aOffset, final int aLength ) throws IOException
        {
        return createImage( new ByteArrayInputStream( aData, aOffset, aLength ) );
        }

    public static final Image createImage( final InputStream aInputStream ) throws IOException
        {
        if ( aInputStream == null ) throw new NullPointerException();

        final BufferedImage loaded = ImageIO.read( aInputStream );
        if ( loaded == null ) throw new NullPointerException();

        final BufferedImage image = createBlitImage( loaded );
        final Graphics2D graphics = image.createGraphics();
        graphics.drawImage( loaded, 0, 0, null );
        return new Image( image, graphics );
        }

    public static final Image createImage( final int aWidth, final int aHeight )
        {
        if ( target_gc != null )
            {
            return new Image( target_gc.createCompatibleImage( aWidth, aHeight, Transparency.TRANSLUCENT ) );
            }
        else
            {
            return new Image( new BufferedImage( aWidth, aHeight, BufferedImage.TYPE_INT_ARGB ) );
            }
        }

    public static final Image createImage( final Image aSource, final int aX, final int aY, final int aWidth, final int aHeight, final int aTransform )
        {
        if ( aTransform != 0 ) throw new RuntimeException( "nyi" );
        final Image image = createImage( aWidth, aHeight );
        image.getGraphics().drawImage( aSource, -aX, -aY, Graphics.TOP | Graphics.LEFT );
        return image;
        }

    public static final Image createRGBImage( final int[] aARGB32, final int aWidth, final int aHeight, final boolean aAlpha )
        {
        final int transparency = aAlpha ? Transparency.TRANSLUCENT : Transparency.OPAQUE;
        final BufferedImage image = target_gc.createCompatibleImage( aWidth, aHeight, transparency );
        image.setRGB( 0, 0, aWidth, aHeight, aARGB32, 0, aWidth );
        return new Image( image );
        }

    public final int getWidth()
        {
        return myBufferedImage.getWidth();
        }

    public final int getHeight()
        {
        return myBufferedImage.getHeight();
        }

    public final Graphics getGraphics()
        {
        return myBufferedGraphics;
        }

    public final void getRGB( final int[] aARGB32, final int aOffset, final int aScansize, final int aX, final int aY, final int aWidth, final int aHeight )
        {
        final int width = Math.min( aWidth, getWidth() - aX );
        final int height = Math.min( aHeight, getHeight() - aY );
        myBufferedImage.getRGB( aX, aY, width, height, aARGB32, aOffset, aScansize );
        }

    // Package Interface

    final java.awt.Image getInternalImage()
        {
        return myBufferedImage;
        }

    // Implementation

    private Image( final BufferedImage aImage )
        {
        this( aImage, aImage.createGraphics() );
        }

    private Image( final BufferedImage aImage, final Graphics2D aGraphics2D )
        {
        myBufferedImage = aImage;
        myBufferedGraphics = new Graphics( aImage, aGraphics2D );
        }

    private static final BufferedImage createBlitImage( final BufferedImage loaded )
        {
        if ( target_gc == null )
            {
            LOG.debug( "Creating slow image - no target_gc" );
            return loaded;
            }
        return target_gc.createCompatibleImage( loaded.getWidth(), loaded.getHeight(), Transparency.TRANSLUCENT );
        }



    private final Graphics myBufferedGraphics;

    private final BufferedImage myBufferedImage;

    private static final Log LOG = Log.create();
    }
