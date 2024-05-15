package model;

import enums.Constant;
import enums.PicturesAddress;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Jet extends Rectangle {

	private double speed;
	private double angle;

	private boolean isGoingUp = false;
	private boolean isGoingDown = false;
	private boolean isGoingLeft = false;
	private boolean isGoingRight = false;

	public Jet() {
		super(Constant.JET_WIDTH.getValue(), Constant.JET_HEIGHT.getValue());
		speed = Constant.JET_SPEED.getValue();
		angle = 0;
		this.setStrokeWidth(0);
		this.setFill(Game.Images.JET.getValue());
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		while(angle < 0)
			angle += 360;
		while(angle >= 360)
			angle -= 360;
		this.angle = angle;
	}

	public boolean isGoingUp() {
		return isGoingUp;
	}

	public void setGoingUp(boolean goingUp) {
		isGoingUp = goingUp;
	}

	public boolean isGoingDown() {
		return isGoingDown;
	}

	public void setGoingDown(boolean goingDown) {
		isGoingDown = goingDown;
	}

	public boolean isGoingLeft() {
		return isGoingLeft;
	}

	public void setGoingLeft(boolean goingLeft) {
		isGoingLeft = goingLeft;
	}

	public boolean isGoingRight() {
		return isGoingRight;
	}

	public void setGoingRight(boolean goingRight) {
		isGoingRight = goingRight;
	}

	public void reset() {
		this.setX(0);
		this.setY(0);
		this.setSpeed(Constant.JET_SPEED.getValue());
		this.setAngle(0);
		this.setRotate(0);
		this.setScaleY(1);
		this.setGoingUp(false);
		this.setGoingDown(false);
		this.setGoingLeft(false);
		this.setGoingRight(false);
	}
}
