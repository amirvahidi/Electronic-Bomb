package model;

import enums.Constant;

public class Fire extends Explosion {
	public Fire(double x, double y) {
		super(x, y, Constant.FIRE_WIDTH.getValue(), Constant.FIRE_HEIGHT.getValue());
	}
}
