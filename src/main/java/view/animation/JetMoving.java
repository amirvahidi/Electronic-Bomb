package view.animation;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.Jet;

public class JetMoving extends Transition {

	private Game game;
	private Jet jet;

	public JetMoving(Jet jet, Game game) {
		this.game = game;
		this.jet = jet;
		setCycleCount(-1);
		setCycleDuration(Duration.millis(10000));
	}
	@Override
	protected void interpolate(double v) {
		Pane pane = (Pane) jet.getParent();
		double angle = jet.getAngle();
		double speed = jet.getSpeed();
		double angleChange = 0.2;
		double x = jet.getX();
		double y = jet.getY();
		if (y <= jet.getWidth() && angle > 0 && angle < 90){
			angle -= 3 * angleChange;
		}
		else if (y <= jet.getWidth() && angle < 180 && angle >= 90){
			angle += 3 * angleChange;
		}
		else if (jet.isGoingUp()){
			if (angle > 90 && angle <= 270){
				angle -= angleChange;
			}
			else if (angle < 90 || angle >= 270){
				angle += angleChange;
			}
		}
		else if (jet.isGoingDown()){
			if (angle >= 90 && angle < 270){
				angle += angleChange;
			}
			else if (angle <= 90 || angle > 270){
				angle -= angleChange;
			}
		}
		else if (jet.isGoingLeft()){
			if (angle >= 0 && angle < 180){
				angle += angleChange;
			}
			else if (angle <= 0 || angle > 180){
				angle -= angleChange;
			}
		}
		else if (jet.isGoingRight()){
			if (angle > 0 && angle <= 180){
				angle -= angleChange;
			}
			else if (angle < 0 || angle >= 180){
				angle += angleChange;
			}
		}
		x += speed * Math.cos(Math.toRadians(angle));
		y -= speed * Math.sin(Math.toRadians(angle));
		if (x <= -jet.getWidth()){
			x = pane.getWidth();
		}
		else if (x >= pane.getWidth()){
			x = -jet.getWidth();
		}
		jet.setRotate(-angle);
		jet.setAngle(angle);
		jet.setX(x);
		jet.setY(y);
	}
}
