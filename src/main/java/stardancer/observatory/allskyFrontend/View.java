package stardancer.observatory.allskyFrontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class View {

    @FXML
    Label cameraImage;// = new Label("cameraImage");

    public void displayImage(Image image) {

        cameraImage.setText("Hulahop");
        cameraImage.setGraphic(new ImageView(testImage));



    }













}
