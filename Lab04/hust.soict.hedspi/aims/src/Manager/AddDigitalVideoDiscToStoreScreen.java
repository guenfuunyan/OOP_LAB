package Manager;

import Store.Store;
import Media.DigitalVideoDisc;
import Media.Media;

import javax.swing.*;
import java.awt.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfDirector;
    private JTextField tfLength;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store);
        setTitle("Add DVD to Store");

        // Thêm các trường director và length
        setLayout(new GridLayout(6, 2, 5, 5));
        add(new JLabel("Director:"));
        tfDirector = new JTextField(20);
        add(tfDirector);

        add(new JLabel("Length:"));
        tfLength = new JTextField(20);
        add(tfLength);
    }

    @Override
    protected Media createMedia() {
        try {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            String director = tfDirector.getText();
            int length = Integer.parseInt(tfLength.getText());
            return new DigitalVideoDisc(title, category, director, length, cost);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid cost or length format!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}