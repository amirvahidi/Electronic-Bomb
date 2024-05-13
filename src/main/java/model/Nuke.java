package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Nuke extends Bomb {

	private double xSpeed, ySpeed;
	private double acceleration;

	public Nuke(Jet jet) {
		super(jet.getX(), jet.getY(), 11, 5);
		this.setFill(new ImagePattern(new Image(Nuke.class.getResource("/assets/Nuke.png").toExternalForm())));
		double angle = jet.getAngle();
		double speed = jet.getSpeed();
		acceleration = speed / 1000;
		xSpeed = speed * Math.cos(Math.toRadians(angle));
		ySpeed = speed * Math.sin(Math.toRadians(angle));
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
