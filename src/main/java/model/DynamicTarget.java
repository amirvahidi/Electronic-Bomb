package model;

import enums.Constant;
import javafx.animation.Transition;

public abstract class DynamicTarget extends Target {

	double speed;
	boolean goingRight;
	public DynamicTarget(double x, double y, double width, double height, boolean goingRight, double speed) {
		super(x, y, width, height);
		this.goingRight = goingRight;
		this.speed = speed;
		if (!goingRight) {
			this.setScaleX(-1);
		}
	}

	public double getSpeed() {
		return speed;
	}

	public boolean isGoingRight() {
		return goingRight;
	}

	public void setGoingRight(boolean goingRight) {
		this.goingRight = goingRight;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void move(){
		if (goingRight) {
			setX(getX() + speed);
		} else {
			setX(getX() - speed);
		}
		if (getX() <= 0){
			goingRight = true;
			setScaleX(1);
		}
		if (getX() >= Constant.SCENE_WIDTH.getValue() - getWidth()){
			goingRight = false;
			setScaleX(-1);
		}
	}
}
