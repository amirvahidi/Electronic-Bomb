package view.animation;


import enums.Constant;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Explosion;
import model.Game;
import model.Missile;
import model.Target;

import java.util.ArrayList;

public class MissileAnimation extends Transition {

	private Game game;

	Missile missile;
	public MissileAnimation(Missile missile, Game game) {
		this.game = game;
		this.missile = missile;
		setCycleCount(-1);
		setCycleDuration(Duration.millis(10000));
	}

	@Override
	protected void interpolate(double v) {
		missile.move();
		double x = missile.getX();
		double y = missile.getY();
		Pane pane = (Pane) missile.getParent();
		if (x < -missile.getWidth() || x >= pane.getWidth()){
			System.out.println("Missile out of bound");
			pane.getChildren().remove(missile);
			game.removeBomb(missile);
			this.stop();
			game.removeAnimation(this);
		}
		if (y >= pane.getHeight() - game.getEarth().getHeight() / 2){
			System.out.println("Missile hit the ground");
			makeExplosion();
			pane.getChildren().remove(missile);
			game.removeBomb(missile);
			this.stop();
			game.removeAnimation(this);
		}

		if (missile.checkCollision(game)) {
			System.out.println("Missile hit the target");
			makeExplosion();
			pane.getChildren().remove(missile);
			game.removeBomb(missile);
			this.stop();
			game.removeAnimation(this);
		}

	}

	private void makeExplosion() {
		missile.removeTargets(game);
		Explosion explosion = new Explosion(missile.getX() - (Constant.MISSILE_EXPLOSION_WIDTH.getValue() / 2 - missile.getWidth()),
				game.getRoot().getHeight() - game.getEarth().getHeight() / 2 - Constant.MISSILE_EXPLOSION_HEIGHT.getValue(),
				Constant.MISSILE_EXPLOSION_WIDTH.getValue(), Constant.MISSILE_EXPLOSION_HEIGHT.getValue());
		Pane root = game.getRoot();
		root.getChildren().add(explosion);
		game.addExplosion(explosion);
		MissileExplosion missileExplosion = new MissileExplosion(game, explosion);
		game.addAnimation(missileExplosion);
		missileExplosion.play();
	}
}
