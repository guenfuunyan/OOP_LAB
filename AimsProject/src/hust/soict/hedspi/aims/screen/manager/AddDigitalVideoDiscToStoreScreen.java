package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;


public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
	private JTextField tfTitle;
	private JTextField tfCategory;
	private JTextField tfDirector;
	private JTextField tfLength;
	private JTextField tfCost;
	
	public AddDigitalVideoDiscToStoreScreen(Store store) {
		super(store);
		
		Container cp = getContentPane();
		cp.add(createCenter(), BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	protected JPanel createCenter() {
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(6, 2, 2, 2));
		
		center.add(new JLabel("Title: "));
		tfTitle = new JTextField();
		center.add(tfTitle);
		
		center.add(new JLabel("Category: "));
		tfCategory = new JTextField();
		center.add(tfCategory);
		
		center.add(new JLabel("Director: "));
		tfDirector = new JTextField();
		center.add(tfDirector);
		
		center.add(new JLabel("Length: "));
		tfLength = new JTextField();
		center.add(tfLength);
		
		center.add(new JLabel("Cost: "));
		tfCost = new JTextField();
		center.add(tfCost);
		
		JButton btnAdd = new JButton("Add DVD");
		btnAdd.addActionListener(new ButtonListener());
		center.add(btnAdd);
		
		return center;
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				String title = tfTitle.getText();
				String category = tfCategory.getText();
				String director = tfDirector.getText();
				int length = Integer.parseInt(tfLength.getText());
				float cost = Float.parseFloat(tfCost.getText());
				
				DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
				store.addMedia(dvd);
				
				JOptionPane.showMessageDialog(null, "DVD added successfully!");
				new StoreManagerScreen(store);
				dispose();
			} catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Invalid input format! Please check again!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
