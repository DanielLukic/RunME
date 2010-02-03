package net.intensicode.runme;

import net.intensicode.runme.util.*;

import javax.microedition.io.ConnectorImpl;
import javax.microedition.midlet.MIDlet;
import java.awt.Dimension;
import java.util.Arrays;

public final class MIDletLauncher
    {
    public static void main( final String[] aArguments ) throws Throwable
        {
        try
            {
            final CommandlineOptions options = new CommandlineOptions( aArguments );

            final MIDletLauncherConfiguration configuration = new MIDletLauncherConfiguration( options );
            configuration.loadRunMePropertiesIfAvailable();

            final MIDletLauncher launcher = new MIDletLauncher();
            launcher.start( configuration );
            }
        catch ( final Exception e )
            {
            e.printStackTrace();
            System.exit( 10 );
            }
        }

    private void start( final MIDletLauncherConfiguration aConfiguration ) throws Exception
        {
        final Dimension targetCanvasSize = aConfiguration.determineTargetSizeFromOptions();

        final String midletClassName = aConfiguration.determineMidletClassName();
        final ConnectorImpl connectorClassName = aConfiguration.createConnectorImplementationClass();
        final String[] resources = aConfiguration.determineListOfResourcesFolders();

        LOG.debug( "Target canvas size: {}", targetCanvasSize );
        LOG.debug( "Connector class: {}", connectorClassName );
        LOG.debug( "MIDlet class: {}", midletClassName );
        LOG.debug( "Resources: {}", Arrays.toString( resources ) );

        // Weird bug here. The MIDlet must not be created before the frame has been constructed.
        // Otherwise the Image#graphicsConfiguration is not initialized.
        final Class midletClass = loadMidletClass( midletClassName, resources );
        final MIDlet midlet = createMidlet( midletClass );

        final SystemContext context = new SystemContext();
        final DisplayTransformation transformation = new DisplayTransformation();
        final MIDletDisplay display = new MIDletDisplay( midlet );
        final MIDletDisplayBuffer buffer = new MIDletDisplayBuffer( context, targetCanvasSize );
        final MIDletContainer container = new MIDletContainer( midlet );
        final MIDletKeyHandler keyHandler = new MIDletKeyHandler( midlet );
        final MIDletPointerHandler pointerHandler = new MIDletPointerHandler( midlet, transformation );
        final MIDletVisibilityHandler visibilityHandler = new MIDletVisibilityHandler( midlet );
        final MIDletFrame frame = new MIDletFrame( context );
        final FullScreenManager fullScreenManager = new FullScreenManager( context );
        final FullScreenHandler fullScreenHandler = new FullScreenHandler( fullScreenManager );

        context.midlet = midlet;
        context.displayBuffer = buffer;
        context.displayContext = frame;
        context.displayTransformation = transformation;

        javax.microedition.io.Connector.implementationClass = connectorClassName;
        javax.microedition.lcdui.Image.theDisplayContext = frame;
        javax.microedition.lcdui.Displayable.displaySize = targetCanvasSize;
        javax.microedition.lcdui.Displayable.displayBuffer = buffer;
        javax.microedition.lcdui.Font.displaySize = targetCanvasSize;

        fullScreenManager.setFullScreenMode( false );

        frame.addKeyListener( keyHandler );
        frame.addKeyListener( fullScreenHandler );
        frame.addMouseListener( pointerHandler );
        frame.addMouseMotionListener( pointerHandler );
        frame.addFocusListener( visibilityHandler );

        container.start();
        }

    private static Class loadMidletClass( final String aClassName, final String[] aResources ) throws Exception
        {
        // The ResourcesClassLoader does not work very well in the WebStart version. This is why I do this:
        if ( aResources.length == 0 ) return Class.forName( aClassName );

        // Hack to make the 'resources' pathes work with the MIDlet class based resource loading.
        // By forcing the MIDlet class to be loaded by our ClassLoader, it will use this loader
        // for getResource.. calls. This way we can prepend additional resource folders.
        final ResourcesClassLoader loader = new ResourcesClassLoader( aResources );
        loader.restrictClassLoadingTo( aClassName );
        return loader.loadClass( aClassName );
        }

    private static MIDlet createMidlet( final Class aMIDletClass )
        {
        try
            {
            return (MIDlet) aMIDletClass.newInstance();
            }
        catch ( final Exception e )
            {
            throw new RuntimeException( "failed creating midlet instance", e );
            }
        }


    private static final Log LOG = Log.create();
    }
