package Manager;

import Store.Store;
import Media.Media;
import Cart.Cart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StoreManagerScreen extends JFrame {
    private Store store;
    private Cart cart;
    private JPanel centerPanel;

    public StoreManagerScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        centerPanel = createCenter();
        cp.add(new JScrollPane(centerPanel), BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));

        north.add(createHeader());
        north.add(createMenuBar());

        return north;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    private JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenuItem viewStoreItem = new JMenuItem("View store");
        viewStoreItem.addActionListener(e -> refreshCenterPanel());
        menu.add(viewStoreItem);

        JMenu smUpdateStore = new JMenu("Update Store");

        JMenuItem addBookItem = new JMenuItem("Add Book");
        addBookItem.addActionListener(e -> new AddBookToStoreScreen(store) {
            @Override
            protected Media createMedia() {
                Media media = super.createMedia();
                if (media != null) {
                    refreshCenterPanel();
                }
                return media;
            }
        });
        smUpdateStore.add(addBookItem);

        JMenuItem addCDItem = new JMenuItem("Add CD");
        addCDItem.addActionListener(e -> new AddCompactDiscToStoreScreen(store) {
            @Override
            protected Media createMedia() {
                Media media = super.createMedia();
                if (media != null) {
                    refreshCenterPanel();
                }
                return media;
            }
        });
        smUpdateStore.add(addCDItem);

        JMenuItem addDVDItem = new JMenuItem("Add DVD");
        addDVDItem.addActionListener(e -> new AddDigitalVideoDiscToStoreScreen(store) {
            @Override
            protected Media createMedia() {
                Media media = super.createMedia();
                if (media != null) {
                    refreshCenterPanel();
                }
                return media;
            }
        });
        smUpdateStore.add(addDVDItem);

        menu.add(smUpdateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    private JPanel createCenter() {
        JPanel center = new JPanel();
        // Tính số hàng cần thiết để hiển thị tất cả media (mỗi hàng 3 media)
        int numMedia = store.getItemsInStore().size();
        int numRows = (int) Math.ceil(numMedia / 3.0);
        center.setLayout(new GridLayout(numRows, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (int i = 0; i < mediaInStore.size(); i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i), cart);
            center.add(cell);
        }

        return center;
    }

    private void refreshCenterPanel() {
        Container cp = getContentPane();
        cp.remove(centerPanel.getParent()); // Xóa JScrollPane chứa centerPanel
        centerPanel = createCenter();
        cp.add(new JScrollPane(centerPanel), BorderLayout.CENTER);
        cp.revalidate();
        cp.repaint();
    }

    public static void main(String[] args) {
        Store store = new Store();
        Cart cart = new Cart();
        store.addMedia(new Media("Harry Potter and the Philosopher's Stone (2001)", "Fantasy", 0.3f) {
            @Override
            public void play() {
                System.out.println("Cannot play books");
            }

            @Override
            public String toString() {
                return getTitle() + " - " + getCategory() + ": " + getCost() + " $";
            }
        });
        store.addMedia(new Media("Harry Potter and the Chamber of Secrets (2002)", "Fantasy", 0.5f) {
            @Override
            public void play() {
                System.out.println("Cannot play books");
            }

            @Override
            public String toString() {
                return getTitle() + " - " + getCategory() + ": " + getCost() + " $";
            }
        });
        store.addMedia(new Media("Harry Potter and the Prisoner of Azkaban (2004)", "Fantasy", 0.5f) {
            @Override
            public void play() {
                System.out.println("Cannot play books");
            }

            @Override
            public String toString() {
                return getTitle() + " - " + getCategory() + ": " + getCost() + " $";
            }
        });
        store.addMedia(new Media("Harry Potter and the Goblet of Fire (2006)", "Fantasy", 4.5f) {
            @Override
            public void play() {
                System.out.println("Cannot play books");
            }

            @Override
            public String toString() {
                return getTitle() + " - " + getCategory() + ": " + getCost() + " $";
            }
        });
        store.addMedia(new Media("Fetch the Bolt Cutters", "Music", 10.39f) {
            @Override
            public void play() {
                System.out.println("Playing CD: " + getTitle());
            }

            @Override
            public String toString() {
                return getTitle() + " - " + getCategory() + ": " + getCost() + " $";
            }
        });
        store.addMedia(new Media("Future Nostalgia", "Music", 9.65f) {
            @Override
            public void play() {
                System.out.println("Playing CD: " + getTitle());
            }

            @Override
            public String toString() {
                return getTitle() + " - " + getCategory() + ": " + getCost() + " $";
            }
        });
        store.addMedia(new Media("The Hunger Games", "Fiction", 5.5f) {
            @Override
            public void play() {
                System.out.println("Cannot play books");
            }

            @Override
            public String toString() {
                return getTitle() + " - " + getCategory() + ": " + getCost() + " $";
            }
        });
        store.addMedia(new Media("Catching Fire", "Fiction", 4.9f) {
            @Override
            public void play() {
                System.out.println("Cannot play books");
            }

            @Override
            public String toString() {
                return getTitle() + " - " + getCategory() + ": " + getCost() + " $";
            }
        });
        store.addMedia(new Media("Mockingjay", "Fiction", 5.15f) {
            @Override
            public void play() {
                System.out.println("Cannot play books");
            }

            @Override
            public String toString() {
                return getTitle() + " - " + getCategory() + ": " + getCost() + " $";
            }
        });

        SwingUtilities.invokeLater(() -> new StoreManagerScreen(store, cart));
    }
}