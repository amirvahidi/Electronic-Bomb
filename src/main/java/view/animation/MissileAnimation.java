package view.animation;


import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.Missile;

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
		missile.setX(x);
		missile.setY(y);
		missile.setRotate(-Math.toDegrees(Math.atan2(ySpeed, xSpeed)));
		missile.setXSpeed(xSpeed);
		missile.setYSpeed(ySpeed);
	}
}
