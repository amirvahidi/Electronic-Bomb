package model;

import controller.GameMenuController;
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
		if (animation == null) System.out.println("lolololo");
		if (animation != null) animation.stop();
	}

	public boolean checkCollision(Target target) {
		return this.getBoundsInParent().intersects(target.getBoundsInParent());
	}

	public boolean checkInRange(Target target) {
		for (int i = 0; i < 4; i++){
			double x = target.getX() + (i % 2 == 1? target.getWidth(): 0);
			double y = target.getY() + ((i / 2) % 2 == 1? target.getHeight(): 0);
			double dx = Math.abs(getX() - x);
			double dy = Math.abs(getY() - y);
			if (Math.sqrt(dx * dx + dy * dy) <= range) return true;
		}
		return false;
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
			if (this.checkInRange(target)) {
				target.destroy(game);
			}
		}
	}
}
