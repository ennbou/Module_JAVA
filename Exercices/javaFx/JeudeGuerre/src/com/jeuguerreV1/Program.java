package com.jeuguerreV1;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Program extends Application {

	// les elements de l'interface
	private double widthWindow = 800;
	private double heightWindow = 600;
	private Pane container = new Pane();
	Line line = new Line(0, 200, widthWindow, 200);
	Zone zone1 = new Zone(0, 0, line.getEndX() - 50, line.getEndY() - 50);
	Zone zone2 = new Zone(line.getStartX(), line.getStartY(), line.getEndX() - 170, heightWindow - 170);
	// 170 7int limage fiha 166x157, lplayer maghadich yakhraj mn l windows

	// les objets de jeu
	private Player player = new Player(zone2);
	private List<Monstre> monstres = new ArrayList<>();
	private List<Balle> balles = new ArrayList<>();

	Arme arme = new Arme(player);
	// Animationtimer
	AnimationTimer animation = new AnimationTimer() {
		public void handle(long now) {
			refreshContent();
		}
	};

	// zadt l condition 9bal up,down,right,left bach maykhraj lplayer mn lwindow
	EventHandler<KeyEvent> event = new EventHandler<KeyEvent>() {
		public void handle(KeyEvent event) {
			if (event.getCode() == KeyCode.X) {
				arme.rotationLeft();
			}
			if (event.getCode() == KeyCode.W) {
				arme.rotationRight();
			}
			if (event.getCode() == KeyCode.SPACE) {
				Balle balle = new Balle(arme);
				container.getChildren().add(balle.getCorps());
				balles.add(balle);
			}
			if (event.getCode() == KeyCode.LEFT) {
				if (player.getCorps().getTranslateX() > zone2.getX1()) {
					player.getCorps().setTranslateX(player.getCorps().getTranslateX() - 5);
					arme.attachToPlayer(player);
				}
			}
			if (event.getCode() == KeyCode.RIGHT) {
				if (player.getCorps().getTranslateX() < zone2.getX2()) {
					player.getCorps().setTranslateX(player.getCorps().getTranslateX() + 5);
					arme.attachToPlayer(player);
				}

			}
			if (event.getCode() == KeyCode.UP) {
				if (player.getCorps().getTranslateY() > zone2.getY1()) {
					player.getCorps().setTranslateY(player.getCorps().getTranslateY() - 5);
					arme.attachToPlayer(player);
				}
			}
			if (event.getCode() == KeyCode.DOWN) {
				if (player.getCorps().getTranslateY() < zone2.getY2()) {
					player.getCorps().setTranslateY(player.getCorps().getTranslateY() + 5);
					arme.attachToPlayer(player);
				}
			}
		}
	};

	private void refreshContent() {
		for (Balle balle : balles) {
			for (Monstre monstre : monstres) {
				if (balle.touch(monstre)) {
					container.getChildren().removeAll(balle.getCorps(), monstre.getCorps());
					balle.setAlive(false);
					monstre.setAlive(false);
				}
			}
		}

		monstres.removeIf(GraphiqueObjet::isDead);
		balles.removeIf(GraphiqueObjet::isDead);

		for (Balle balle : balles) {
			balle.update();
		}
		if (Math.random() < 0.001) {
			Monstre m = new Monstre(zone1);
			container.getChildren().add(m.getCorps());
			monstres.add(m);

		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	private void createContent() {
		container.getChildren().add(line);
		container.getChildren().add(player.getCorps());
		container.getChildren().add(arme.getCorps());
		container.getChildren().add(arme.getSortie());
	}

	@Override
	public void start(Stage window) throws Exception {
		window.setWidth(widthWindow);
		window.setHeight(heightWindow);
		window.setTitle("jeu de guerre");
		createContent();
		Scene scene = new Scene(container);
		window.setScene(scene);
		animation.start();
		scene.setOnKeyPressed(event);
		window.show();

	}

}
