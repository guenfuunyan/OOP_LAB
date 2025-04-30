package Manager;

import Store.Store;
import Media.Book;
import Media.Media;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfAuthors;

    public AddBookToStoreScreen(Store store) {
        super(store);
        setTitle("Add Book to Store");

        // Thêm trường authors
        setLayout(new GridLayout(5, 2, 5, 5));
        add(new JLabel("Authors (comma-separated):"));
        tfAuthors = new JTextField(20);
        add(tfAuthors);
    }

    @Override
    protected Media createMedia() {
        try {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            String[] authorsArray = tfAuthors.getText().split(",");
            ArrayList<String> authors = new ArrayList<>();
            for (String author : authorsArray) {
                authors.add(author.trim());
            }
            return new Book(title, category, cost, authors);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid cost format!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}