package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ClientIHM extends Application {

    private static final double WIDTH = 600;
    private static final double HEIGHT = 400;

    private ClientSide client = null;
    private Circle circle1;
    private Circle circle2;

    @Override
    public void start(Stage window) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, WIDTH, HEIGHT);

        circle1 = new Circle(0, 0, 50, Color.BLUE);
        circle2 = new Circle(100, 100, 50, Color.BLACK);

        try {
            client = new ClientSide(circle2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        pane.getChildren().addAll(circle1, circle2);

        window.setScene(scene);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent ke) {

                double x = circle1.getTranslateX();
                double y = circle1.getTranslateY();

                if (ke.getCode() == KeyCode.A) {
                    System.out.println("x : " + x);
                    circle1.setTranslateY(y - 10);
                }
                if (ke.getCode() == KeyCode.Q) {
                    System.out.println("y : " + y);
                    circle1.setTranslateY(y + 10);
                }

                client.send(new Shape(circle1.getTranslateX(), circle1.getTranslateY()));

            }

        });

        client.listener();

        window.show();

    }

}

class ClientSide extends Socket {

    ObjectOutputStream out;
    ObjectInputStream in;
    Circle circle;

    public ClientSide(Circle circle) throws IOException {
        super("127.0.0.1", 8080);
        this.circle = circle;
        out = new ObjectOutputStream(getOutputStream());
        in = new ObjectInputStream(getInputStream());
    }

    public void send(Shape shape) {
        try {
            out.writeObject(shape);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listener() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Shape p = (Shape) in.readObject();
                        System.out.println("Recive Shape " + p.getX() + ", " + p.getY());
                        circle.setTranslateX(p.getX());
                        circle.setTranslateY(p.getY());
                    }
                } catch (ClassNotFoundException | IOException e) {

                    e.printStackTrace();
                }
            }
        }).start();
    }

}