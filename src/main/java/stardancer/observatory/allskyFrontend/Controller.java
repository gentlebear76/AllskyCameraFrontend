package stardancer.observatory.allskyFrontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Controller implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    private final String imagePath = "D:\\Downloads\\";

    private Model model;

    private DirectoryWatcher directoryWatcher;
    private ExecutorService executorService;
    private Future<?> future;

    private boolean exposureRunning = false;

    private double exposureTime;
    private int cameraGain;


    @FXML
    ImageView imageView;

    @FXML
    TextField inputGain;

    @FXML
    TextField inputExposureTime;

    @FXML
    public void displayImage(Image image) {
        imageView.setImage(image);
    }

    @FXML
    public void updateExposure() {

    }

    @FXML
    public void updateGain() {

    }

    @FXML
    public void toggleExposure() {
        if (exposureRunning) {
            model.stopRemoteImaging();
        } else {
//            model.startRemoteImaging();
        }
    }


    private void initiateDirectoryWatcher() {
        try {
            Path directory = Paths.get(imagePath);
            directoryWatcher = new DirectoryWatcher(directory, this);
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


    /**
     * This class listenes for new directory updates and triggers this method when one shows up.
     * @param a
     */
    public void actionPerformed(ActionEvent a) {
        updateImage(a.getActionCommand());
    }

    private void updateImage(String fileName) {
        InputStream image = null;
        try {
            image = new FileInputStream(new File(imagePath + "\\" + fileName));
            displayImage(new Image(image));
        } catch (IOException i) {

        }
    }

    @FXML
    private void initialize() {
        initiateDirectoryWatcher();
        model = new Model();


    }






}
