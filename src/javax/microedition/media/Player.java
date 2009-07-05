/************************************************************************/
/* {{PROJECT_NAME}}             {{COMPANY}}             {{DATE_CREATE}} */
/************************************************************************/

package javax.microedition.media;

/**
 * TODO: Describe this!
 */
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

    void close() throws MediaException;

    void deallocate() throws MediaException;

    int getState();

    void addPlayerListener( PlayerListener aPlayerListener );

    void removePlayerListener( PlayerListener aPlayerListener );
    }
