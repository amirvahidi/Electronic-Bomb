package model;

import enums.Constant;
import enums.PicturesAddress;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Bomblet extends Bomb {

	public Bomblet(double x, double y, double xSpeed, double ySpeed) {
		super(x, y, Constant.BOMBLET_WIDTH.getValue(), Constant.BOMBLET_HEIGHT.getValue(), xSpeed, ySpeed);
		this.setFill(Game.Images.BOMBLET.getValue());
	}

}
