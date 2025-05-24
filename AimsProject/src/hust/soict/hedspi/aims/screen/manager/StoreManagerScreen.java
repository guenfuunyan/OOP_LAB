package hust.soict.hedspi.aims.screen.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;

public class StoreManagerScreen extends JFrame{
	private Store store;
	
	public StoreManagerScreen(Store store) {
		this.store = store;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("AIMS - Store Manager");
		setSize(1024, 768);
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(createNorth(), BorderLayout.NORTH);
		cp.add(createCenter(), BorderLayout.CENTER);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	JPanel createNorth() {
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar());
		north.add(createHeader());
		return north;
	}
	
	JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("Options");
		
		JMenuItem viewStore = new JMenuItem("View Store");
		viewStore.addActionListener(e -> refreshStoreDisplay());
		menu.add(viewStore);
		
		JMenu updateStoreMenu = new JMenu("Update Store");
		
		JMenuItem addBook = new JMenuItem("Add Book");
		addBook.addActionListener(e -> {
			new AddBookToStoreScreen(store);
			dispose();
		});
		updateStoreMenu.add(addBook);
		
		JMenuItem addCD = new JMenuItem("Add CD");
		addCD.addActionListener(e -> {
			new AddCompactDiscToStoreScreen(store);
		});
		updateStoreMenu.add(addCD);
		
		JMenuItem addDVD = new JMenuItem("Add DVD");
		addDVD.addActionListener(e -> {
			new AddDigitalVideoDiscToStoreScreen(store);
		});
		updateStoreMenu.add(addDVD);
		
		menu.add(updateStoreMenu);
		menuBar.add(menu);
		
		return menuBar;
	}
	
	JPanel createHeader() {
		JPanel header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		
		JLabel title = new JLabel("AIMS - Store Management");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
		title.setForeground(Color.CYAN);
		
		header.add(Box.createRigidArea(new Dimension(10, 10)));
		header.add(title);
		header.add(Box.createHorizontalGlue());
		
		JButton refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(e -> refreshStoreDisplay());
		header.add(refreshButton);
		header.add(Box.createRigidArea(new Dimension(10, 10)));
		
		return header;
	}
	
	JPanel createCenter() {
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(3, 3, 2, 2));
		center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		ArrayList<Media> mediaInStore = store.getItemsInStore();
		for (Media media : mediaInStore) {
			center.add(createMediaCell(media));
		}
		
		for (int i = mediaInStore.size(); i < 0; i++) {
			center.add(createEmptyCell());
		}
		
		return center;
	}
	
	JPanel createMediaCell(Media media) {
		JPanel cell = new JPanel();
		cell.setLayout(new BoxLayout(cell, BoxLayout.Y_AXIS));
        cell.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        
        JLabel titleLabel = new JLabel(media.getTitle());
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 14));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel detailsLabel = new JLabel(media.getClass().getSimpleName() + " - " + media.getCost() + " $");
        detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel buttonPanel = new JPanel();
        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> {
				try {
					playMedia(media);
				} catch (PlayerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
            buttonPanel.add(playButton);
        }
        
        cell.add(Box.createVerticalGlue());
        cell.add(titleLabel);
        cell.add(detailsLabel);
        cell.add(Box.createVerticalGlue());
        cell.add(buttonPanel);
        
        return cell;
	}
	
	JPanel createEmptyCell() {
		JPanel cell = new JPanel();
        cell.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        cell.setBackground(new Color(240, 240, 240));
        return cell;
	}
	
	private void playMedia(Media media) throws PlayerException {
        JDialog playDialog = new JDialog(this, "Now Playing", true);
        playDialog.setSize(300, 200);
        playDialog.setLocationRelativeTo(this);
        
        JTextArea playInfo = new JTextArea();
        playInfo.setEditable(false);
        playInfo.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        if (media instanceof Playable) {
            playInfo.setText("Now playing: " + media.getTitle() + "\n\n");
            
            if (media instanceof CompactDisc) {
                CompactDisc cd = (CompactDisc) media;
                playInfo.append("Artist: " + cd.getArtist() + "\n");
                playInfo.append("Total length: " + cd.getLength() + " seconds\n");
            } else if (media instanceof DigitalVideoDisc) {
                DigitalVideoDisc dvd = (DigitalVideoDisc) media;
                playInfo.append("Director: " + dvd.getDirector() + "\n");
                playInfo.append("Length: " + dvd.getLength() + " minutes\n");
            }
            
            ((Playable) media).play();
        }
        
        playDialog.add(new JScrollPane(playInfo));
        playDialog.setVisible(true);
    }
	
	private void refreshStoreDisplay() {
        getContentPane().removeAll();
        getContentPane().add(createNorth(), BorderLayout.NORTH);
        getContentPane().add(createCenter(), BorderLayout.CENTER);
        revalidate();
        repaint();
    }
	
	public static void main(String[] args) {
	    Store store = new Store();
	    
	    CompactDisc cd1 = new CompactDisc("The Dark Side of the Moon", "Rock", "Pink Floyd", 15.99f);
	    cd1.addTrack(new Track("Speak to Me", 90));
	    cd1.addTrack(new Track("Breathe", 163));
	    cd1.addTrack(new Track("On the Run", 216));
	    store.addMedia(cd1);
	    
	    CompactDisc cd2 = new CompactDisc("Thriller", "Pop", "Michael Jackson", 12.99f);
	    cd2.addTrack(new Track("Wanna Be Startin' Somethin'", 363));
	    cd2.addTrack(new Track("Thriller", 357));
	    store.addMedia(cd2);
	    
	    DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Shawshank Redemption", "Drama", "Frank Darabont", 142, 9.99f);
	    DigitalVideoDisc dvd2 = new DigitalVideoDisc("Inception", "Sci-Fi", "Christopher Nolan", 148, 12.99f);
	    store.addMedia(dvd1);
	    store.addMedia(dvd2);
	    
	    Book book1 = new Book("The Lord of the Rings", "Fantasy", 19.99f);
	    book1.addAuthor("J.R.R. Tolkien");
	    Book book2 = new Book("Clean Code", "Programming", 29.99f);
	    book2.addAuthor("Robert C. Martin");
	    store.addMedia(book1);
	    store.addMedia(book2);
	    
	    SwingUtilities.invokeLater(() -> {
	        StoreManagerScreen screen = new StoreManagerScreen(store);
	        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        screen.setVisible(true);
	    });
	}
}
