package javax.microedition.lcdui.game;



final class Position
{
    public int x;

    public int y;



    public Position()
    {
    }

    public Position( int aX, int aY )
    {
        x = aX;
        y = aY;
    }

    public final void addFrom( final javax.microedition.lcdui.game.Position aPosition )
    {
        x += aPosition.x;
        y += aPosition.y;
    }

    public final void setTo( final javax.microedition.lcdui.game.Position aPosition )
    {
        x = aPosition.x;
        y = aPosition.y;
    }
}
