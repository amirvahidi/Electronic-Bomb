package view.animation;

import enums.Constant;
import javafx.animation.Transition;
import javafx.util.Duration;
import model.Bomblet;
import model.Explosion;
import model.Game;
import model.Target;

import java.util.ArrayList;

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
		bomblet.move();
		double x = bomblet.getX();
		double y = bomblet.getY();
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
		if (bomblet.checkCollision(game)) {
			makeExplosion();
			System.out.println("Bomblet hit the target");
			game.getRoot().getChildren().remove(bomblet);
			game.removeBomb(bomblet);
			game.removeAnimation(this);
			this.stop();
		}
	}

	private void makeExplosion() {
		bomblet.removeTargets(game);
		Explosion explosion = new Explosion(bomblet.getX() - (Constant.BOMBLET_EXPLOSION_WIDTH.getValue() / 2 - bomblet.getWidth()),
				game.getRoot().getHeight() - game.getEarth().getHeight() / 2 - Constant.BOMBLET_EXPLOSION_HEIGHT.getValue(),
				Constant.BOMBLET_EXPLOSION_WIDTH.getValue(), Constant.BOMBLET_EXPLOSION_HEIGHT.getValue());
		game.getRoot().getChildren().add(explosion);
		game.addExplosion(explosion);
		BombletExplosion bombletExplosion = new BombletExplosion(game, explosion);
		game.addAnimation(bombletExplosion);
		bombletExplosion.play();
	}
}
