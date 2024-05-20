package view.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.effect.ColorAdjust;
import javafx.util.Duration;
import model.Explosion;
import model.Game;
import model.Nuke;
import model.Target;

import java.util.ArrayList;

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
		nuke.move();
		double x = nuke.getX();
		double y = nuke.getY();
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

		if (nuke.checkCollision(game)) {
			makeExplosion();
			System.out.println("Nuke hit the target");
			game.getRoot().getChildren().remove(nuke);
			game.removeBomb(nuke);
			game.removeAnimation(this);
			this.stop();
		}

	}

	private void makeExplosion() {
		nuke.removeTargets(game);
		Explosion explosion = new Explosion();
		Explosion topExplosion = new Explosion();
		double stX = nuke.getX() + nuke.getWidth() / 2;
		double stY = game.getRoot().getHeight() - game.getEarth().getHeight() / 2;
		NukeExplosion nukeExplosion = new NukeExplosion(game, explosion, topExplosion, stX, stY);
		game.getRoot().getChildren().add(explosion);
		game.getRoot().getChildren().add(topExplosion);
		game.addExplosion(explosion);
		game.addExplosion(topExplosion);
		game.addAnimation(nukeExplosion);
		nukeExplosion.play();
	}


}
