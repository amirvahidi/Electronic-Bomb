package view.animation;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Game;
import model.Nuke;

public class NukeAnimation extends Transition {

	private Game game;
	private Nuke nuke;

	public NukeAnimation(Nuke nuke, Game game) {
		this.game = game;
		this.nuke = nuke;
		setCycleCount(-1);
		setCycleDuration(Duration.millis(10000));
	}

	@Override
	protected void interpolate(double v) {
		double x = nuke.getX();
		double y = nuke.getY();
		double xSpeed = nuke.getXSpeed();
		double ySpeed = nuke.getYSpeed();
		double acceleration = nuke.getAcceleration();
		x += xSpeed;
		y -= ySpeed;
		ySpeed -= acceleration;
		if (x < -nuke.getWidth() || x >= game.getRoot().getWidth()){
			game.getRoot().getChildren().remove(nuke);
			game.removeBomb(nuke);
			game.removeAnimation(this);
			this.stop();
		}
		nuke.setX(x);
		nuke.setY(y);
		nuke.setRotate(-Math.toDegrees(Math.atan2(ySpeed, xSpeed)));
		nuke.setXSpeed(xSpeed);
		nuke.setYSpeed(ySpeed);
	}
}
