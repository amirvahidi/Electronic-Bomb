package view.animation;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Bomblet;
import model.Game;

public class BombletAnimation extends Transition {

	private Game game;
	private Bomblet bomblet;

	public BombletAnimation(Bomblet bomblet, Game game) {
		this.game = game;
		this.bomblet = bomblet;
		setCycleCount(-1);
		setCycleDuration(Duration.millis(10000));
	}

	@Override
	protected void interpolate(double v) {
		double x = bomblet.getX();
		double y = bomblet.getY();
		double xSpeed = bomblet.getXSpeed();
		double ySpeed = bomblet.getYSpeed();
		double acceleration = bomblet.getAcceleration();
		x += xSpeed;
		y -= ySpeed;
		ySpeed -= acceleration;
		if (x < -bomblet.getWidth() || x >= game.getRoot().getWidth()){
			game.getRoot().getChildren().remove(bomblet);
			game.removeBomb(bomblet);
			game.removeAnimation(this);
			this.stop();
		}
		bomblet.setX(x);
		bomblet.setY(y);
		bomblet.setRotate(-Math.toDegrees(Math.atan2(ySpeed, xSpeed)));
		bomblet.setXSpeed(xSpeed);
		bomblet.setYSpeed(ySpeed);
	}
}
