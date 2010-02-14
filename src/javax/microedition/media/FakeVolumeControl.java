package javax.microedition.media;

import javax.microedition.media.control.VolumeControl;

public final class FakeVolumeControl implements VolumeControl
    {
    public final void setMute( final boolean aMutedFlag )
        {
        myMutedFlag = aMutedFlag;
        }

    public final boolean isMuted()
        {
        return myMutedFlag;
        }

    public final int setLevel( final int aValFrom0To100 )
        {
        return myLevel = aValFrom0To100;
        }

    public final int getLevel()
        {
        return myLevel;
        }

    private int myLevel;

    private boolean myMutedFlag;
    }
