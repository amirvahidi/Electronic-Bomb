package model;


import enums.Constant;
import enums.PicturesAddress;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Cluster extends Bomb {

	public Cluster(double x, double y, double xSpeed, double ySpeed) {
		super(x, y, Constant.CLUSTER_WIDTH.getValue(), Constant.CLUSTER_HEIGHT.getValue(), xSpeed, ySpeed);
		this.setFill(Game.Images.CLUSTER.getValue());
	}


}
