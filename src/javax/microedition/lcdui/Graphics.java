package javax.microedition.lcdui;

import net.intensicode.runme.util.Log;

import java.awt.*;
import java.awt.image.*;


/**
 * TODO: Describe this!
 */
public final class Graphics
    {
    public static final int HCENTER = 1;

    public static final int VCENTER = 2;

    public static final int LEFT = 4;

    public static final int RIGHT = 8;

    public static final int TOP = 16;

    public static final int BOTTOM = 32;

    public static final int BASELINE = 64;


    public Graphics( final VolatileImage aImage )
        {
        this( aImage, aImage.createGraphics() );
        }

    public Graphics( final BufferedImage aImage )
        {
        this( aImage, aImage.createGraphics() );
        }

    Graphics( final java.awt.Image aImage, final Graphics2D aGraphics2D )
        {
        myGraphics = aGraphics2D;
        myGraphics.setClip( 0, 0, aImage.getWidth( null ), aImage.getHeight( null ) );
        myBlitBuffer = new BufferedImage( aImage.getWidth( null ), aImage.getHeight( null ), BufferedImage.TYPE_INT_ARGB );
        myBlitGraphics = myBlitBuffer.createGraphics();
        }

    public final void dispose()
        {
        myGraphics.dispose();
        myBlitGraphics.dispose();
        }

    public final void setColor( final int aRGB24 )
        {
        if ( ( aRGB24 >> 24 ) == 0 )
            {
            myGraphics.setColor( new Color( aRGB24, false ) );
            }
        else
            {
            myGraphics.setColor( new Color( aRGB24, true ) );
            }
        }

    public final void setFont( final Font aFont )
        {
        if ( myFont != null ) myFont.detachFrom( myGraphics );
        myFont = aFont;
        if ( myFont != null )
            {
            myFont.attachTo( myGraphics );
            }
        }

    public final void clipRect( final int aX, final int aY, final int myWidth, final int myHeight )
        {
        myGraphics.clipRect( aX, aY, myWidth, myHeight );
        }

    public final void setClip( final int aX, final int aY, final int aWidth, final int aHeight )
        {
        myGraphics.setClip( aX, aY, aWidth, aHeight );
        }

    public final void translate( final int aDeltaX, final int aDeltaY )
        {
        myTranslation.x += aDeltaX;
        myTranslation.y += aDeltaY;
        myGraphics.translate( aDeltaX, aDeltaY );
        }

    public final int getClipX()
        {
        return myGraphics.getClipBounds().x;
        }

    public final int getClipY()
        {
        return myGraphics.getClipBounds().y;
        }

    public final int getClipWidth()
        {
        return myGraphics.getClipBounds().width;
        }

    public final int getClipHeight()
        {
        return myGraphics.getClipBounds().height;
        }

    public final int getTranslateX()
        {
        return myTranslation.x;
        }

    public final int getTranslateY()
        {
        return myTranslation.y;
        }

    public final void drawImage( final Image aImage, final int aX, final int aY, final int aAlignment )
        {
        final int imageWidth = aImage.getWidth();
        final int imageHeight = aImage.getHeight();

        final Point alignedPoint = getAlignedPosition( aX, aY, imageWidth, imageHeight, aAlignment );
        myGraphics.drawImage( aImage.getInternalImage(), alignedPoint.x, alignedPoint.y, null );
        }

    public final void drawRGB( final int[] aARGB32, final int aOffset, final int aScansize, final int aX, final int aY, final int aWidth, final int aHeight, final boolean aProcessAlpha )
        {
        try
            {
            myBlitGraphics.setColor( Color.BLACK );
            myBlitGraphics.fillRect( 0, 0, aWidth, aHeight );
            myBlitBuffer.setRGB( 0, 0, aWidth, aHeight, aARGB32, aOffset, aScansize );
            final Shape savedClip = myGraphics.getClip();
            myGraphics.clipRect( aX, aY, aWidth, aHeight );
            myGraphics.drawImage( myBlitBuffer, aX, aY, null );
            myGraphics.setClip( savedClip );
            }
        catch ( final Throwable t )
            {
            Log.error( "unexpected error while drawing", t );
            }
        }

    public final void drawSubstring( final String aText, final int aOffset, final int aLength, final int aX, final int aY, final int aAlignment )
        {
        final int yOffset = myFont.getBaselinePosition();
        final Point alignedPoint = getAlignedPosition( aX, aY, myFont.stringWidth( aText ), myFont.getHeight(), aAlignment );
        myGraphics.drawChars( aText.toCharArray(), aOffset, aLength, alignedPoint.x, alignedPoint.y + yOffset );
        }

    public final void drawString( final String aText, final int aX, final int aY, final int aAlignment )
        {
        final int yOffset = myFont.getBaselinePosition();
        final Point alignedPoint = getAlignedPosition( aX, aY, myFont.stringWidth( aText ), myFont.getHeight(), aAlignment );
        myGraphics.drawString( aText, alignedPoint.x, alignedPoint.y + yOffset );
        }

    public final void drawChar( final char aChar, final int aX, final int aY, final int aAlignment )
        {
        if ( myFont == null ) return;

        final char[] chars = new char[1];
        chars[ 0 ] = aChar;

        final int yOffset = myFont.getBaselinePosition();
        final Point alignedPoint = getAlignedPosition( aX, aY, myFont.charWidth( aChar ), myFont.getHeight(), aAlignment );
        myGraphics.drawChars( chars, 0, 1, alignedPoint.x, alignedPoint.y + yOffset );
        }

    public final void drawLine( final int aX1, final int aY1, final int aX2, final int aY2 )
        {
        myGraphics.drawLine( aX1, aY1, aX2, aY2 );
        }

    public final void drawRect( final int aX, final int aY, final int aWidth, final int aHeight )
        {
        myGraphics.drawRect( aX, aY, aWidth, aHeight );
        }

    public final void fillRect( final int aX, final int aY, final int aWidth, final int aHeight )
        {
        myGraphics.fillRect( aX, aY, aWidth, aHeight );
        }

    private final int[] myPointsX = new int[3];

    private final int[] myPointsY = new int[3];

    public final void fillTriangle( final int aX1, final int aY1, final int aX2, final int aY2, final int aX3, final int aY3 )
        {
        myPointsX[ 0 ] = aX1;
        myPointsX[ 1 ] = aX2;
        myPointsX[ 2 ] = aX3;
        myPointsY[ 0 ] = aY1;
        myPointsY[ 1 ] = aY2;
        myPointsY[ 2 ] = aY3;
        myGraphics.fillPolygon( myPointsX, myPointsY, 3 );
        }

    // Package Interface

    final Graphics2D getInternalGraphics()
        {
        return myGraphics;
        }

    // Implementation

    private Point getAlignedPosition( final int aX, final int aY, final int aWidth, final int aHeight, final int aAlignment )
        {
        final int x;
        if ( ( aAlignment & Graphics.LEFT ) != 0 )
            {
            x = aX;
            }
        else if ( ( aAlignment & Graphics.RIGHT ) != 0 )
            {
            x = aX - aWidth;
            }
        else if ( ( aAlignment & Graphics.HCENTER ) != 0 )
                {
                x = aX - aWidth / 2;
                }
            else
                {
                throw new IllegalArgumentException();
                }

        final int y;
        if ( ( aAlignment & Graphics.TOP ) != 0 )
            {
            y = aY;
            }
        else if ( ( aAlignment & Graphics.BOTTOM ) != 0 )
            {
            y = aY - aHeight;
            }
        else if ( ( aAlignment & Graphics.VCENTER ) != 0 )
                {
                y = aY - aHeight / 2;
                }
            else
                {
                throw new IllegalArgumentException();
                }

        myAlignedPosition.x = x;
        myAlignedPosition.y = y;
        return myAlignedPosition;
        }


    private Font myFont;

    private final Graphics2D myGraphics;

    private final Graphics2D myBlitGraphics;

    private final BufferedImage myBlitBuffer;

    private final Point myTranslation = new Point();

    private final Point myAlignedPosition = new Point();
    }
