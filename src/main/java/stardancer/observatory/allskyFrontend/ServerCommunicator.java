package stardancer.observatory.allskyFrontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerCommunicator {

    String test = "192.168.1.42";
    int port = 4242;
    Settings settings;

    public ServerCommunicator(Settings settings) {
        this.settings = settings;
    }

    public void communicate() {

        try {
            Socket serverSocket = new Socket(test, port);
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

            out.println(Settings.CAMERA_EXPOSURE_TIME + "," + 42);



            String fromServer;
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye.")) {
                    System.out.println("Closing the connection!");
                    break;
                }
            }









        } catch (IOException i) {

        }






    }






}
