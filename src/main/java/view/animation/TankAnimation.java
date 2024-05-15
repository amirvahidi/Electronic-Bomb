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
		double x = tank.getX();
		double y = tank.getY();
		double speed = tank.getSpeed();
		if (tank.isGoingRight()) x += speed;
		else x -= speed;
		if (x >= game.getRoot().getWidth()) {
			x = -tank.getWidth();
		}
		else if (x <= -tank.getWidth()) {
			x = game.getRoot().getWidth();
		}
		tank.setX(x);
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
