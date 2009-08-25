package javax.microedition.lcdui;

import java.awt.event.KeyEvent;



public abstract class Canvas extends Displayable
{
    public static final int DOWN = 6;

    public static final int LEFT = 2;

    public static final int RIGHT = 5;

    public static final int UP = 1;

    public static final int FIRE = 8;

    public static final int GAME_A = 9;

    public static final int GAME_B = 10;

    public static final int GAME_C = 11;

    public static final int GAME_D = 12;

    public static final int KEY_NUM0 = 48;

    public static final int KEY_NUM1 = 49;

    public static final int KEY_NUM2 = 50;

    public static final int KEY_NUM3 = 51;

    public static final int KEY_NUM4 = 52;

    public static final int KEY_NUM5 = 53;

    public static final int KEY_NUM6 = 54;

    public static final int KEY_NUM7 = 55;

    public static final int KEY_NUM8 = 56;

    public static final int KEY_NUM9 = 57;

    public static final int KEY_POUND = '#';

    public static final int KEY_STAR = '*';



    public final int getGameAction( final int aCode )
    {
        switch ( aCode )
        {
            case KeyEvent.VK_CONTROL:
                return FIRE;
            case KeyEvent.VK_SPACE:
                return GAME_A;
            case KeyEvent.VK_ENTER:
                return GAME_B;
            case KeyEvent.VK_Q:
                return GAME_C;
            case KeyEvent.VK_E:
                return GAME_D;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                return LEFT;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                return UP;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                return RIGHT;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                return DOWN;
            default:
                return 0;
        }
    }
}
