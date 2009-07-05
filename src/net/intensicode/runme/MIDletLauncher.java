package net.intensicode.runme;

import net.intensicode.runme.util.CommandlineOptions;
import net.intensicode.runme.util.Log;

import javax.microedition.io.Connector;
import javax.microedition.io.ConnectorImpl;
import javax.microedition.lcdui.Font;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;



/**
 * TODO: Describe this!
 */
public final class MIDletLauncher
    {
    public static void main( final String[] aArguments ) throws Throwable
        {
        try
            {
            start( aArguments );
            }
        catch ( final Throwable t )
            {
            t.printStackTrace();
            System.exit( 10 );
            }
        }

    private static void start( final String[] aArguments ) throws Throwable
        {
        final Properties properties = new Properties();
        try
            {
            final InputStream stream = MIDletLauncher.class.getResourceAsStream( "/runme.properties" );
            LOG.debug( "Properties: {}", stream );
            properties.load( stream );
            }
        catch ( final Exception e )
            {
            LOG.debug( "Failed loading /runme.properties: {}", e );
            }

        final int configWidth = getInt( properties, "width", 240 );
        final int configHeight = getInt( properties, "height", 320 );
        LOG.debug( "Properties size: {} x {}", configWidth, configHeight );

        final CommandlineOptions options = new CommandlineOptions( aArguments );
        final int width = options.getInteger( "width", configWidth );
        final int height = options.getInteger( "height", configHeight );
        final String midletClassName = options.getString( "midlet", properties.getProperty( "midlet" ) );
        final String connectorClass = options.getString( "connector", "javax.microedition.io.EmulatingConnector" );
        final String[] resources = options.getList( "resources", new String[0] );

        LOG.debug( "Canvas size: {} x {}", width, height );
        LOG.debug( "MIDlet class: {}", midletClassName );
        LOG.debug( "Connector class: {}", connectorClass );
        LOG.debug( "Resources: {}", Arrays.toString( resources ) );

        Font.midlet_display_width = width;
        Font.midlet_display_height = height;

        Connector.implementation = (ConnectorImpl) Class.forName( connectorClass ).newInstance();

        final Class midletClass = loadMIDletClass( midletClassName, resources );
        final MIDletFrame frame = new MIDletFrame( width, height );

        // Weird bug here. The MIDlet must not be created before the frame has been constructed.
        // Otherwise the Image#target_gc is not initialized.
        frame.start( MIDletContainer.createMIDlet( midletClass ) );
        }

    private static int getInt( final Properties aProperties, final String aName, final int aDefault )
        {
        final String value = aProperties.getProperty( aName );
        if ( value == null ) return aDefault;
        try
            {
            return Integer.parseInt( value );
            }
        catch ( final Throwable t )
            {
            LOG.debug( "Bad {}: {}", aName, value );
            LOG.debug( "Exception: {}", t );
            return aDefault;
            }
        }

    private static Class loadMIDletClass( final String aClassName, final String[] aResources ) throws Exception
        {
        if ( aResources.length == 0 ) return Class.forName( aClassName );

        // The ResourcesClassLoader does not work very well in the WebStart version..
        // This is why I use the check above..

        // Hack to make the 'resources' pathes work with the MIDlet class based resource loading.
        // By forcing the MIDlet class to be loaded by our ClassLoader, it will use this loader
        // for getResource.. calls. This way we can prepend additinal resource folders.
        final ResourcesClassLoader loader = new ResourcesClassLoader( aResources );
        loader.restrictClassLoadingTo( aClassName );
        return loader.loadClass( aClassName );
        }

    private static final Log LOG = Log.create();
    }
