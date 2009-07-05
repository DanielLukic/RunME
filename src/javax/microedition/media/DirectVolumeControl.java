/************************************************************************/
/* {{PROJECT_NAME}}             {{COMPANY}}             {{DATE_CREATE}} */
/************************************************************************/

package javax.microedition.media;

import javax.microedition.media.control.VolumeControl;
import javax.sound.sampled.FloatControl;



/**
 * TODO: Describe this!
 */
final class DirectVolumeControl implements VolumeControl
    {
    public DirectVolumeControl( final FloatControl aFloatControl )
        {
        myFloatControl = aFloatControl;
        }

    // From VolumeControl

    public final void setMute( final boolean aMutedFlag )
        {
        throw new RuntimeException( "NYI" );
        }

    public final boolean isMuted()
        {
        throw new RuntimeException( "NYI" );
        }

    public final int setLevel( final int aValFrom0To100 )
        {
        final float delta = myFloatControl.getMaximum() - myFloatControl.getMinimum();
        final float value = delta / 2 * aValFrom0To100 / 100;
        myFloatControl.setValue( myFloatControl.getMinimum() + value + delta / 2 );
        return aValFrom0To100;
        }

    public final int getLevel()
        {
        throw new RuntimeException( "NYI" );
        }

    private final FloatControl myFloatControl;
    }
