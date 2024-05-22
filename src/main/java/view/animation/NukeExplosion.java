package view.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Explosion;
import model.Game;

public class NukeExplosion extends Transition {

	private Game game;

	private Explosion topExplosion;
	private Explosion explosion;

	double stX, stY;
	int count = 0;

	public NukeExplosion(Game game, Explosion explosion, Explosion topExplosion, double stX, double stY) {
		this.game = game;
		this.explosion = explosion;
		this.topExplosion = topExplosion;
		this.stX = stX;
		this.stY = stY;
		setCycleCount(1);
		setCycleDuration(javafx.util.Duration.millis(5000));
	}

	@Override
	protected void interpolate(double v) {
		count++;
		if (count == 1) {
			//make flash effect
			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setBrightness(1);
			game.getRoot().setEffect(colorAdjust);
			Timeline flashTimeline = new Timeline(
					new KeyFrame(Duration.seconds(0), new KeyValue(colorAdjust.brightnessProperty(), 1)),
					new KeyFrame(Duration.seconds(1), new KeyValue(colorAdjust.brightnessProperty(), 0))
			);

			flashTimeline.play();
		}
		int topCnt = 4;
		topExplosion.setFill(Game.AnimationPictures.NUKE_EXPLOSION.getValue()[count / 50 % topCnt]);
		explosion.setFill(Game.AnimationPictures.NUKE_EXPLOSION.getValue()[topCnt]);
		Pane root = game.getRoot();
		v += 0.3;
		double explosionWidth = (root.getWidth() / 6) * v;
		double explosionHeight = (root.getHeight() / 3) * v;
		double topExplosionWidth = (root.getWidth() / 3) * v;
		double topExplosionHeight = (root.getHeight() / 6) * v;
		explosion.setWidth(explosionWidth);
		explosion.setHeight(explosionHeight);
		topExplosion.setWidth(topExplosionWidth);
		topExplosion.setHeight(topExplosionHeight);
		explosion.setX(stX - explosionWidth / 2);
		explosion.setY(stY - explosionHeight);
		topExplosion.setX(stX - topExplosionWidth / 2);
		topExplosion.setY(stY + explosionHeight / 10 - explosionHeight - topExplosionHeight);
		if (v >= 1) {
			game.getRoot().getChildren().remove(explosion);
			game.getRoot().getChildren().remove(topExplosion);
			game.removeExplosion(explosion);
			game.removeExplosion(topExplosion);
			this.stop();
			game.removeAnimation(this);
		}
	}
}
