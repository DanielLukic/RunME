package javax.microedition.media;

public interface Player extends Controllable
    {
    int UNREALIZED = 100;

    int REALIZED = 200;

    int PREFETCHED = 300;

    int STARTED = 400;

    int CLOSED = 0;

    long TIME_UNKNOWN = -1;

    void setLoopCount( int aLoopCount );

    long setMediaTime( long aMediaTime ) throws MediaException;

    void realize() throws MediaException;

    void prefetch() throws MediaException;

    void start() throws MediaException;

    void stop() throws MediaException;

    void deallocate();

    void close();

    int getState();

    void addPlayerListener( PlayerListener aPlayerListener );

    void removePlayerListener( PlayerListener aPlayerListener );
    }
