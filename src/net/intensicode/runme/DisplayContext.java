package net.intensicode.runme;

import java.awt.*;

public interface DisplayContext
    {
    boolean isFullScreen();

    GraphicsDevice getGraphicsDevice();

    GraphicsConfiguration getGraphicsConfiguration();

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
