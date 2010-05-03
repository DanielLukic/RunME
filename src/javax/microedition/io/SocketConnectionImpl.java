package javax.microedition.io;

import net.intensicode.runme.util.Log;

import java.io.*;
import java.net.*;

public class SocketConnectionImpl implements SocketConnection
    {
    public SocketConnectionImpl( final String aURL, final int aMode, final boolean aTimeOut )
        {
        myHost = extractHost( aURL );
        myPort = extractPort( aURL );
        Log.debug( "SocketConnectionImpl: host={} port={}", myHost, myPort );
        }

    private String myHost;
    private int myPort;

    private String extractHost( final String aURL )
        {
        final int endOfProtocol = aURL.indexOf( "://" ) + 3;
        final int startOfPort = aURL.indexOf( ":", endOfProtocol );
        return aURL.substring( endOfProtocol, startOfPort );
        }

    private int extractPort( final String aURL )
        {
        final int endOfProtocol = aURL.indexOf( "://" ) + 3;
        final int startOfPort = aURL.indexOf( ":", endOfProtocol ) + 1;
        final String portSpec = aURL.substring( startOfPort );
        return Integer.parseInt( portSpec );
        }

    // From SocketConnection

    public void setSocketOption( final byte b, final int i ) throws IllegalArgumentException, IOException
        {
        throw new RuntimeException( "nyi" );
        }

    public int getSocketOption( final byte b ) throws IllegalArgumentException, IOException
        {
        throw new RuntimeException( "nyi" );
        }

    public String getLocalAddress() throws IOException
        {
        throw new RuntimeException( "nyi" );
        }

    public int getLocalPort() throws IOException
        {
        throw new RuntimeException( "nyi" );
        }

    public String getAddress() throws IOException
        {
        throw new RuntimeException( "nyi" );
        }

    public int getPort() throws IOException
        {
        throw new RuntimeException( "nyi" );
        }

    public InputStream openInputStream() throws IOException
        {
        createSocketIfNecessary();
        return mySocket.getInputStream();
        }

    private void createSocketIfNecessary() throws IOException
        {
        if ( mySocket != null ) return;
        mySocket = new Socket( myHost, myPort );
        }

    public DataInputStream openDataInputStream() throws IOException
        {
        throw new RuntimeException( "nyi" );
        }

    public OutputStream openOutputStream() throws IOException
        {
        return mySocket.getOutputStream();
        }

    public DataOutputStream openDataOutputStream() throws IOException
        {
        throw new RuntimeException( "nyi" );
        }

    public void close() throws IOException
        {
        mySocket.close();
        }

    private Socket mySocket;
    }
