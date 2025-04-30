package Manager;

import Media.Media;
import Media.Playable;
import Cart.Cart;

import javax.swing.*;
import java.awt.*;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Tiêu đề
        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        title.setAlignmentX(CENTER_ALIGNMENT);

        // Giá
        JLabel cost = new JLabel("$" + media.getCost());
        cost.setAlignmentX(CENTER_ALIGNMENT);

        // Panel chứa các nút
        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Nút Play (nếu media là Playable)
        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> {
                try {
                    ((Playable) media).play();
                    JOptionPane.showMessageDialog(this, "Playing: " + media.getTitle());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error playing media: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            container.add(playButton);
        }

        // Nút Add to Cart
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> {
            cart.addMedia(media);
            JOptionPane.showMessageDialog(this, "Added to cart: " + media.getTitle());
        });
        container.add(addToCartButton);

        // Thêm các thành phần vào panel
        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        // Đặt viền
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}