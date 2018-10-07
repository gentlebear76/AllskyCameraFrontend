package stardancer.observatory.allskyFrontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Presenter {


    View view = new View();

    public void loadImage() {

    }



    @FXML
    private void initialize() {
       InputStream image = null;
        try {
            image = new FileInputStream(new File("D:\\Downloads\\test.jpg"));
        } catch (IOException i) {

        }
        Image testImage = null;
        if (image != null) {
            view.displayImage(new Image(image));
        }

    }






}
