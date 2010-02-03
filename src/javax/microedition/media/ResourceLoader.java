package javax.microedition.media;

import java.io.*;

final class ResourceLoader
    {
    static byte[] loadStream( final InputStream aStream ) throws IOException
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
