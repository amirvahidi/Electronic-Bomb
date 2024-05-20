package view.animation;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Explosion;
import model.Game;
import model.Target;

public class FireAnimation extends Transition {

	private Game game;

	private Explosion explosion;
	private Target target;
	public FireAnimation(Explosion explosion, Target target, Game game) {
		this.game = game;
		this.explosion = explosion;
		this.target = target;
		setCycleCount(1);
		setCycleDuration(Duration.millis(1000));
	}

	@Override
	protected void interpolate(double v) {
		int lim = 7;
		v *= lim;
		int tmp = (int) v % Game.AnimationPictures.FIRE_ANIMATION.getValue().length;
		explosion.setFill(Game.AnimationPictures.FIRE_ANIMATION.getValue()[tmp]);
		if (v >= lim){
			game.getRoot().getChildren().remove(explosion);
			game.removeExplosion(explosion);
			game.removeAnimation(this);
			this.stop();
			target.remove(game);
		}
	}
}
