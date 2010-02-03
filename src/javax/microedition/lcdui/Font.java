package javax.microedition.lcdui;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.*;

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

    public static Dimension displaySize;


    // RunME API

    void attachTo( final Graphics2D aGraphics2D )
        {
        if ( myAttachedGraphics != null ) throw new IllegalStateException();
        myAttachedGraphics = aGraphics2D;
        myAttachedGraphics.setFont( myAwtFont );
        }

    void detachFrom( final Graphics2D aGraphics2D )
        {
        if ( myAttachedGraphics != aGraphics2D ) throw new IllegalArgumentException();
        myAttachedGraphics.setFont( null );
        myAttachedGraphics = null;
        }

    // J2ME API

    public static Font getFont( final int aFace, final int aStyle, final int aSize )
        {
        final String name = determineAwtFontName( aFace );
        final int style = determineAwtFontStyle( aStyle );
        final int size = determineAwtFontSize( aSize );
        return new Font( new java.awt.Font( name, style, size ) );
        }

    public static Font getDefaultFont()
        {
        return getFont( FACE_SYSTEM, STYLE_PLAIN, SIZE_MEDIUM );
        }

    public final int charWidth( final char aChar )
        {
        return stringWidth( new String( new char[]{ aChar } ) );
        }

    public final int stringWidth( final String aString )
        {
        final FontRenderContext frc = getFontRenderContext();
        final Rectangle2D rectangle2D = myAwtFont.getStringBounds( aString, frc );
        return (int) Math.round( rectangle2D.getWidth() );
        }

    public final int substringWidth( final String aString, final int aOffset, final int aLength )
        {
        final FontRenderContext frc = getFontRenderContext();
        final Rectangle2D rectangle2D = myAwtFont.getStringBounds( aString, aOffset, aOffset + aLength, frc );
        return (int) Math.round( rectangle2D.getWidth() );
        }

    public final int getHeight()
        {
        final FontRenderContext frc = getFontRenderContext();
        final Rectangle2D rectangle2D = myAwtFont.getStringBounds( METRICS_TEXT, frc );
        return (int) Math.round( rectangle2D.getHeight() );
        }

    public final int getBaselinePosition()
        {
        final FontRenderContext frc = getFontRenderContext();
        final Rectangle2D rectangle2D = myAwtFont.getStringBounds( METRICS_TEXT, frc );
        return (int) Math.round( -rectangle2D.getY() );
        }

    // Implementation

    private Font( final java.awt.Font aFont )
        {
        myAwtFont = aFont;
        }

    private FontRenderContext getFontRenderContext()
        {
        if ( myAttachedGraphics == null ) return DEFAULT_FONT_RENDER_CONTEXT;
        return myAttachedGraphics.getFontRenderContext();
        }

    private static String determineAwtFontName( final int aFace )
        {
        if ( aFace == FACE_MONOSPACE ) return "Monospaced";
        if ( aFace == FACE_PROPORTIONAL ) return "Serif";
        if ( aFace == FACE_SYSTEM ) return "SansSerif";
        throw new IllegalArgumentException();
        }

    private static int determineAwtFontStyle( final int aStyle )
        {
        if ( aStyle == STYLE_PLAIN ) return java.awt.Font.PLAIN;
        if ( aStyle == STYLE_BOLD ) return java.awt.Font.BOLD;
        if ( aStyle == STYLE_ITALIC ) return java.awt.Font.ITALIC;
        throw new IllegalArgumentException();
        }

    private static int determineAwtFontSize( final int aSize )
        {
        final int displaySize = (int) Math.sqrt( Font.displaySize.height );
        final int referenceSize = ( displaySize == 0 ) ? REFERENCE_DISPLAY_SIZE : displaySize;
        if ( aSize == SIZE_LARGE ) return 16 * referenceSize / REFERENCE_DISPLAY_SIZE;
        if ( aSize == SIZE_MEDIUM ) return 14 * referenceSize / REFERENCE_DISPLAY_SIZE;
        if ( aSize == SIZE_SMALL ) return 12 * referenceSize / REFERENCE_DISPLAY_SIZE;
        throw new IllegalArgumentException();
        }


    private Graphics2D myAttachedGraphics;

    private final java.awt.Font myAwtFont;

    private static final int REFERENCE_DISPLAY_SIZE = (int) Math.sqrt( 416 * 3 / 4 );

    private static final String METRICS_TEXT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_,^%~/|\\?";

    private static final FontRenderContext DEFAULT_FONT_RENDER_CONTEXT = new FontRenderContext( new AffineTransform(), false, false );
    }
