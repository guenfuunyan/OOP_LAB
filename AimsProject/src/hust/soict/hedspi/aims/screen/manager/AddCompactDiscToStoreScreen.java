package hust.soict.hedspi.aims.screen.manager;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Track;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
	private JTextField tfTitle;
	private JTextField tfCategory;
	private JTextField tfArtist;
	private JTextField tfCost;
	private JTextArea taTracks;
	private List<Track> tracks = new ArrayList<Track>();
	
	public AddCompactDiscToStoreScreen(Store store) {
		super(store);
		
		Container cp = getContentPane();
		cp.add(createCenter(), BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	protected JPanel createCenter() {
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		
		JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
		
		formPanel.add(new JLabel("Title: "));
		tfTitle = new JTextField();
		formPanel.add(tfTitle);
		
		
		formPanel.add(new JLabel("Category: "));
		tfCategory = new JTextField();
		formPanel.add(tfCategory);
		
		formPanel.add(new JLabel("Artist: "));
		tfArtist = new JTextField();
		formPanel.add(tfArtist);
		
		formPanel.add(new JLabel("Cost: "));
		tfCost = new JTextField();
		formPanel.add(tfCost);
		
		center.add(formPanel, BorderLayout.NORTH);
		
		JPanel tracksPanel = new JPanel(new BorderLayout());
		tracksPanel.setBorder(BorderFactory.createTitledBorder("Tracks"));
		
		taTracks = new JTextArea(5, 20);
		taTracks.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(taTracks);
		tracksPanel.add(scrollPane, BorderLayout.CENTER);
		
		JLabel instructions = new JLabel("Enter tracks in format: 'Title:Length' (one per line)\n" + "Example: Song 1:180 (180 seconds)");
		tracksPanel.add(instructions, BorderLayout.NORTH);
		
		center.add(tracksPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JButton btnAdd = new JButton("Add CD");
		btnAdd.addActionListener(new ButtonListener());
		buttonPanel.add(btnAdd);
		
		center.add(buttonPanel, BorderLayout.SOUTH);
		
		return center;
	}
	
	private void parseTrack() {
		tracks.clear();
		String[] lines = taTracks.getText().split("\n");
		
		for (String line : lines) {
			try {
				String[] parts = line.split(":");
				if (parts.length == 2) {
					String title = parts[0].trim();
					int length = Integer.parseInt(parts[1].trim());
					tracks.add(new Track(title, length));
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Invalid track length format in line: " + line, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		JOptionPane.showMessageDialog(this, "Parsed " + tracks.size() + " tracks successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				String title = tfTitle.getText();
                String category = tfCategory.getText();
                String artist = tfArtist.getText();
                float cost = Float.parseFloat(tfCost.getText());
                
                if (tracks.isEmpty()) {
                    JOptionPane.showMessageDialog(null, 
                        "Please add at least one track!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                
                CompactDisc cd = new CompactDisc(title, category, artist, cost);
                for (Track track : tracks) {
                	cd.addTrack(track);
                }
                
                store.addMedia(cd);
                
                JOptionPane.showMessageDialog(null, "CD added successfully with " + tracks.size() + " tracks!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new StoreManagerScreen(store);
                dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input format. Please check cost value.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
