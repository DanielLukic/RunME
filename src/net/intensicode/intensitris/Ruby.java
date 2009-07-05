package net.intensicode.intensitris;

import java.io.*;



/**
 * TODO: Describe this!
 */
final class Ruby
{
    final Process execute( final String aScript, final String... aArguments ) throws IOException
    {
        final String[] commandLine = new String[ aArguments.length + 2 ];
        System.arraycopy( aArguments, 0, commandLine, 2, aArguments.length );

        commandLine[ 0 ] = myRubyExe;
        commandLine[ 1 ] = aScript;

        return Runtime.getRuntime().exec( commandLine, new String[0], myWorkingDirectory );
    }

    static final void endInput( final Process aProcess ) throws IOException
    {
        aProcess.getOutputStream().close();
    }

    static final String readResponse( final Process aProcess ) throws IOException
    {
        final StringBuilder builder = new StringBuilder();

        final InputStreamReader input = new InputStreamReader( aProcess.getInputStream() );
        final BufferedReader reader = new BufferedReader( input );
        while ( true )
        {
            final String line = reader.readLine();
            if ( line == null ) break;
            builder.append( line );
            builder.append( "\n" );
        }
        reader.close();

        return builder.toString();
    }

    static final String readErrorResponse( final Process aProcess ) throws IOException
    {
        final StringBuilder builder = new StringBuilder();

        final InputStreamReader input = new InputStreamReader( aProcess.getErrorStream() );
        final BufferedReader reader = new BufferedReader( input );
        while ( true )
        {
            final String line = reader.readLine();
            if ( line == null ) break;
            builder.append( line );
            builder.append( "\n" );
        }
        reader.close();

        return builder.toString();
    }

    static final void sendInput( final Process aProcess, final String aInput ) throws IOException
    {
        final OutputStream output = aProcess.getOutputStream();
        output.write( aInput.getBytes() );
        output.close();
    }



    private final String myRubyExe = "env/ruby/bin/ruby.exe";

    private final File myWorkingDirectory = new File( "server" );
}
