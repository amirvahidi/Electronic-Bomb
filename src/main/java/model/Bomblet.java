package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Bomblet extends Bomb {

	private double xSpeed, ySpeed;
	private double acceleration;

	public Bomblet(double x, double y, double xSpeed, double ySpeed, double acceleration) {
		super(x, y, 5, 3);
		this.setFill(new ImagePattern(new Image(Bomblet.class.getResource("/assets/Bomblet.png").toExternalForm())));
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.acceleration = acceleration;
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
