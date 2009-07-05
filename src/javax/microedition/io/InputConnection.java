package javax.microedition.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;



/**
 * TODO: Describe this!
 */
public interface InputConnection extends Connection
{
    InputStream openInputStream() throws IOException;

    DataInputStream openDataInputStream() throws IOException;
}