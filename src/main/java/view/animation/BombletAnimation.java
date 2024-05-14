package view.animation;

import enums.Constant;
import javafx.animation.Transition;
import javafx.util.Duration;
import model.Bomblet;
import model.Explosion;
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
		if (y >= game.getRoot().getHeight() - game.getEarth().getHeight() / 2) {
			makeExplosion();
			System.out.println("Bomblet hit the ground");
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

	private void makeExplosion() {
		Explosion explosion = new Explosion(bomblet.getX() - (Constant.BOMBLET_EXPLOSION_WIDTH.getValue() / 2 - bomblet.getWidth()),
				bomblet.getY() - (Constant.BOMBLET_EXPLOSION_HEIGHT.getValue() - bomblet.getHeight()),
				Constant.BOMBLET_EXPLOSION_WIDTH.getValue(), Constant.BOMBLET_EXPLOSION_HEIGHT.getValue());
		game.getRoot().getChildren().add(explosion);
		game.addExplosion(explosion);
		BombletExplosion bombletExplosion = new BombletExplosion(game, explosion);
		game.addAnimation(bombletExplosion);
		bombletExplosion.play();
	}
}
