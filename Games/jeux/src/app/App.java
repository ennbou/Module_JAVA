package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {

    // variables
    private static final Random RAND = new Random();
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PLAYER_SIZE = 45;
    static final Image PLAYER_IMG = new Image("img\\1.png");
    static final Image EXPLOSION_IMG = new Image("src/app/img/explosion.png");
    static final int EXPLOSION_W = 120;
    static final int EXPLOSION_ROWS = 4;
    static final int EXPLOSION_COL = 4;
    static final int EXPLOSION_H = 120;
    static final int EXPLOSION_STEPS = 20;

    static final Image BOMBS_IMG[] = { new Image("src/app/img/1.png"), new Image("src/app/img/1.png"),
            new Image("src/app/img/1.png"), new Image("src/app/img/1.png"), new Image("src/app/img/1.png"),
            new Image("src/app/img/1.png"), new Image("src/app/img/1.png"), new Image("src/app/img/1.png"),
            new Image("src/app/img/1.png"), new Image("src/app/img/1.png"), new Image("src/app/img/1.png"),
            new Image("src/app/img/1.png"), new Image("src/app/img/1.png"), new Image("src/app/img/1.png"),
            new Image("src/app/img/1.png"), new Image("src/app/img/1.png"),

    };

    final int MAX_BOMBS = 10, MAX_SHOTS = MAX_BOMBS * 3;
    boolean gameOver = false;
    private GraphicsContext gc;

    Rocket player;
    List<Shot> shots;
    List<Bomb> Bombs;

    private double mouseX;
    private int score;

    // start
    public void start(Stage stage) throws Exception {

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(60), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        canvas.setCursor(Cursor.MOVE);
        canvas.setOnMouseMoved(e -> mouseX = e.getX());
        canvas.setOnMouseClicked(e -> {
            if (shots.size() < MAX_SHOTS)
                shots.add(player.shoot());
            if (gameOver) {
                gameOver = false;
                setup();
            }
        });
        try {
            Pane pane = new Pane();
            pane.getChildren().add(canvas);
            Scene scene = new Scene(pane);
            setup();
            stage.setScene(scene);
            stage.setTitle("Jeu de Guerre R�alis�e Par ANASS EL KAMA && BOUCHAIB ENNAK-R");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // setup the game
    private void setup() {
        shots = new ArrayList<>();
        Bombs = new ArrayList<>();
        player = new Rocket(WIDTH / 4, HEIGHT - PLAYER_SIZE, PLAYER_SIZE, PLAYER_IMG);
        score = 0;
        IntStream.range(0, MAX_BOMBS).mapToObj(i -> this.newBomb()).forEach(Bombs::add);
    }

    // run Graphics
    private void run(GraphicsContext gc) {
        Image background = new Image("src/app/img/images.jpg");
        gc.setFill(new ImagePattern(background));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font(25));
        gc.setFill(Color.WHITE);
        gc.fillText("Score : " + score, 60, 20);
        gc.fillText("JEU DE GUERRE", 400, 60);
        gc.setTextAlign(TextAlignment.CENTER);
        if (gameOver) {
            gc.setFont(Font.font(40));
            gc.setFill(Color.BURLYWOOD);
            gc.fillText("Game Over !! ton score est  " + score + " \n Click to play again", WIDTH / 2, HEIGHT / 2.5);
        }
        player.update();
        player.draw();
        player.posX = (int) mouseX;

        Bombs.stream().peek(Rocket::update).peek(Rocket::draw).forEach(e -> {
            if (player.colide(e) && !player.exploding) {
                player.explode();
            }
        });

        for (int i = shots.size() - 1; i >= 0; i--) {
            Shot shot = shots.get(i);
            if (shot.posY < 0 || shot.toRemove) {
                shots.remove(i);
                continue;
            }
            shot.update();
            shot.draw();
            for (Bomb bomb : Bombs) {
                if (shot.colide(bomb) && !bomb.exploding) {
                    score++;
                    bomb.explode();
                    shot.toRemove = true;
                }
            }
        }

        for (int i = Bombs.size() - 1; i >= 0; i--) {
            if (Bombs.get(i).destroyed) {
                Bombs.set(i, newBomb());
            }
        }

        gameOver = player.destroyed;
    }

    // player
    public class Rocket {

        int posX, posY, size;
        boolean exploding, destroyed;
        Image img;
        int explosionStep = 0;

        public Rocket(int posX, int posY, int size, Image image) {
            this.posX = posX;
            this.posY = posY;
            this.size = size;
            img = image;
        }

        public Shot shoot() {
            return new Shot(posX + size / 2 - Shot.size / 2, posY - Shot.size);
        }

        public void update() {
            if (exploding)
                explosionStep++;
            destroyed = explosionStep > EXPLOSION_STEPS;
        }

        public void draw() {
            if (exploding) {
                gc.drawImage(EXPLOSION_IMG, explosionStep % EXPLOSION_COL * EXPLOSION_W,
                        (explosionStep / EXPLOSION_ROWS) * EXPLOSION_H + 1, EXPLOSION_W, EXPLOSION_H, posX, posY, size,
                        size);
            } else {
                gc.drawImage(img, posX, posY, size, size);
            }
        }

        public boolean colide(Rocket other) {
            int d = distance(this.posX + size / 2, this.posY + size / 2, other.posX + other.size / 2,
                    other.posY + other.size / 2);
            return d < other.size / 2 + this.size / 2;
        }

        public void explode() {
            exploding = true;
            explosionStep = -1;
        }

    }

    // computer player
    public class Bomb extends Rocket {

        int SPEED = (score / 5) + 2;

        public Bomb(int posX, int posY, int size, Image image) {
            super(posX, posY, size, image);
        }

        public void update() {
            super.update();
            if (!exploding && !destroyed)
                posY += SPEED;
            if (posY > HEIGHT)
                destroyed = true;
        }
    }

    // bullets
    public class Shot {

        public boolean toRemove;

        int posX, posY, speed = 15;
        static final int size = 11;

        public Shot(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
        }

        public void update() {
            posY -= speed;
        }

        public void draw() {
            gc.setFill(Color.CADETBLUE);
            if (score >= 40 && score <= 70 || score >= 120 && score <= 150 || score >= 180) {
                gc.setFill(Color.BLUE);
                speed = 40;
                gc.fillRect(posX - 5, posY - 10, size + 10, size + 30);
            } else {
                gc.fillOval(posX, posY, size, size);
            }
        }

        public boolean colide(Rocket Rocket) {
            int distance = distance(this.posX + size / 2, this.posY + size / 2, Rocket.posX + Rocket.size / 2,
                    Rocket.posY + Rocket.size / 2);
            return distance < Rocket.size / 2 + size / 2;
        }
    }

    Bomb newBomb() {
        return new Bomb(50 + RAND.nextInt(WIDTH - 100), 0, PLAYER_SIZE, BOMBS_IMG[RAND.nextInt(BOMBS_IMG.length)]);
    }

    int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    public static void main(String[] args) {
        launch(args);
    }
}