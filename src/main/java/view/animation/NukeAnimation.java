package view.animation;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Explosion;
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
		if (y >= game.getRoot().getHeight() - game.getEarth().getHeight() / 2) {
			makeExplosion();
			System.out.println("Nuke hit the ground");
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

	private void makeExplosion() {
		Explosion explosion = new Explosion();
		Explosion topExplosion = new Explosion();
		double stX = nuke.getX() + nuke.getWidth() / 2;
		double stY = nuke.getY() + nuke.getHeight() / 2;
		NukeExplosion nukeExplosion = new NukeExplosion(game, explosion, topExplosion, stX, stY);
		game.getRoot().getChildren().add(explosion);
		game.getRoot().getChildren().add(topExplosion);
		game.addExplosion(explosion);
		game.addExplosion(topExplosion);
		game.addAnimation(nukeExplosion);
		nukeExplosion.play();
	}


}
