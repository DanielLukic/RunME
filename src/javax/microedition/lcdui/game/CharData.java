package javax.microedition.lcdui.game;

import javax.microedition.lcdui.*;


final class CharData
    {
    public final int width;

    public final int height;


    CharData( final int aCharWidth, final int aCharHeight )
        {
        width = aCharWidth;
        height = aCharHeight;
        myPixelData = new int[aCharWidth * aCharHeight];
        }

    CharData( final Image aCharBitmap, final int aX, final int aY, final int aCharWidth, final int aCharHeight )
        {
        this( aCharWidth, aCharHeight );
        aCharBitmap.getRGB( myPixelData, 0, aCharWidth, aX, aY, aCharWidth, aCharHeight );
        }

    public final int[] pixelData()
        {
        return myPixelData;
        }

    public final void blit( final Graphics aGC, final int aX, final int aY )
        {
        final int dx = aGC.getTranslateX();
        final int dy = aGC.getTranslateY();
        aGC.translate( -dx, -dy );
        aGC.drawRGB( myPixelData, 0, width, aX + dx, aY + dy, width, height, true );
        aGC.translate( dx, dy );
        }

    public final void blitScaled( final Graphics aGraphics, final int aX, final int aY, final int aWidth, final int aHeight )
        {
        javax.microedition.lcdui.game.CharData.updateBuffer( aWidth, aHeight );

        int idx = 0;
        for ( int y = 0; y < aHeight; y++ )
            {
            for ( int x = 0; x < aWidth; x++ )
                {
                final int xSrc = x * ( width - 1 ) / ( aWidth - 1 );
                final int ySrc = y * ( height - 1 ) / ( aHeight - 1 );
                javax.microedition.lcdui.game.CharData.myScaleBuffer[ idx++ ] = myPixelData[ xSrc + ySrc * width ];
                }
            }

        aGraphics.drawRGB( javax.microedition.lcdui.game.CharData.myScaleBuffer, 0, aWidth, aX, aY, aWidth, aHeight, true );
        }

    private static final void updateBuffer( final int aWidth, final int aHeight )
        {
        final int bufferSize = aWidth * aHeight;
        if ( javax.microedition.lcdui.game.CharData.myScaleBuffer == null )
            javax.microedition.lcdui.game.CharData.myScaleBuffer = new int[bufferSize];
        if ( javax.microedition.lcdui.game.CharData.myScaleBuffer.length < bufferSize )
            javax.microedition.lcdui.game.CharData.myScaleBuffer = new int[bufferSize];
        }


    private final int[] myPixelData;

    private static int[] myScaleBuffer;
    }
