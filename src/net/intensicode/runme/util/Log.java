package net.intensicode.runme.util;

/**
 * TODO: Describe this!
 */
public final class Log
{
    public static final Log create()
    {
        return new Log();
    }

    public final void debug( final String aMessage, final Object...aParameters )
    {
        log( DEBUG, aMessage, aParameters );
    }

    // Implementation

    private Log()
    {
    }

    private final void log( final String aKind, final String aMessage, final Object[] aParameters )
    {
        final StringBuilder message = new StringBuilder();
        message.append( "[" );
        message.append( aKind );
        message.append( "] " );
        message.append( aMessage );

        int paramIndex = 0;
        while ( paramIndex < aParameters.length )
        {
            final int insertPos = message.indexOf( "{}" );
            if ( insertPos == -1 ) break;

            message.delete( insertPos, insertPos + 2 );
            message.insert( insertPos, aParameters[ paramIndex++ ] );
        }

        System.out.println( message );
    }

    private static final String DEBUG = "DEBUG";
}
