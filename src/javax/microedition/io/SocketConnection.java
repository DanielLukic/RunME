package javax.microedition.io;

public interface SocketConnection extends StreamConnection
    {

    byte DELAY = 0;
    byte LINGER = 1;
    byte KEEPALIVE = 2;
    byte RCVBUF = 3;
    byte SNDBUF = 4;

    void setSocketOption( byte b, int i ) throws java.lang.IllegalArgumentException, java.io.IOException;

    int getSocketOption( byte b ) throws java.lang.IllegalArgumentException, java.io.IOException;

    java.lang.String getLocalAddress() throws java.io.IOException;

    int getLocalPort() throws java.io.IOException;

    java.lang.String getAddress() throws java.io.IOException;

    int getPort() throws java.io.IOException;
    }
