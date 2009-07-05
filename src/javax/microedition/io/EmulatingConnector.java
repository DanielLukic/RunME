package javax.microedition.io;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;



/**
 * TODO: Describe this!
 */
public final class EmulatingConnector implements ConnectorImpl
{
    public final Connection open( final String aString ) throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final Connection open( final String aString, final int aMode ) throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final Connection open( final String aURL, final int aMode, final boolean aTimeOut ) throws IOException
    {
        final URL url = new URL( aURL );
        final HttpURLConnection connection = ( HttpURLConnection ) url.openConnection();
        connection.setConnectTimeout( CONNECT_TIME_OUT );
        connection.setDoInput( ( aMode & Connector.READ ) == Connector.READ );
        connection.setDoOutput( ( aMode & Connector.WRITE ) == Connector.WRITE );
        connection.setReadTimeout( READ_TIME_OUT );
        connection.setUseCaches( false );
        return new WrappedHttpConnection( connection );
    }

    public final DataInputStream openDataInputStream( final String aURL ) throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final DataOutputStream openDataOutputStream( final String aURL ) throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final InputStream openInputStream( final String aURL ) throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final OutputStream openOutputStream( final String aURL ) throws IOException
    {
        throw new RuntimeException( "NYI" );
    }



    private final int READ_TIME_OUT = 10000;

    private final int CONNECT_TIME_OUT = 10000;
}
