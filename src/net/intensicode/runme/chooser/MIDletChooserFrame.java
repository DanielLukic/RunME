package net.intensicode.runme.chooser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class MIDletChooserFrame extends JFrame implements LaunchContext
    {
    public MIDletChooserFrame()
        {
        setTitle( "RunME MIDlet Chooser - Intensicode / The.Berlin.Factor - www.intensicode.net" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );

        final LaunchAction launchAction = new LaunchAction( this );
        final QuitAction quitAction = new QuitAction();

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout( new BorderLayout() );

        final JButton launchButton = new JButton( launchAction );
        buttonPanel.add( BorderLayout.WEST, launchButton );
        buttonPanel.add( BorderLayout.EAST, new JButton( quitAction ) );

        getRootPane().setDefaultButton( launchButton );

        final Container container = getContentPane();
        container.setLayout( new BorderLayout() );

        final JScrollPane scrollPane = new JScrollPane( myMIDletList );
        container.add( BorderLayout.CENTER, scrollPane );
        container.add( BorderLayout.SOUTH, buttonPanel );

        myMIDletList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        myMIDletList.addListSelectionListener( launchAction );

        pack();
        setSize( getWidth() * 3 / 2, getHeight() * 3 / 2 );
        }

    public final void addMIDlet( final File aFile )
        {
        myMIDlets.add( new MIDletEntry( aFile ) );

        Collections.sort( myMIDlets, new Comparator<MIDletEntry>()
        {
        public int compare( final MIDletEntry o1, final MIDletEntry o2 )
            {
            return o1.getPathName().compareTo( o2.getPathName() );
            }
        } );

        myMIDletList.updateUI();

        if ( myMIDlets.size() == 1 ) myMIDletList.setSelectedIndex( 0 );
        }

    // From LaunchContext

    public final MIDletEntry getSelectedEntry()
        {
        final int index = myMIDletList.getSelectedIndex();
        if ( index < 0 || index >= myListModel.getSize() ) return null;
        return myMIDlets.get( index );
        }

    public final void disposeChooser()
        {
        setVisible( false );
        dispose();
        }



    private final ArrayList<MIDletEntry> myMIDlets = new ArrayList<MIDletEntry>();

    private final MIDletChooserListModel myListModel = new MIDletChooserListModel( myMIDlets );

    private final JList myMIDletList = new JList( myListModel );
    }
