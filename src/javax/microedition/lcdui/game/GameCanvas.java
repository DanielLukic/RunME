package javax.microedition.lcdui.game;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;



/**
 * TODO: Describe this!
 */
public class GameCanvas extends Canvas
    {
    public boolean mySuppressKeyEvents = false;

    public GameCanvas( final boolean aSuppressKeyEvents )
        {
        mySuppressKeyEvents = aSuppressKeyEvents;
        }

    public final Graphics getGraphics()
        {
        if ( myBuffer == null || myGraphics == null )
            {
            myBuffer = Image.createImage( super.getWidth(), super.getHeight() );
            myGraphics = myBuffer.getGraphics();
            }
        return myGraphics;
        }

    public final void flushGraphics()
        {
        // In the RunME implementation this will directly call paint:
        repaint();
        }

    // From Canvas

    protected final void paint( final Graphics aGraphics )
        {
        aGraphics.drawImage( myBuffer, 0, 0, Graphics.TOP | Graphics.LEFT );
        }



    private Image myBuffer;

    private Graphics myGraphics;
    }
