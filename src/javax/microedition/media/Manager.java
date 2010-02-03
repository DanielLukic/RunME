package javax.microedition.media;

import java.io.InputStream;

public final class Manager
    {
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
        if ( aContentType.equals( "audio/x-wav" ) ) return new DirectPlayer( aInputStream );
        if ( aContentType.equals( "audio/mpeg" ) ) return new DirectPlayer( aInputStream );
        throw new RuntimeException( "NYI" );
        }
    }
