package view.animation;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Bunker;
import model.Game;

public class BunkerAnimation extends Transition {

	private Game game;
	private Bunker bunker;

	public BunkerAnimation(Game game, Bunker bunker) {
		this.game = game;
		this.bunker = bunker;
		this.setCycleCount(-1);
		this.setCycleDuration(Duration.millis(1000));
	}

	@Override
	protected void interpolate(double v) {
		int idx;
		if (v < 0.5) {
			idx = 0;
		}
		else {
			idx = 1;
		}
		bunker.setFill(Game.AnimationPictures.BUNKER_ANIMATION.getValue()[idx]);
	}
}
