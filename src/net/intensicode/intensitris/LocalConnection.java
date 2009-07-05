package net.intensicode.intensitris;

import net.intensicode.runme.util.Log;

import java.io.*;

import javax.microedition.io.HttpConnection;



/**
 * TODO: Describe this!
 */
final class LocalConnection implements HttpConnection
{
    LocalConnection( final String aURL )
    {
        final int lastSlash = aURL.lastIndexOf( '/' );
        myScript = aURL.substring( lastSlash + 1 );

        //#if DEBUG
        Log.create().debug( "Local connection for script {}", myScript );
        //#endif
    }

    // From HttpConnection

    public final long getDate() throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final long getExpiration() throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final String getFile()
    {
        throw new RuntimeException( "NYI" );
    }

    public final String getHeaderField( final String aString ) throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final String getHeaderField( final int i ) throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final long getHeaderFieldDate( final String aString, final long l ) throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final int getHeaderFieldInt( final String aString, final int i ) throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final String getHeaderFieldKey( final int i ) throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final String getHost()
    {
        throw new RuntimeException( "NYI" );
    }

    public final long getLastModified() throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final int getPort()
    {
        throw new RuntimeException( "NYI" );
    }

    public final String getProtocol()
    {
        throw new RuntimeException( "NYI" );
    }

    public final String getQuery()
    {
        throw new RuntimeException( "NYI" );
    }

    public final String getRef()
    {
        throw new RuntimeException( "NYI" );
    }

    public final String getRequestMethod()
    {
        throw new RuntimeException( "NYI" );
    }

    public final String getRequestProperty( final String aString )
    {
        throw new RuntimeException( "NYI" );
    }

    public final int getResponseCode() throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final String getResponseMessage() throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    public final String getURL()
    {
        throw new RuntimeException( "NYI" );
    }

    public final void setRequestMethod( final String aMethodName ) throws IOException
    {
    }

    public final void setRequestProperty( final String aName, final String aValue ) throws IOException
    {
    }

    // From ContentConnection

    public final String getEncoding()
    {
        return null;
    }

    public final long getLength()
    {
        return 0;
    }

    public final String getType()
    {
        return null;
    }

    // From InputConnection

    public final DataInputStream openDataInputStream() throws IOException
    {
        initProcess();
        final InputStream input = myProcess.getInputStream();
        return new DataInputStream( input );
    }

    public final InputStream openInputStream() throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    // From OutputConnection

    public final DataOutputStream openDataOutputStream() throws IOException
    {
        initProcess();
        final OutputStream output = myProcess.getOutputStream();
        return new DataOutputStream( output );
    }

    public final OutputStream openOutputStream() throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    // From Connection

    public final void close() throws IOException
    {
        throw new RuntimeException( "NYI" );
    }

    // Implementation

    private final void initProcess() throws IOException
    {
        if ( myProcess != null ) return;
        myProcess = myRuby.execute( myScript, "-noheader" );
    }



    private String myScript;

    private Process myProcess;

    private final Ruby myRuby = new Ruby();
}
