package stardancer.observatory.allskyFrontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.commons.cli.CommandLine;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Logging.setupLogConsoleLogger();
        CommandLine cmd = CommandLineController.parseCommandLine(args);
        Logging.setupFileLogger(cmd);
        Settings settings = new Settings();

        ServerCommunicator serverCommunicator = new ServerCommunicator(settings);
        serverCommunicator.communicate();





//        launch(args);
    }
}
