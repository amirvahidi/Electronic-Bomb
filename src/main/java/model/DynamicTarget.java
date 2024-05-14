package model;

public abstract class DynamicTarget extends Target {

	double speed;
	boolean goingRight;
	public DynamicTarget(double x, double y, double width, double height, boolean goingRight, double speed) {
		super(x, y, width, height);
		this.goingRight = goingRight;
		this.speed = speed;
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
}
