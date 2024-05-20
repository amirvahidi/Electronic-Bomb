package view.animation;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Game;
import model.Tank;

public class TankAnimation extends Transition {

	private Game game;
	private Tank tank;

	public TankAnimation(Game game, Tank tank){
		this.game = game;
		this.tank = tank;
		this.setCycleCount(-1);
		this.setCycleDuration(Duration.millis(1000));
	}
	
	@Override
	protected void interpolate(double v){
		tank.move();
		int idx;
		if (v < 0.5){
			idx = 0;
		}
		else{
			idx = 1;
		}
		tank.setFill(Game.AnimationPictures.TANK_ANIMATION.getValue()[idx]);
	}
}
