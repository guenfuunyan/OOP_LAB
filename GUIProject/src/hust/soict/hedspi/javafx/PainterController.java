package hust.soict.hedspi.javafx;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.event.ActionEvent;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private void drawingAreaMouseDragged(MouseEvent event) {
        Circle circle = createCircleAt(event.getX(), event.getY());
        drawingAreaPane.getChildren().add(circle);
    }

    @FXML
    private void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    private Circle createCircleAt(double x, double y) {
        Circle circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(4);
        circle.setFill(Color.BLACK);
        return circle;
    }
}
