package app;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

public class Camera3D extends Application {

    private static final double WIDTH = 800;
    private static final double HEIGHT = 500;

    public static void main(String[] args) {
        System.out.println("My App is starting");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Box box = new Box(100, 100, 100);
        SmartGroup group = new SmartGroup();
        group.getChildren().add(box);
        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.BISQUE);

        Camera camera = new PerspectiveCamera();
        scene.setCamera(camera);

        group.translateXProperty().set(WIDTH / 2);
        group.translateYProperty().set(HEIGHT / 2);
        group.translateZProperty().set(-400);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            switch (e.getCode()) {
            case W:
                group.translateZProperty().set(group.getTranslateZ() + 100);
                break;
            case S:
                group.translateZProperty().set(group.getTranslateZ() - 100);
                break;
            case Q:
                group.rotateByX(10);
                break;
            case E:
                group.rotateByX(-10);
                break;
            case A:
                group.rotateByY(10);
                break;
            case D:
                group.rotateByY(-10);
                break;
            default: ;
            }
        });

        primaryStage.setTitle("Camera");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}

class SmartGroup extends Group {
    private Rotate r;
    private Transform t = new Rotate();

    public void rotateByX(int ang) {
        rotate(ang, Rotate.X_AXIS);
    }

    public void rotateByY(int ang) {
        rotate(ang, Rotate.Y_AXIS);
    }

    public void rotate(int ang, Point3D point) {
        r = new Rotate(ang, point);
        t = t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().add(t);
    }

}