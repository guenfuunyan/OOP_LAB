package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewStoreController {

    private Store store;
    private Cart cart;

    public ViewStoreController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    private GridPane gridPane;

    @FXML
    private void btnViewCartPressed(ActionEvent event) {
        try {
            final String CART_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Cart.fxml";

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CART_FXML_FILE_PATH));
            fxmlLoader.setController(new CartController(store, cart));

            Parent cartRoot = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(cartRoot));
            stage.setTitle("Cart");
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load Cart.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        int column = 0;
        int row = 1;

        for (Media media : store.getItemsInStore()) {
            try {
                AnchorPane mediaCard = loadMediaItem(media);
                gridPane.add(mediaCard, column, row);

                column++;
                if (column == 3) {
                    column = 0;
                    row++;
                }

                GridPane.setMargin(mediaCard, new Insets(20, 10, 10, 10));
            } catch (IOException e) {
                System.err.println("Failed to load Item.fxml for media: " + media.getTitle());
                e.printStackTrace();
            }
        }
    }

    private AnchorPane loadMediaItem(Media media) throws IOException {
        final String ITEM_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Item.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ITEM_FXML_FILE_PATH));
        ItemController itemController = new ItemController(cart);
        fxmlLoader.setController(itemController);

        AnchorPane anchorPane = fxmlLoader.load();
        itemController.setData(media);

        System.out.println("Loaded media item: " + media.getTitle());
        return anchorPane;
    }
}
