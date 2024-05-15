package model;

import enums.Constant;
import javafx.scene.shape.Rectangle;

public abstract class Bomb extends Rectangle {

	private double xSpeed, ySpeed, acceleration, range;
	public Bomb(double x, double y, double width, double height, double xSpeed, double ySpeed, double range) {
		super(width, height);
		setX(x);
		setY(y);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.range = range;
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

	public boolean checkCollision(Target target) {
		return this.getBoundsInParent().intersects(target.getBoundsInParent());
	}

	public boolean checkInRange(Target target) {
		if (target.getX() <= this.getX() && this.getX() <= target.getX() + target.getWidth()) {
			return true;
		}
		if (Math.abs(target.getX() - this.getX()) <= this.range) {
			return true;
		}
		if (Math.abs(target.getX() + target.getWidth() - this.getX()) <= this.range) {
			return true;
		}
		return false;
	}

}
