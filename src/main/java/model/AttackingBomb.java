package model;

import enums.Constant;
import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;


public class AttackingBomb extends Rectangle {

	private double xSpeed, ySpeed;

	private Transition animation = null;

	public AttackingBomb(double x, double y, double dx, double dy) {
		super(x, y, Constant.ATTACKING_BOMB_WIDTH.getValue(), Constant.ATTACKING_BOMB_HEIGHT.getValue());
		setX(x);
		setY(y);
		double angle = Math.atan2(dy, dx);
		this.setRotate(Math.toDegrees(angle));
		this.xSpeed = Constant.ATTACKING_BOMB_SPEED.getValue() * Math.cos(angle);
		this.ySpeed = Constant.ATTACKING_BOMB_SPEED.getValue() * Math.sin(angle);
	}

	public void move() {
		setX(getX() + xSpeed);
		setY(getY() + ySpeed);
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

	public void pauseAnimation() {
		animation.pause();
	}

	public double getXSpeed() {
		return xSpeed;
	}

	public double getYSpeed() {
		return ySpeed;
	}
}
