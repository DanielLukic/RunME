package net.intensicode.runme;

import net.intensicode.runme.chooser.MIDletChooserFrame;

import java.io.File;



/**
 * TODO: Describe this!
 */
public final class MIDletChooser
    {
    public static void main( final String[] aArguments ) throws Throwable
        {
        final String folderPath = aArguments.length > 0 ? aArguments[ 0 ] : "release";
        final File folder = new File( folderPath );
        if ( !folder.exists() || !folder.isDirectory() )
            {
            final String msg = String.format( "Invalid folder path: %s", folderPath );
            throw new IllegalArgumentException( msg );
            }

        final MIDletChooserFrame frame = new MIDletChooserFrame();

        final File[] files = folder.listFiles();
        for ( final File file : files )
            {
            if ( !file.getName().endsWith( ".jar" ) ) continue;
            frame.addMIDlet( file );
            }

        frame.setVisible( true );
        }
    }
