package model;

import enums.Constant;
import javafx.scene.shape.Rectangle;

public abstract class Bomb extends Rectangle {

	private double xSpeed, ySpeed, acceleration;
	public Bomb(double x, double y, double width, double height, double xSpeed, double ySpeed) {
		super(width, height);
		setX(x);
		setY(y);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		System.out.println(this.xSpeed + " " + this.ySpeed);
		this.acceleration = Constant.BOMB_ACCELERATION.getValue();
	}

	public double getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public double getYSpeed() {
		return ySpeed;
	}

	public void setYSpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

}
