package net.intensicode.runme.util;

import java.util.ArrayList;


public final class CommandlineOptions
    {
    public CommandlineOptions( final String[] aArguments )
        {
        myArguments = aArguments;
        }

    public final int getInteger( final String aOptionName, final int aDefaultValue )
        {
        final String value = extractValueFor( aOptionName );
        if ( value == null ) return aDefaultValue;
        return Integer.parseInt( value );
        }

    public final String getString( final String aOptionName, final String aDefaultValue )
        {
        final String value = extractValueFor( aOptionName );
        if ( value == null ) return aDefaultValue;
        return value;
        }

    public final String[] getList( final String aOptionName, final String[] aDefaultValue )
        {
        final ArrayList<String> result = new ArrayList<String>();
        while ( true )
            {
            final String value = extractValueFor( aOptionName );
            if ( value == null ) break;
            result.add( value );
            }
        if ( result.size() == 0 ) return aDefaultValue;
        return result.toArray( new String[result.size()] );
        }

    // Implementation

    private final String extractValueFor( final String aOptionName )
        {
        for ( int idx = 0; idx < myArguments.length; idx++ )
            {
            final String argument = myArguments[ idx ];
            if ( argument == null ) continue;

            // Check for "<name> <value>"
            if ( argument.equals( aOptionName ) )
                {
                if ( idx < myArguments.length - 1 )
                    {
                    final String value = myArguments[ idx + 1 ];
                    myArguments[ idx ] = myArguments[ idx + 1 ] = null;
                    return value;
                    }
                }
            // Check for "<name>=<value>"
            else if ( argument.startsWith( aOptionName ) )
                {
                final int assignment = argument.indexOf( '=' );
                if ( assignment != aOptionName.length() ) continue;
                final String value = argument.substring( assignment + 1 );
                myArguments[ idx ] = null;
                return value;
                }
            }
        return null;
        }

    private final String[] myArguments;
    }
