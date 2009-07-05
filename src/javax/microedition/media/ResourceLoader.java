/************************************************************************/
/* {{PROJECT_NAME}}             {{COMPANY}}             {{DATE_CREATE}} */
/************************************************************************/

package javax.microedition.media;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;



/**
 * TODO: Describe this!
 */
final class ResourceLoader
{
    static final byte[] loadStream( final InputStream aStream ) throws IOException
    {
        if ( aStream == null ) throw new IOException();

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final byte[] buffer = new byte[4096];
        while ( true )
        {
            final int newBytes = aStream.read( buffer );
            if ( newBytes == -1 ) break;
            output.write( buffer, 0, newBytes );
        }
        aStream.close();
        output.close();

        return output.toByteArray();
    }
}