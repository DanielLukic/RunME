/************************************************************************/
/* {{PROJECT_NAME}}             {{COMPANY}}             {{DATE_CREATE}} */
/************************************************************************/

package javax.microedition.media;

import java.io.InputStream;



/**
 * TODO: Describe this!
 */
public final class Manager
    {
    public static final String[] getSupportedContentTypes( final String aProtocol )
        {
        return new String[]{"audio/x-wav", "audio/mp3"};
        }

    public static final String[] getSupportedProtocols( final String aContentType )
        {
        return new String[]{"http", "file"};
        }

    public static Player createPlayer( final InputStream aInputStream, final String aContentType ) throws MediaException
        {
        if ( aContentType.contains( "midi" ) ) return new MidiPlayer( aInputStream, aContentType );
        else if ( aContentType.contains( "wav" ) ) return new DirectPlayer( aInputStream );
        else throw new RuntimeException( "NYI" );
        }
    }
