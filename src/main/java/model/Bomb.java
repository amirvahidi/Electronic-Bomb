package model;

import enums.Constant;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public abstract class Bomb extends Rectangle {

	private double xSpeed, ySpeed, acceleration, range;

	private Transition animation = null;
	public Bomb(double x, double y, double width, double height, double xSpeed, double ySpeed, double range) {
		super(width, height);
		setX(x);
		setY(y);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.range = range;
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

	public Transition getAnimation() {
		return animation;
	}

	public void setAnimation(Transition animation) {
		this.animation = animation;
	}

	public void playAnimation() {
		animation.play();
	}

	public void stopAnimation() {
		animation.stop();
	}

	public boolean checkCollision(Target target) {
		return this.getBoundsInParent().intersects(target.getBoundsInParent());
	}

	public boolean checkInRange(double x, double y) {
		double dx = Math.abs(this.getX() - x);
		double dy = Math.abs(this.getY() - y);
		double distance = Math.sqrt(dx * dx + dy * dy);
		return distance <= this.range;
	}

	public void move() {
		this.setX(this.getX() + this.xSpeed);
		this.setY(this.getY() - this.ySpeed);
		this.ySpeed -= this.acceleration;
		double angle = Math.toDegrees(Math.atan2(this.ySpeed, this.xSpeed));
		this.setRotate(-angle);
	}

	public boolean checkCollision(Game game){
		ArrayList<Target> targets = game.getTargets();
		for (Target target : targets) {
			if (this.checkCollision(target)) {
				return true;
			}
		}
		return false;
	}

	public void removeTargets(Game game){
		ArrayList<Target> targets = game.getTargets();
		for (Target target : targets) {
			if (this.checkInRange(target.getX(), target.getY())) {
				target.destroy(game);
			}
		}
	}
}
