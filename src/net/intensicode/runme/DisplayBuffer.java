package net.intensicode.runme;

public interface DisplayBuffer
    {
    int width();

    int height();

    javax.microedition.lcdui.Graphics beginFrame();

    void endFrame();

    void renderInto( java.awt.Graphics aGraphics, int aX, int aY, final int aWidth, final int aHeight );
    }
