package javax.microedition.media;

import net.intensicode.runme.util.Log;

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
        return new String[]{ "audio/mid", "audio/x-wav", "audio/mpeg" };
        }

    public static String[] getSupportedProtocols( final String aContentType )
        {
        return new String[]{ "http", "file" };
        }

    public static Player createPlayer( final InputStream aInputStream, final String aContentType ) throws MediaException
        {
        if ( aContentType.contains( "audio/mid" ) ) return new MidiPlayer( aInputStream, aContentType );
        if ( aContentType.equals( "audio/x-wav" ) ) return new ClipPlayer( aInputStream );
        if ( aContentType.equals( "audio/mpeg" ) ) return new ClipPlayer( aInputStream );
        if ( aContentType.equals( "audio/ogg" ) ) return OggPlayer.create( aInputStream );
        throw new RuntimeException( "NYI" );
        }
    }
