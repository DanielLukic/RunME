package net.intensicode.runme.chooser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class ProcessOutputLogger implements Runnable
    {
    public ProcessOutputLogger( final InputStream aStream )
        {
        myStream = new BufferedReader( new InputStreamReader( aStream ) );
        }

    // From Runnable

    public final void run()
        {
        while ( true )
            {
            try
                {
                final String line = myStream.readLine();
                if ( line == null ) return;

                System.out.println( line );
                }
            catch ( final IOException e )
                {
                // Bail out..
                return;
                }
            }
        }

    private final BufferedReader myStream;
    }
