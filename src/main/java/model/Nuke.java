package model;

import enums.Constant;
import enums.PicturesAddress;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Nuke extends Bomb {

	public Nuke(double x, double y, double xSpeed, double ySpeed) {
		super(x, y, Constant.NUKE_WIDTH.getValue(), Constant.NUKE_HEIGHT.getValue(), xSpeed, ySpeed);
		this.setFill(new ImagePattern(new Image(Nuke.class.getResource(PicturesAddress.NUKE.getValue()).toExternalForm())));
	}

}
