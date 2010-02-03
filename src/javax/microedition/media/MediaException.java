package javax.microedition.media;

public final class MediaException extends Exception
    {
    public MediaException()
        {
        }

    public MediaException( final String aMessage )
        {
        super( aMessage );
        }

    public MediaException( final Throwable aCause )
        {
        super( aCause );
        }
    }
