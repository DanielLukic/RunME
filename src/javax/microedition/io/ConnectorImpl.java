package javax.microedition.io;

import java.io.*;



/**
 * TODO: Describe this!
 */
public interface ConnectorImpl
{
    Connection open( String aURL ) throws IOException;

    Connection open( String aURL, int aMode ) throws IOException;

    Connection open( String aURL, int aMode, boolean aTimeOut ) throws IOException;

    DataInputStream openDataInputStream( String aURL ) throws IOException;

    DataOutputStream openDataOutputStream( String aURL ) throws IOException;

    InputStream openInputStream( String aURL ) throws IOException;

    OutputStream openOutputStream( String aURL ) throws IOException;
}
