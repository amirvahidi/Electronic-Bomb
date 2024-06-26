package view.animation;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Base;
import model.Game;

public class BaseAnimation extends Transition {

	private Game game;
	private Base base;

	public BaseAnimation(Game game, Base base) {
		this.game = game;
		this.base = base;
		this.setCycleCount(-1);
		this.setCycleDuration(Duration.millis(1000));
	}

	@Override
	protected void interpolate(double v) {
		int idx;
		if (v < 0.5){
			idx = 0;
		}
		else{
			idx = 1;
		}
		base.setFill(Game.AnimationPictures.BASE_ANIMATION.getValue()[idx]);
	}
}
