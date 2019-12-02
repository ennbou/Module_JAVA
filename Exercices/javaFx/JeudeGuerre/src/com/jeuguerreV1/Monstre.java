 package com.jeuguerreV1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monstre extends GraphiqueObjet {
public Monstre(Zone zone) {
	Image image=null;
	try {
		image = new Image(new FileInputStream("photojeu/monstre2.png"));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	corps=new ImageView(image);
	((ImageView)corps).setX(0);
	((ImageView)corps).setY(0);
	
	//doit appartenir zone
	double x=zone.getX1()+(zone.getX2()-zone.getX1())*Math.random();
	double y=zone.getY1()+(zone.getY2()-zone.getY1())*Math.random();
	corps.setTranslateX(x);
	corps.setTranslateY(y);
}
}
