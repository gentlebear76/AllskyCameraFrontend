package stardancer.observatory.allskyFrontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Controller {

    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    private final String imagePath = "D:\\Downloads\\";

    View view = new View();

    DirectoryWatcher directoryWatcher;
    ExecutorService executorService;
    Future<?> future;



    @FXML
    Label cameraImage;// = new Label("cameraImage");

    public void displayImage(Image image) {

        cameraImage.setText("Hulahop");
        cameraImage.setGraphic(new ImageView(image));



    }



    public void loadImage() {

    }

    private void initiateDirectoryWatcher() {
        try {
            Path directory = Paths.get(imagePath);
            directoryWatcher = new DirectoryWatcher(directory);
            executorService = Executors.newSingleThreadExecutor();
            future = executorService.submit(directoryWatcher);
            executorService.shutdown();
        } catch (IOException i) {
            LOGGER.error("Could not initiate the directory watcher! " + i.getMessage());
        }
    }

    private void shutDownnDirectoryWatcher() {
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
            future.cancel(true);
            executorService.awaitTermination(1, TimeUnit.SECONDS);
            executorService.shutdown();
        } catch (InterruptedException i) {
            LOGGER.debug("We got interrupted! " + i.getMessage());
        }
    }






    @FXML
    private void initialize() {
        initiateDirectoryWatcher();

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
