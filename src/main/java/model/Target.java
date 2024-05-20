package model;

import javafx.scene.shape.Rectangle;
import javafx.animation.Transition;
import view.animation.FireAnimation;

public abstract class Target extends Rectangle {

	private Transition animation;
	public Target(double x, double y, double width, double height) {
		super(x, y, width, height);
		this.animation = null;
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

	public void destroy(Game game) {
		stopAnimation();
		game.removeAnimation(animation);
		double x = getX();
		double y = getY();
		Fire fire = new Fire(x, y);
		game.addExplosion(fire);
		game.getRoot().getChildren().add(fire);
		FireAnimation fireAnimation = new FireAnimation(fire, this, game);
		game.addAnimation(fireAnimation);
		fireAnimation.play();

	}

	public void remove(Game game) {
		game.getRoot().getChildren().remove(this);
		game.removeTarget(this);
	}
}
