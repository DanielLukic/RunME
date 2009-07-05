package net.intensicode.runme;

import net.intensicode.runme.util.Log;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public final class ResourcesClassLoader extends ClassLoader
    {
    public ResourcesClassLoader( final String[] aResources )
        {
        myResources = aResources;
        }

    public final void restrictClassLoadingTo( final String aClassName )
        {
        myClassName = aClassName;
        }

    // From ClassLoader

    public Class<?> loadClass( final String name ) throws ClassNotFoundException
        {
        if ( myClassName == null || myClassName.equals( name ) )
            {
            try
                {
                final InputStream classStream = getParent().getResourceAsStream( name.replace( '.', '/' ) + ".class" );
                final byte[] classData = loadStream( classStream );
                return defineClass( name, classData, 0, classData.length );
                }
            catch ( final Exception e )
                {
                LOG.debug( "Failed loading class {}: {}", name, e );
                }
            }
        return super.loadClass( name );
        }

    public InputStream getResourceAsStream( final String name )
        {
        try
            {
            final File check = findFile( name );
            if ( check != null ) return new FileInputStream( check );
            }
        catch ( final FileNotFoundException e )
            {
            // Ignore and try parent..
            }
        return getParent().getResourceAsStream( name );
        }

    public URL getResource( final String name )
        {
        try
            {
            final File check = findFile( name );
            if ( check != null ) return check.toURI().toURL();
            }
        catch ( final MalformedURLException e )
            {
            // Ignore and try parent..
            }
        return getParent().getResource( name );
        }

    // Implementation

    private File findFile( final String name )
        {
        for ( final String path : myResources )
            {
            final File check = new File( path, name );
            if ( check.exists() ) return check;
            }
        return null;
        }

    private byte[] loadStream( final InputStream aStream ) throws IOException
        {
        if ( aStream == null ) throw new IOException();

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final byte[] buffer = new byte[4096];
        while ( true )
            {
            final int newBytes = aStream.read( buffer );
            if ( newBytes == -1 ) break;
            output.write( buffer, 0, newBytes );
            }
        aStream.close();
        output.close();

        return output.toByteArray();
        }



    private String myClassName;

    private final String[] myResources;

    private static final Log LOG = Log.create();
    }
