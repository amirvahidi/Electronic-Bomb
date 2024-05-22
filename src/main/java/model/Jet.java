package model;

import controller.GameMenuController;
import enums.Constant;
import enums.PicturesAddress;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.AppView;
import view.animation.JetExplosion;
import view.menu.MainMenuViewController;

public class Jet extends Rectangle {

	private double speed;
	private double angle;

	private boolean isGoingUp = false;
	private boolean isGoingDown = false;
	private boolean isGoingLeft = false;
	private boolean isGoingRight = false;

	private Transition animation = null;

	public Jet() {
		super(Constant.JET_WIDTH.getValue(), Constant.JET_HEIGHT.getValue());
		speed = Constant.JET_SPEED.getValue();
		angle = 0;
		this.setStrokeWidth(0);
		this.setFill(Game.Images.JET.getValue());
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		while(angle < 0)
			angle += 360;
		while(angle >= 360)
			angle -= 360;
		this.angle = angle;
	}

	public boolean isGoingUp() {
		return isGoingUp;
	}

	public void setGoingUp(boolean goingUp) {
		isGoingUp = goingUp;
	}

	public boolean isGoingDown() {
		return isGoingDown;
	}

	public void setGoingDown(boolean goingDown) {
		isGoingDown = goingDown;
	}

	public boolean isGoingLeft() {
		return isGoingLeft;
	}

	public void setGoingLeft(boolean goingLeft) {
		isGoingLeft = goingLeft;
	}

	public boolean isGoingRight() {
		return isGoingRight;
	}

	public void setGoingRight(boolean goingRight) {
		isGoingRight = goingRight;
	}

	public Transition getAnimation() {
		return animation;
	}

	public void setAnimation(Transition animation) {
		this.animation = animation;
	}

	public void playAnimation() {
		animation.play();
	}

	public void stopAnimation() {
		animation.stop();
	}

	public void pauseAnimation() {
		animation.pause();
	}

	public void updateAngle(){
		double angle = this.getAngle();
		double angleChange = 0.2;
		double y = this.getY();
		if (y <= this.getWidth() && angle > 0 && angle < 90){
			angle -= 3 * angleChange;
		}
		else if (y <= this.getWidth() && angle < 180 && angle >= 90){
			angle += 3 * angleChange;
		}
		else if (this.isGoingUp()){
			if (angle > 90 && angle <= 270){
				angle -= angleChange;
			}
			else if (angle < 90 || angle >= 270){
				angle += angleChange;
			}
		}
		else if (this.isGoingDown()){
			if (angle >= 90 && angle < 270){
				angle += angleChange;
			}
			else if (angle <= 90 || angle > 270){
				angle -= angleChange;
			}
		}
		else if (this.isGoingLeft()){
			if (angle >= 0 && angle < 180){
				angle += angleChange;
			}
			else if (angle <= 0 || angle > 180){
				angle -= angleChange;
			}
		}
		else if (this.isGoingRight()){
			if (angle > 0 && angle <= 180){
				angle -= angleChange;
			}
			else if (angle < 0 || angle >= 180){
				angle += angleChange;
			}
		}
		this.setAngle(angle);
	}

	public void move() {
		GameMenuController.checkEndWave();
		Pane pane = (Pane) this.getParent();
		double angle = this.getAngle();
		double speed = this.getSpeed();
		double x = this.getX();
		double y = this.getY();
		if (angle > 90 && angle <= 270){
			this.setScaleY(-1);
		}
		else{
			this.setScaleY(1);
		}
		x += speed * Math.cos(Math.toRadians(angle));
		y -= speed * Math.sin(Math.toRadians(angle));
		if (x <= -this.getWidth()){
			x = pane.getWidth();
		}
		else if (x >= pane.getWidth()){
			x = -this.getWidth();
		}
		this.setRotate(-angle);
		this.setAngle(angle);
		this.setX(x);
		this.setY(y);
	}

	public void explode(Game game) {
		Explosion explosion = new Explosion(this.getX() - (Constant.JET_EXPLOSION_WIDTH.getValue() - this.getWidth()) / 2,
				this.getY() - (Constant.JET_EXPLOSION_HEIGHT.getValue() - this.getHeight()) / 2,
				Constant.JET_EXPLOSION_WIDTH.getValue(), Constant.JET_EXPLOSION_HEIGHT.getValue());
		Pane root = game.getRoot();
		root.getChildren().add(explosion);
		game.addExplosion(explosion);
		JetExplosion jetExplosion = new JetExplosion(game, explosion);
		game.addAnimation(jetExplosion);
		jetExplosion.play();
		GameMenuController.removeLive();
		if (game.getNumberOfLive() == 0){
			game.gameOver();
			Application menu = new MainMenuViewController();
			AppView.setCurrentMenu(menu);
			try {
				menu.start(AppView.getStage());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void reset() {
		this.setX(0);
		this.setY(0);
		this.setSpeed(Constant.JET_SPEED.getValue());
		this.setAngle(0);
		this.setRotate(0);
		this.setScaleY(1);
		this.setGoingUp(false);
		this.setGoingDown(false);
		this.setGoingLeft(false);
		this.setGoingRight(false);
	}
}
