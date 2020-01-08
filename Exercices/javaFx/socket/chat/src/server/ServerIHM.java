package server;

import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ServerIHM extends Application {

    TextArea text;

    @Override
    public void start(Stage window) {

        ServerSide server = null;
        text = new TextArea();
        try {
            server = new ServerSide(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.acceptClients();

        Pane p = new Pane();
        Scene scene = new Scene(p);
        p.getChildren().add(text);
        window.setScene(scene);
        window.show();
    }

}

class ServerSide extends ServerSocket {

    // private List<Socket> clients;
    TextArea text;

    public ServerSide(TextArea text) throws IOException {
        super(8080, 1);
        this.text = text;
        System.out.println("server starting");
        // clients = new ArrayList<>();
    }

    public void acceptClients() {
        new Thread(() -> {
            Socket s1, s2, s3, s4;
            try {
                s1 = accept();
                s2 = accept();
                s3 = accept();
                s4 = accept();

                // clients.add(s);
                listener(s1, s2.getOutputStream());
                listener(s2, s1.getOutputStream());
                listener(s3, s4.getOutputStream());
                listener(s4, s3.getOutputStream());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    private void listener(Socket s, OutputStream out) {
        new Thread(() -> {
            try (InputStream in = s.getInputStream();) {
                System.out.println("data connect");
                byte[] buffer = new byte[8192];
                while (true) {
                    int bytesRead = in.read(buffer);
                    if (bytesRead == -1)
                        break;
                    out.write(buffer, 0, bytesRead);
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

}