package javax.microedition.lcdui.game;

import javax.microedition.lcdui.*;


public final class Sprite
    {
    public Sprite( final Image aImage, final int aFrameWidth, final int aFrameHeight )
        {
        myCharGen = CharGen.fromSize( aImage, aFrameWidth, aFrameHeight );
        myFrameSequence = new int[myCharGen.charsPerRow * myCharGen.charsPerColumn];
        for ( int idx = 0; idx < myFrameSequence.length; idx++ )
            {
            myFrameSequence[ idx ] = idx;
            }
        }

    public final int getWidth()
        {
        return myCharGen.charWidth;
        }

    public final int getHeight()
        {
        return myCharGen.charHeight;
        }

    public final int getRawFrameCount()
        {
        return myCharGen.charsPerRow * myCharGen.charsPerColumn;
        }

    public final int getFrameSequenceLength()
        {
        return myFrameSequence.length;
        }

    public final void defineReferencePixel( final int aOffsetX, final int aOffsetY )
        {
        myRefOffset.x = aOffsetX;
        myRefOffset.y = aOffsetY;
        }

    public final void setRefPixelPosition( final int aX, final int aY )
        {
        myPosition.x = aX;
        myPosition.y = aY;
        }

    public final void setFrame( final int aFrameID )
        {
        myFrameID = myFrameSequence[ aFrameID ];
        }

    public final void setFrameSequence( final int[] aFrameSequence )
        {
        myFrameSequence = new int[aFrameSequence.length];
        for ( int idx = 0; idx < myFrameSequence.length; idx++ )
            {
            myFrameSequence[ idx ] = aFrameSequence[ idx ];
            }
        }

    public final void paint( final Graphics aGraphics )
        {
        final int x = myPosition.x - myRefOffset.x;
        final int y = myPosition.y - myRefOffset.y;
        myCharGen.blit( aGraphics, x, y, myFrameID );
        }


    private int myFrameID;

    private int[] myFrameSequence;

    private final CharGen myCharGen;

    private final Position myPosition = new Position();

    private final Position myRefOffset = new Position();
    }
