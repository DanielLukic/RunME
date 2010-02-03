package net.intensicode.runme;

public interface DisplayContext extends GraphicsContext
    {
    boolean isFullScreen();

    void centerAndResizeTo( int aWidth, int aHeight );

    void showTitleAndBorder();

    void hideTitleAndBorder();

    void showAndFocus();

    void hideAndDispose();

    void makeFullScreen();

    void unmakeFullScreen();

    // From Component

    void repaint();
    }
