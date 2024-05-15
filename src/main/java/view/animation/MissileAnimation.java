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
		double x = missile.getX();
		double y = missile.getY();
		double xSpeed = missile.getXSpeed();
		double ySpeed = missile.getYSpeed();
		double acceleration = missile.getAcceleration();
		x += xSpeed;
		y -= ySpeed;
		ySpeed -= acceleration;
		Pane pane = (Pane) missile.getParent();
		if (x < -missile.getWidth() || x >= pane.getWidth()){
			System.out.println("Missile out of bound");
			pane.getChildren().remove(missile);
			game.removeBomb(missile);
			game.removeAnimation(this);
			this.stop();
		}
		if (y >= pane.getHeight() - game.getEarth().getHeight() / 2) {
			makeExplosion();
			pane.getChildren().remove(missile);
			game.removeBomb(missile);
			game.removeAnimation(this);
			this.stop();
		}
		ArrayList<Target> targets = game.getTargets();
		for (Target target : targets) {
			if (missile.checkCollision(target)) {
				makeExplosion();
				pane.getChildren().remove(missile);
				game.removeBomb(missile);
				game.removeAnimation(this);
				this.stop();
				break;
			}
		}
		missile.setX(x);
		missile.setY(y);
		missile.setRotate(-Math.toDegrees(Math.atan2(ySpeed, xSpeed)));
		missile.setXSpeed(xSpeed);
		missile.setYSpeed(ySpeed);
	}

	private void makeExplosion() {
		ArrayList<Target> targets = game.getTargets();
		for (Target target : targets) {
			if (missile.checkCollision(target)) {
				game.getRoot().getChildren().remove(target);
				game.removeTarget(target);
				break;
			}
		}
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
