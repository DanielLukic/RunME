package javax.microedition.io;

/**
 * TODO: Describe this!
 */
public interface ContentConnection extends StreamConnection
{
    String getType();

    String getEncoding();

    long getLength();
}