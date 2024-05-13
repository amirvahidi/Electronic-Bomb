package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Grass extends Rectangle {
	public Grass() {
		super(17, 9);
		this.setFill(new ImagePattern(new Image(getClass().getResource("/assets/Grass.png").toExternalForm())));
	}
}
