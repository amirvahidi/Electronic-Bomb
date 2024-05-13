package model;

import javafx.scene.shape.Rectangle;

public abstract class Bomb extends Rectangle {

	public Bomb(double x, double y, double width, double height) {
		super(width, height);
		setX(x);
		setY(y);
	}

	public abstract double getXSpeed();

	public abstract void setXSpeed(double xSpeed);

	public abstract double getYSpeed();

	public abstract void setYSpeed(double ySpeed);

	public abstract double getAcceleration();

	public abstract void setAcceleration(double acceleration);
}
