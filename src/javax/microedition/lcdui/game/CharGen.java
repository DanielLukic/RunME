package javax.microedition.lcdui.game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;



final class CharGen
{
    public final int charWidth;

    public final int charHeight;

    public final int charsPerRow;

    public final int charsPerColumn;



    public static final javax.microedition.lcdui.game.CharGen fromLayout( final Image aCharBitmap, final int aCharsPerRow, final int aCharsPerColumn )
    {
        final int charWidth = aCharBitmap.getWidth() / aCharsPerRow;
        final int charHeight = aCharBitmap.getHeight() / aCharsPerColumn;
        return new javax.microedition.lcdui.game.CharGen( aCharBitmap, charWidth, charHeight );
    }

    public static final javax.microedition.lcdui.game.CharGen fromSize( final Image aCharBitmap, final int aCharWidth, final int aCharHeight )
    {
        return new javax.microedition.lcdui.game.CharGen( aCharBitmap, aCharWidth, aCharHeight );
    }

    public final CharData getCharData( final int aCharCode )
    {
        if ( aCharCode < 0 || aCharCode >= myDataCache.length )
        {
            return getCharData( 0 );
        }

        if ( myDataCache[ aCharCode ] == null )
        {
            final int column = aCharCode % charsPerRow;
            final int row = aCharCode / charsPerRow;

            final int x = column * charWidth;
            final int y = row * charHeight;
            myDataCache[ aCharCode ] = new CharData( myBitmap, x, y, charWidth, charHeight );
        }

        return myDataCache[ aCharCode ];
    }

    public final void blit( final Graphics aGC, final int aX, final int aY, final int aCharCode )
    {
        final CharData data = getCharData( aCharCode );
        if ( data == null ) return;
        data.blit( aGC, aX, aY );
    }

    private CharGen( final Image aCharBitmap, final int aCharWidth, final int aCharHeight )
    {
        final int bitmapWidth = aCharBitmap.getWidth();
        final int bitmapHeight = aCharBitmap.getHeight();
        final int charsPerRow = bitmapWidth / aCharWidth;
        final int charsPerColumn = bitmapHeight / aCharHeight;

        //#if DEBUG
        if ( charsPerRow * aCharWidth != bitmapWidth || charsPerColumn * aCharHeight != bitmapHeight )
        {
            throw new IllegalArgumentException( "Char size mismatch" );
        }
        //#endif

        myBitmap = aCharBitmap;
        charWidth = aCharWidth;
        charHeight = aCharHeight;
        this.charsPerRow = charsPerRow;
        this.charsPerColumn = charsPerColumn;
        myDataCache = new CharData[ charsPerRow * charsPerColumn ];
    }



    private final Image myBitmap;

    private final CharData[] myDataCache;
}
