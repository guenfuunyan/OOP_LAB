package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartController {
    private Cart cart;
    private Store store;
    private FilteredList<Media> filteredMedia;

    public CartController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML private Label lblTotalCost;
    @FXML private TextField tfFilter;
    @FXML private RadioButton radioBtnFilterId;
    @FXML private RadioButton radioBtnFilterTitle;
    @FXML private ToggleGroup filterCategory;
    @FXML private TableView<Media> tblMedia;
    @FXML private TableColumn<Media, Integer> colMediaId;
    @FXML private TableColumn<Media, String> colMediaTitle;
    @FXML private TableColumn<Media, String> colMediaCategory;
    @FXML private TableColumn<Media, Float> colMediaCost;
    @FXML private Button btnPlay;
    @FXML private Button btnRemove;

    @FXML
    private void initialize() {
        // Liên kết cột với thuộc tính của Media
        colMediaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        // Khởi tạo danh sách có thể lọc
        filteredMedia = new FilteredList<>(cart.getItemsOrdered(), p -> true);
        tblMedia.setItems(filteredMedia);

        // Mặc định ẩn nút Play và Remove khi chưa chọn
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        // Cập nhật nút khi người dùng chọn media
        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Media> observable, Media oldValue, Media newValue) -> updateButtonBar(newValue)
        );

        // Cập nhật lọc khi người dùng gõ từ khoá
        tfFilter.textProperty().addListener((obs, oldVal, newVal) -> showFilteredMedia(newVal));

        // Cập nhật lọc khi thay đổi loại lọc (ID hoặc Title)
        radioBtnFilterId.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) showFilteredMedia(tfFilter.getText());
        });

        radioBtnFilterTitle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) showFilteredMedia(tfFilter.getText());
        });

        radioBtnFilterId.setSelected(true); // Mặc định lọc theo ID

        // Tự động cập nhật tổng tiền khi có thay đổi giỏ hàng
        cart.getItemsOrdered().addListener((ListChangeListener<Media>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved()) {
                    updateTotalCost();
                }
            }
        });

        updateTotalCost();
    }

    private void updateButtonBar(Media media) {
        if (media == null) {
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        } else {
            btnRemove.setVisible(true);
            btnPlay.setVisible(media instanceof Playable);
        }
    }

    private void showFilteredMedia(String filterText) {
        if (filterText == null || filterText.trim().isEmpty()) {
            filteredMedia.setPredicate(media -> true);
            return;
        }

        String lowerCaseFilter = filterText.trim().toLowerCase();

        if (radioBtnFilterId.isSelected()) {
            filteredMedia.setPredicate(media ->
                    String.valueOf(media.getId()).toLowerCase().contains(lowerCaseFilter)
            );
        } else if (radioBtnFilterTitle.isSelected()) {
            filteredMedia.setPredicate(media ->
                    media.getTitle() != null && media.getTitle().toLowerCase().contains(lowerCaseFilter)
            );
        }
    }

    public void updateTotalCost() {
        lblTotalCost.setText(String.format("%.2f $", cart.totalCost()));
    }

    @FXML
    private void btnRemovePressed(ActionEvent event) {
        Media selected = tblMedia.getSelectionModel().getSelectedItem();
        if (selected != null) {
            cart.removeMedia(selected);
            showFilteredMedia(tfFilter.getText());
        }
    }

    @FXML
    private void btnPlayPressed(ActionEvent event) {
        Media selected = tblMedia.getSelectionModel().getSelectedItem();
        if (selected instanceof Playable) {
            try {
                ((Playable) selected).play();
                showAlert(Alert.AlertType.INFORMATION, "Play Media", null, "Đang phát: " + selected.getTitle());
            } catch (PlayerException e) {
                showAlert(Alert.AlertType.ERROR, "Lỗi phát media", "Không thể phát media", e.getMessage());
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Lỗi không xác định", "Đã xảy ra lỗi", e.getMessage());
            }
        }
    }

    @FXML
    private void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Giỏ hàng trống", null, "Hãy thêm sản phẩm vào giỏ hàng trước khi đặt hàng!");
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Đặt hàng thành công", null, "Đơn hàng của bạn đã được đặt thành công!");
            cart.getItemsOrdered().clear();
            updateTotalCost();
        }
    }

    @FXML
    private void btnViewStorePressed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/hedspi/aims/screen/customer/view/Store.fxml"));
            loader.setController(new ViewStoreController(store, cart));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Store");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
