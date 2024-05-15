package model;

import enums.Constant;
import enums.PicturesAddress;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Nuke extends Bomb {

	public Nuke(double x, double y, double xSpeed, double ySpeed) {
		super(x, y, Constant.NUKE_WIDTH.getValue(), Constant.NUKE_HEIGHT.getValue(), xSpeed, ySpeed, Constant.NUKE_RANGE.getValue());
		this.setFill(Game.Images.NUKE.getValue());
	}

}
