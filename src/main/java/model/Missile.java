package model;

import enums.Constant;
import enums.PicturesAddress;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Missile extends Bomb {
	public Missile(double x, double y, double xSpeed, double ySpeed) {
		super(x, y, Constant.MISSILE_WIDTH.getValue(), Constant.MISSILE_HEIGHT.getValue(), xSpeed, ySpeed, Constant.MISSILE_RANGE.getValue());
		this.setFill(Game.Images.MISSILE.getValue());
	}
}
