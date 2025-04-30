package Manager;

import Store.Store;
import Media.CompactDisc;
import Media.Media;

import javax.swing.*;
import java.awt.*;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfArtist;
    private JTextField tfDirector;

    public AddCompactDiscToStoreScreen(Store store) {
        super(store);
        setTitle("Add CD to Store");

        // Thêm các trường artist và director
        setLayout(new GridLayout(6, 2, 5, 5));
        add(new JLabel("Artist:"));
        tfArtist = new JTextField(20);
        add(tfArtist);

        add(new JLabel("Director:"));
        tfDirector = new JTextField(20);
        add(tfDirector);
    }

    @Override
    protected Media createMedia() {
        try {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            String artist = tfArtist.getText();
            String director = tfDirector.getText();
            return new CompactDisc(title, category, artist, director, cost);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid cost format!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}