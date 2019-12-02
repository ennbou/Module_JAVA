package com.jeuguerreV1;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Arme {
	private Rectangle corps = new Rectangle(30, 0, 10, 80);
	private Circle sortie = new Circle(35, 0, 5);
	@SuppressWarnings("unused")
	private Node node;

	public Arme(GraphiqueObjet player) {
		node = player.getCorps();
		corps.setTranslateX(player.getCorps().getTranslateX());
		corps.setTranslateY(player.getCorps().getTranslateY());
		// corps.setX(10);
		// corps.setY(10);
		corps.setFill(Color.MEDIUMVIOLETRED);
		sortie.setFill(Color.CORAL);
		updateSortie();

	}

	public void attachToPlayer(Player player) {
		corps.setTranslateX(player.getCorps().getTranslateX());
		corps.setTranslateY(player.getCorps().getTranslateY());
		updateSortie();
	}

	public void updateSortie() {
		sortie.setTranslateX(corps.getTranslateX());
		sortie.setTranslateY(corps.getTranslateY() + 40);

	}

	public Rectangle getCorps() {
		return corps;
	}

	public void setCorps(Rectangle corps) {
		this.corps = corps;
	}

	public Circle getSortie() {
		return sortie;
	}

	public void setSortie(Circle sortie) {
		this.sortie = sortie;
	}

//si on clique sur x tourner vers la droite si sur y il tourne vers la gauche
	public void rotationRight() {
		corps.setRotate(corps.getRotate() - 5);

	}

	public void rotationLeft() {
		corps.setRotate(corps.getRotate() + 5);

	}

	public double getRotate() {
		return corps.getRotate() - 90;

	}
}
