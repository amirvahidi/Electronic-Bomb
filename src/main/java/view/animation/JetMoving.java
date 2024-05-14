package view.animation;

import enums.Constant;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Explosion;
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
		double preAngle = angle;
		double speed = jet.getSpeed();
		double angleChange = 0.2;
		double x = jet.getX();
		double y = jet.getY();
		if (y >= pane.getHeight() - game.getEarth().getHeight() / 2) {
			makeExplosion();
			game.getJet().reset();
			return;
		}
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
		if ((angle > 90 && angle <= 270) != (preAngle > 90 && preAngle <= 270)){
			jet.setScaleY(-jet.getScaleY());
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

	private void makeExplosion() {
		Explosion explosion = new Explosion(jet.getX() - (Constant.JET_EXPLOSION_WIDTH.getValue() - jet.getWidth()) / 2,
				jet.getY() - (Constant.JET_EXPLOSION_HEIGHT.getValue() - jet.getHeight()) / 2,
				Constant.JET_EXPLOSION_WIDTH.getValue(), Constant.JET_EXPLOSION_HEIGHT.getValue());
		Pane root = game.getRoot();
		root.getChildren().add(explosion);
		game.addExplosion(explosion);
		JetExplosion jetExplosion = new JetExplosion(game, explosion);
		game.addAnimation(jetExplosion);
		jetExplosion.play();
	}
}
