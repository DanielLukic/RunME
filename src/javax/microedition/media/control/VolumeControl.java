package javax.microedition.media.control;

import javax.microedition.media.Control;

public interface VolumeControl extends Control
    {
    void setMute( boolean aMutedFlag );

    boolean isMuted();

    int setLevel( int aValFrom0To100 );

    int getLevel();
    }
