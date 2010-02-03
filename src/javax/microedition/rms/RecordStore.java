package javax.microedition.rms;

import java.io.*;
import java.util.HashMap;

public final class RecordStore
    {
    public static final void deleteRecordStore( final String aName )
        {
        final RecordStore store = new RecordStore( aName );
        delete( store.getStoreFile() );
        }

    public static final RecordStore openRecordStore( final String aName, final boolean aCreateFlag )
        {
        synchronized ( theOpenStores )
            {
            if ( theOpenStores.containsKey( aName ) == false )
                {
                theOpenStores.put( aName, new RecordStore( aName ) );
                }

            final RecordStore recordStore = theOpenStores.get( aName );
            recordStore.openCount++;

            if ( aCreateFlag == false && recordStore.getStoreFile().exists() == false )
                {
                recordStore.closeRecordStore();
                return null;
                }

            return recordStore;
            }
        }

    public final synchronized byte[] getRecord( final int aRecordID ) throws RecordStoreException
        {
        try
            {
            final File recordFile = getRecordFile( aRecordID );
            final RandomAccessFile file = new RandomAccessFile( recordFile, "r" );
            final long fileLength = file.length();
            final byte[] data = new byte[(int) fileLength];
            file.readFully( data );
            return data;
            }
        catch ( final FileNotFoundException e )
            {
            return null;
            }
        catch ( final IOException e )
            {
            throw new RecordStoreException( e );
            }
        }

    public final synchronized void setRecord( final int aRecordID, final byte[] aData, final int aOffset, final int aLength ) throws RecordStoreException
        {
        try
            {
            final File recordFile = getRecordFile( aRecordID );
            final RandomAccessFile file = new RandomAccessFile( recordFile, "rw" );
            file.setLength( aLength );
            file.write( aData, aOffset, aLength );
            }
        catch ( final IOException e )
            {
            throw new RecordStoreException( e );
            }
        }

    public final synchronized void closeRecordStore()
        {
        openCount--;
        if ( openCount <= 0 ) theOpenStores.remove( this );
        }

    // Implementation

    private RecordStore( final String aName )
        {
        myName = aName;
        }

    private final File getStoreFile()
        {
        return new File( theStoragePrefix, myName );
        }

    private final File getRecordFile( final int aRecordID )
        {
        final StringBuilder builder = new StringBuilder();
        builder.append( aRecordID );
        builder.append( theRecordSuffix );
        final String recordFileName = builder.toString();

        final File storageFolder = new File( theStoragePrefix, myName );
        storageFolder.mkdirs();
        return new File( storageFolder, recordFileName );
        }

    private static final void delete( final File aFileOrDirectory )
        {
        if ( aFileOrDirectory.isDirectory() )
            {
            for ( final File file : aFileOrDirectory.listFiles() )
                {
                delete( file );
                }
            }
        aFileOrDirectory.delete();
        }


    private int openCount = 0;

    private final String myName;

    private static final String theStoragePrefix = ".rms";

    private static final String theRecordSuffix = ".record";

    private static final HashMap<String, RecordStore> theOpenStores = new HashMap<String, RecordStore>();
    }
