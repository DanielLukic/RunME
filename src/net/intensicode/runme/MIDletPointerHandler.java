package net.intensicode.runme;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import java.awt.Point;
import java.awt.event.*;

public final class MIDletPointerHandler implements MouseListener, MouseMotionListener
    {
    public MIDletPointerHandler( final MIDlet aMidlet, final DisplayTransformation aDisplayTransformation )
        {
        myMidlet = aMidlet;
        myTransformation = aDisplayTransformation;
        }

    // From MouseMotionListener

    public final void mouseDragged( final MouseEvent aMouseEvent )
        {
        if ( !myTransformation.valid ) return;

        final Canvas canvas = getCanvasOrNull();
        if ( canvas == null ) return;

        final Point canvasPoint = getCanvasPoint( canvas, aMouseEvent );
        canvas.pointerDragged( canvasPoint.x, canvasPoint.y );
        }

    public final void mouseMoved( final MouseEvent e )
        {
        }

    // From MouseListener

    public void mouseClicked( final MouseEvent e )
        {
        }

    public void mousePressed( final MouseEvent aMouseEvent )
        {
        if ( !myTransformation.valid ) return;

        final Canvas canvas = getCanvasOrNull();
        if ( canvas == null ) return;

        final Point canvasPoint = getCanvasPoint( canvas, aMouseEvent );
        canvas.pointerPressed( canvasPoint.x, canvasPoint.y );
        }

    public void mouseReleased( final MouseEvent aMouseEvent )
        {
        if ( !myTransformation.valid ) return;

        final Canvas canvas = getCanvasOrNull();
        if ( canvas == null ) return;

        final Point point = getCanvasPoint( canvas, aMouseEvent );
        canvas.pointerReleased( point.x, point.y );
        }

    public void mouseEntered( final MouseEvent e )
        {
        }

    public void mouseExited( final MouseEvent e )
        {
        }

    // Implementation

    private Canvas getCanvasOrNull()
        {
        final Displayable displayable = Display.getDisplayable( myMidlet );
        if ( displayable == null ) return null;
        if ( !( displayable instanceof Canvas ) ) return null;
        return (Canvas) displayable;
        }

    private Point getCanvasPoint( final Canvas aCanvas, final MouseEvent aMouseEvent )
        {
        final int relativeX = aMouseEvent.getX() - myTransformation.xOffset;
        final int relativeY = aMouseEvent.getY() - myTransformation.yOffset;
        final int scaledX = (int) ( relativeX / myTransformation.scale );
        final int scaledY = (int) ( relativeY / myTransformation.scale );
        myCanvasPosition.x = Math.max( 0, Math.min( aCanvas.getWidth(), scaledX ) );
        myCanvasPosition.y = Math.max( 0, Math.min( aCanvas.getHeight(), scaledY ) );
        return myCanvasPosition;
        }


    private final MIDlet myMidlet;

    private final DisplayTransformation myTransformation;

    private final Point myCanvasPosition = new Point();
    }
