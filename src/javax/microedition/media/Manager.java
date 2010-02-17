package javax.microedition.media;

import net.intensicode.runme.util.Log;

import javax.microedition.media.control.VolumeControl;
import javax.sound.sampled.*;
import java.io.InputStream;

public final class Manager
    {
    static
        {
        for ( final Mixer.Info info : AudioSystem.getMixerInfo() )
            {
            Log.debug( "Mixer info: {}", info );
            }
        for ( final AudioFileFormat.Type type : AudioSystem.getAudioFileTypes() )
            {
            Log.debug( "Audio type: {}", type );
            }
        }

    public static String[] getSupportedContentTypes( final String aProtocol )
        {
        return new String[]{ "audio/mid", "audio/x-wav", "audio/mpeg", "audio/ogg", "audio/mod", "audio/xm" };
        }

    public static String[] getSupportedProtocols( final String aContentType )
        {
        return new String[]{ "http", "file" };
        }

    public static Player createPlayer( final InputStream aInputStream, final String aContentType ) throws MediaException
        {
        if ( aContentType.contains( "audio/mid" ) ) return new MidiPlayer( aInputStream, aContentType );
        if ( aContentType.equals( "audio/ogg" ) ) return OggPlayer.create( aInputStream );
        if ( aContentType.equals( "audio/mod" ) ) return new MuxmPlayer( aInputStream );
        if ( aContentType.equals( "audio/xm" ) ) return new MuxmPlayer( aInputStream );
        return new ClipPlayer( aInputStream );
        }

    public static VolumeControl findVolumeControl( final javax.sound.sampled.Control[] controls )
        {
        for ( final javax.sound.sampled.Control control : controls )
            {
            final String controlInfo = control.toString().toLowerCase();
            if ( controlInfo.contains( "gain" ) || controlInfo.contains( "volume" ) )
                {
                return new DirectVolumeControl( (FloatControl) control );
                }
            }
        return new FakeVolumeControl();
        }
    }
