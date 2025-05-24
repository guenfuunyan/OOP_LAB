package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
	private JTextField tfTitle;
	private JTextField tfCategory;
	private JTextField tfCost;
	private JTextField tfAuthors;
	
	public AddBookToStoreScreen(Store store) {
		super(store);
		
		Container cp = getContentPane();
		cp.add(createCenter(), BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	protected JPanel createCenter() {
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(5, 2, 2, 2));
		
		center.add(new JLabel("Title: "));
		tfTitle = new JTextField();
		center.add(tfTitle);
		
		center.add(new JLabel("Category: "));
		tfCategory = new JTextField();
		center.add(tfCategory);
		
		center.add(new JLabel("Cost: "));
		tfCost = new JTextField();
		center.add(tfCost);
		
		center.add(new JLabel("Authors (comma seperated): "));
		tfAuthors = new JTextField();
		center.add(tfAuthors);
		
		JButton btnAdd = new JButton("Add Book: ");
		btnAdd.addActionListener(new ButtonListener());
		center.add(btnAdd);
		
		return center;
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String title = tfTitle.getText();
			String category = tfCategory.getText();
			float cost = Float.parseFloat(tfCost.getText());
			String[] authors = tfAuthors.getText().split(",");
			
			Book book = new Book(title, category, cost);
			for (String author : authors) {
				book.addAuthor(author.trim());
			}
			
			store.addMedia(book);
			JOptionPane.showMessageDialog(null, "Book added successfully!");
			new StoreManagerScreen(store);
			dispose();
		}
	}
}
