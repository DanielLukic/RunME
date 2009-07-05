package net.intensicode.runme.chooser;

import net.intensicode.runme.util.Log;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;

final class LaunchAction extends AbstractAction implements ListSelectionListener
    {
    public LaunchAction( final LaunchContext aContext )
        {
        putValue( Action.NAME, "Launch" );
        putValue( Action.MNEMONIC_KEY, KeyEvent.VK_L );
        putValue( Action.SHORT_DESCRIPTION, "Launch selected MIDlet" );
        setEnabled( false );

        myContext = aContext;
        }

    // From ListSelectionListener

    public final void valueChanged( final ListSelectionEvent aEvent )
        {
        if ( aEvent.getValueIsAdjusting() )
            {
            setEnabled( false );
            }
        else
            {
            setEnabled( aEvent.getFirstIndex() >= 0 );
            }
        }

    // From Action

    public final void actionPerformed( final ActionEvent aEvent )
        {
        final MIDletEntry entry = myContext.getSelectedEntry();
        if ( entry == null ) return;

        try
            {
            final String jarPath = entry.getPathName();
            final String midletClass = extractMIDletClass( jarPath );

            LOG.debug( "Launching {} in {}", midletClass, jarPath );

            final StringBuilder builder = new StringBuilder();
            builder.append( "java -cp " );
            builder.append( jarPath );
            builder.append( ":build/classes" );
            builder.append( ":release/RunME.jar" );
            builder.append( " net.intensicode.runme.MIDletLauncher " );
            builder.append( " midlet " );
            builder.append( midletClass );

            final Process process = Runtime.getRuntime().exec( builder.toString() );
            logOutput( process.getInputStream() );
            logOutput( process.getErrorStream() );

            myContext.disposeChooser();

            Thread.sleep( 1000 );

            System.exit( 0 );
            }
        catch ( final Throwable aThrowable )
            {
            aThrowable.printStackTrace();
            LOG.debug( "Failed launching MIDlet: {}", aThrowable );
            }
        }

    // Implementation

    private String extractMIDletClass( final String aJarPath ) throws IOException
        {
        final String jadPath = aJarPath.replace( ".jar", ".jad" );
        final BufferedReader reader = new BufferedReader( new FileReader( jadPath ) );
        while ( true )
            {
            final String line = reader.readLine();
            if ( line == null ) throw new EOFException();
            if ( line.startsWith( "MIDlet-1" ) )
                {
                final int lastCommadIndex = line.lastIndexOf( ',' );
                return line.substring( lastCommadIndex + 1 );
                }
            }
        }

    private static void logOutput( final InputStream aStream )
        {
        final ProcessOutputLogger logger = new ProcessOutputLogger( aStream );
        final Thread thread = new Thread( logger );
        //thread.setDaemon( true );
        thread.start();
        }



    private final LaunchContext myContext;

    private static final Log LOG = Log.create();
    }
