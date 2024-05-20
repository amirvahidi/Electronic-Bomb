package view.animation;

import javafx.animation.Transition;
import model.Game;
import model.Truck;

public class TruckMoving extends Transition {

	private Game game;

	private Truck truck;

	public TruckMoving(Truck truck, Game game) {
		this.game = game;
		this.truck = truck;
		setCycleCount(-1);
		setCycleDuration(javafx.util.Duration.millis(10000));
	}

	@Override
	protected void interpolate(double v) {
		truck.move();
	}
}
