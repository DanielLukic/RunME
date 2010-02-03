package net.intensicode.runme;

import net.intensicode.runme.util.*;

import javax.microedition.io.ConnectorImpl;
import java.awt.*;
import java.io.InputStream;
import java.util.Properties;

public final class MIDletLauncherConfiguration
    {
    public MIDletLauncherConfiguration( final CommandlineOptions aOptions )
        {
        myOptions = aOptions;
        }

    public final void loadRunMePropertiesIfAvailable()
        {
        try
            {
            final InputStream stream = MIDletLauncher.class.getResourceAsStream( RUNME_PROPERTIES_RESOURCE_PATH );
            if ( stream == null ) return;
            myProperties.load( stream );
            stream.close();
            }
        catch ( final Exception e )
            {
            LOG.debug( "Failed loading runme.properties. Ignored. Using defaults." );
            }
        }

    public final Dimension determineTargetSizeFromOptions()
        {
        final Dimension sizeFromProperties = getSizeFromPropertiesOrDefaultSize();
        final int width = myOptions.getInteger( ARGUMENT_WIDTH, sizeFromProperties.width );
        final int height = myOptions.getInteger( ARGUMENT_HEIGHT, sizeFromProperties.height );
        return new Dimension( width, height );
        }

    public final String determineMidletClassName()
        {
        final String midletClassNameFromProperties = myProperties.getProperty( ARGUMENT_MIDLET );
        return myOptions.getString( ARGUMENT_MIDLET, midletClassNameFromProperties );
        }

    public final ConnectorImpl createConnectorImplementationClass() throws Exception
        {
        final String connectorClassName = determineConnectorClassName();
        return (ConnectorImpl) Class.forName( connectorClassName ).newInstance();
        }

    public final String[] determineListOfResourcesFolders()
        {
        return myOptions.getList( ARGUMENT_RESOURCES, EMPTY_LIST_OF_RESOURCES_FOLDERS );
        }

    public final GraphicsConfiguration determineGraphicsConfiguration()
        {
        final GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice device = environment.getDefaultScreenDevice();
        return device.getDefaultConfiguration();
        }

    // Implementation

    private Dimension getSizeFromPropertiesOrDefaultSize()
        {
        final int configWidth = getInt( myProperties, ARGUMENT_WIDTH, DEFAULT_WIDTH_IN_PIXELS );
        final int configHeight = getInt( myProperties, ARGUMENT_HEIGHT, DEFAULT_HEIGHT_IN_PIXELS );
        return new Dimension( configWidth, configHeight );
        }

    private String determineConnectorClassName()
        {
        return myOptions.getString( ARGUMENT_CONNECTOR, DEFAULT_CONNECTOR_CLASS );
        }

    private static int getInt( final Properties aProperties, final String aName, final int aDefault )
        {
        try
            {
            final String value = aProperties.getProperty( aName );
            if ( value == null ) return aDefault;
            return Integer.parseInt( value );
            }
        catch ( final Throwable t )
            {
            return aDefault;
            }
        }


    private final CommandlineOptions myOptions;

    private final Properties myProperties = new Properties();

    private static final Log LOG = Log.create();

    private static final int DEFAULT_WIDTH_IN_PIXELS = 240;

    private static final int DEFAULT_HEIGHT_IN_PIXELS = 320;

    private static final String ARGUMENT_WIDTH = "width";

    private static final String ARGUMENT_HEIGHT = "height";

    private static final String ARGUMENT_MIDLET = "midlet";

    private static final String ARGUMENT_CONNECTOR = "connector";

    private static final String ARGUMENT_RESOURCES = "resources";

    private static final String RUNME_PROPERTIES_RESOURCE_PATH = "/runme.properties";

    private static final String DEFAULT_CONNECTOR_CLASS = "javax.microedition.io.EmulatingConnector";

    private static final String[] EMPTY_LIST_OF_RESOURCES_FOLDERS = new String[0];
    }
