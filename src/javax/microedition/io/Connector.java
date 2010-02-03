package javax.microedition.io;

import java.io.*;



/**
 * TODO: Describe this!
 */
public final class Connector
{
    public static ConnectorImpl implementationClass = new EmulatingConnector();

    public static final int READ = 1;

    public static final int WRITE = 2;

    public static final int READ_WRITE = 3;



    public static final Connection open( final String aString ) throws IOException
    {
        return implementationClass.open( aString );
    }

    public static final Connection open( final String aString, final int aMode ) throws IOException
    {
        return implementationClass.open( aString, aMode );
    }

    public static final Connection open( final String aURL, final int aMode, final boolean aTimeOut ) throws IOException
    {
        return implementationClass.open( aURL, aMode, aTimeOut );
    }

    public static final DataInputStream openDataInputStream( String aURL ) throws IOException
    {
        return implementationClass.openDataInputStream( aURL );
    }

    public static final DataOutputStream openDataOutputStream( String aURL ) throws IOException
    {
        return implementationClass.openDataOutputStream( aURL );
    }

    public static final InputStream openInputStream( String aURL ) throws IOException
    {
        return implementationClass.openInputStream( aURL );
    }

    public static final OutputStream openOutputStream( String aURL ) throws IOException
    {
        return implementationClass.openOutputStream( aURL );
    }

    // Implementation

    private Connector()
    {
    }
}