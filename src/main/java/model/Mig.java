package model;

import enums.Constant;

public class Mig extends AttackingTarget {

	public Mig(double x, double y, boolean goingRight) {
		super(x, y, Constant.MIG_WIDTH.getValue(), Constant.MIG_HEIGHT.getValue(), goingRight,
				Constant.MIG_SPEED.getValue(), Constant.MIG_RANGE.getValue());
		this.setFill(Game.Images.MIG.getValue());
	}
}
