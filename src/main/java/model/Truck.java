package model;

import enums.Constant;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;

public class Truck extends DynamicTarget {

	public Truck(double x, double y, boolean goingRight) {
		super(x, y, Constant.TRUCK_WIDTH.getValue(), Constant.TRUCK_HEIGHT.getValue(), goingRight, Constant.TRUCK_SPEED.getValue());
		this.setFill(Game.Images.TRUCK.getValue());
		if (!goingRight){
			this.setScaleX(-1);
		}
	}
}
