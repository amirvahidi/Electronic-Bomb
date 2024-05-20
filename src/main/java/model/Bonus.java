package model;

import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;

public abstract class Bonus extends Rectangle {

	private Transition animation = null;
	private double speed;
	public Bonus(double x, double y, double width, double height, double speed) {
		super(x, y, width, height);
		this.speed = speed;
	}

	public void move() {
		this.setY(this.getY() - speed);
	}

	public boolean isCollidingWith(Jet jet) {
		return this.getBoundsInParent().intersects(jet.getBoundsInParent());
	}

	public Transition getAnimation() {
		return animation;
	}

	public void setAnimation(Transition animation) {
		this.animation = animation;
	}

	public void playAnimation() {
		if (animation == null) return;
		animation.play();
	}

	public void stopAnimation() {
		if (animation == null) return;
		animation.stop();
	}

	public void pauseAnimation() {
		if (animation == null) return;
		animation.pause();
	}

	public abstract void remove(Game game);
}
