package javax.microedition.lcdui;

import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;



/**
 * TODO: Describe this!
 */
public final class Font
{
    public static final int STYLE_PLAIN = 0;

    public static final int STYLE_BOLD = 1;

    public static final int STYLE_ITALIC = 2;

    public static final int STYLE_UNDERLINED = 4;

    public static final int SIZE_SMALL = 8;

    public static final int SIZE_MEDIUM = 0;

    public static final int SIZE_LARGE = 16;

    public static final int FACE_SYSTEM = 0;

    public static final int FACE_MONOSPACE = 32;

    public static final int FACE_PROPORTIONAL = 64;

    public static final int FONT_STATIC_TEXT = 0;

    public static final int FONT_INPUT_TEXT = 1;


    final java.awt.Font awt_font;

    Graphics2D attached_graphics;

    public static int midlet_display_width;

    public static int midlet_display_height;



    public static final Font getDefaultFont()
    {
        return getFont( FACE_SYSTEM, STYLE_PLAIN, SIZE_MEDIUM );
    }

    public static final Font getFont( final int aFace, final int aStyle, final int aSize )
    {
        final String name;
        if ( aFace == FACE_MONOSPACE ) name = "Monospaced";
        else if ( aFace == FACE_PROPORTIONAL ) name = "Serif";
        else if ( aFace == FACE_SYSTEM ) name = "SansSerif";
        else throw new IllegalArgumentException();

        final int style;
        if ( aStyle == STYLE_PLAIN ) style = java.awt.Font.PLAIN;
        else if ( aStyle == STYLE_BOLD ) style = java.awt.Font.BOLD;
        else if ( aStyle == STYLE_ITALIC ) style = java.awt.Font.ITALIC;
        else throw new IllegalArgumentException();

        final int displaySize = ( int ) Math.sqrt( midlet_display_height );
        final int refSize = ( displaySize == 0 ) ? REFERENCE_DISPLAY_SIZE : displaySize;

        final int size;
        if ( aSize == SIZE_LARGE ) size = 16 * refSize / REFERENCE_DISPLAY_SIZE;
        else if ( aSize == SIZE_MEDIUM ) size = 14 * refSize / REFERENCE_DISPLAY_SIZE;
        else if ( aSize == SIZE_SMALL ) size = 12 * refSize / REFERENCE_DISPLAY_SIZE;
        else throw new IllegalArgumentException();

        return new Font( new java.awt.Font( name, style, size ) );
    }

    public final int charWidth( final char aChar )
    {
        return stringWidth( new String( new char[]{aChar} ) );
    }

    public final int stringWidth( final String aString )
    {
        final FontRenderContext frc = getFontRenderContext();
        final Rectangle2D rectangle2D = awt_font.getStringBounds( aString, frc );
        return ( int ) Math.round( rectangle2D.getWidth() );
    }

    public final int substringWidth( final String aString, final int aOffset, final int aLength )
    {
        final FontRenderContext frc = getFontRenderContext();
        final Rectangle2D rectangle2D = awt_font.getStringBounds( aString, aOffset, aOffset + aLength, frc );
        return ( int ) Math.round( rectangle2D.getWidth() );
    }

    public final int getHeight()
    {
        final FontRenderContext frc = getFontRenderContext();
        final Rectangle2D rectangle2D = awt_font.getStringBounds( METRICS_TEXT, frc );
        return ( int ) Math.round( rectangle2D.getHeight() );
    }

    public final int getBaselinePosition()
    {
        final FontRenderContext frc = getFontRenderContext();
        final Rectangle2D rectangle2D = awt_font.getStringBounds( METRICS_TEXT, frc );
        return ( int ) Math.round( -rectangle2D.getY() );
    }

    // Implementation

    private Font( final java.awt.Font aFont )
    {
        awt_font = aFont;
    }

    private final FontRenderContext getFontRenderContext()
    {
        if ( attached_graphics == null ) return DEFAULT_FONT_RENDER_CONTEXT;
        return attached_graphics.getFontRenderContext();
    }



    private static final int REFERENCE_DISPLAY_SIZE = ( int ) Math.sqrt( 416 * 3 / 4 );

    private static final String METRICS_TEXT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_,^%~/|\\?";

    private static final FontRenderContext DEFAULT_FONT_RENDER_CONTEXT = new FontRenderContext( new AffineTransform(), false, false );
}
