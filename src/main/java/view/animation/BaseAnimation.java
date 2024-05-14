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
		int idx2;
		if (v < 0.5){
			idx2 = 1;
		}
		else{
			idx2 = 2;
		}
		base.setFill(new ImagePattern(new Image(Base.class.getResource("/assets/base/" + base.getIdx() + "/" + idx2 + ".png").toExternalForm())));
	}
}
