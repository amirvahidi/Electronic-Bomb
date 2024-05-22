package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public abstract class AttackingTarget extends DynamicTarget {

	private double range;
	private Circle rangeCircle;

	public AttackingTarget(double x, double y, double width, double height, boolean goingRight, double speed, double range) {
		super(x, y, width, height, goingRight, speed);
		this.range = range * Game.getInstance().getSetting().getDifficulty();
		rangeCircle = new Circle();
		rangeCircle.setRadius(this.range);
		rangeCircle.setCenterX(x + width / 2);
		rangeCircle.setCenterY(y + height / 2);
		rangeCircle.setStroke(Color.RED);
		rangeCircle.setFill(Color.TRANSPARENT);
		rangeCircle.setStrokeType(StrokeType.OUTSIDE);
		rangeCircle.setStrokeWidth(2);
	}

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public Circle getRangeCircle() {
		return rangeCircle;
	}

	public void setRangeCircle(Circle rangeCircle) {
		this.rangeCircle = rangeCircle;
	}

	@Override
	public void destroy(Game game) {
		game.getRoot().getChildren().remove(rangeCircle);
		super.destroy(game);
	}

	@Override
	public void remove(Game game) {
		game.getRoot().getChildren().remove(rangeCircle);
		super.remove(game);
	}

	@Override
	public void move() {
		super.move();
		rangeCircle.setCenterX(getX() + getWidth() / 2);
	}

	public boolean isInRange(double x, double y) {
		double centerX = getX() + getWidth() / 2;
		double centerY = getY() + getHeight() / 2;
		return Math.sqrt(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2)) <= range;
	}
}
