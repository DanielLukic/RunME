package net.intensicode.intensitris;

import java.io.*;

import javax.microedition.io.Connection;
import javax.microedition.io.ConnectorImpl;



/**
 * TODO: Describe this!
 */
public final class LocalConnector implements ConnectorImpl
{
    public final Connection open( final String aURL ) throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final Connection open( final String aURL, final int aMode ) throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final Connection open( final String aURL, final int aMode, final boolean aTimeOut ) throws IOException
    {
        return new LocalConnection( aURL );
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
}
