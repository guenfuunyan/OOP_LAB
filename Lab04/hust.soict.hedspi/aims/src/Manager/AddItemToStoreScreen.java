package Manager;

import Store.Store;
import Media.Media;

import javax.swing.*;
import java.awt.*;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected JTextField tfTitle;
    protected JTextField tfCategory;
    protected JTextField tfCost;

    public AddItemToStoreScreen(Store store) {
        this.store = store;

        setTitle("Add Item to Store");
        setLayout(new GridLayout(4, 2, 5, 5));
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Thêm các trường nhập thông tin chung
        add(new JLabel("Title:"));
        tfTitle = new JTextField(20);
        add(tfTitle);

        add(new JLabel("Category:"));
        tfCategory = new JTextField(20);
        add(tfCategory);

        add(new JLabel("Cost:"));
        tfCost = new JTextField(20);
        add(tfCost);

        // Nút Add (sẽ được xử lý trong các lớp con)
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            Media media = createMedia();
            if (media != null) {
                store.addMedia(media);
                JOptionPane.showMessageDialog(this, "Added: " + media.getTitle());
                dispose(); // Đóng cửa sổ sau khi thêm
            }
        });
        add(new JLabel("")); // Placeholder
        add(addButton);

        setVisible(true);
    }

    // Phương thức trừu tượng để các lớp con tạo media
    protected abstract Media createMedia();
}