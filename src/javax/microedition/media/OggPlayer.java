package javax.microedition.media;

import java.io.*;

final class OggPlayer
    {
    static Player create( final InputStream aInputStream ) throws MediaException
        {
        try
            {
            final ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
            final FormatInfo info = OggDecoder.decode( aInputStream, bufferStream, false );
            final byte[] buffer = bufferStream.toByteArray();
            final ByteArrayInputStream pcmStream = new ByteArrayInputStream( buffer );
            return new RawPcmPlayer( pcmStream, info );
            }
        catch ( final IOException e )
            {
            throw new MediaException( e );
            }
        }
    }
