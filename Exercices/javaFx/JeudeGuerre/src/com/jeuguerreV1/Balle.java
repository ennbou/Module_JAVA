package com.jeuguerreV1;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Balle extends GraphiqueObjet {
	private Point2D direction = new Point2D(0, 0);

	public Balle(Arme arme) {
		corps = new Circle(0,0, 5, Color.CHOCOLATE);
		corps.setTranslateX(arme.getCorps().getTranslateX()+ 35);
		corps.setTranslateY(arme.getCorps().getTranslateY()+45);
		updateDirection(arme.getRotate());
	}

	private void updateDirection(double rotation) {
		Point2D p;
		double x = Math.cos(Math.toRadians(rotation));
		double y = Math.sin(Math.toRadians(rotation));
		p = new Point2D(x, y);
		direction = p.normalize().multiply(10);
	}

	public void update() {
		corps.setTranslateX(corps.getTranslateX() + direction.getX());
		corps.setTranslateY(corps.getTranslateY() + direction.getY());

	}

}
