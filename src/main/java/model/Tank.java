package model;

import enums.Constant;

public class Tank extends DynamicTarget {

	public Tank(double x, double y, boolean goingRight){
		super(x, y, Constant.TANK_WIDTH.getValue(), Constant.TANK_HEIGHT.getValue(),  goingRight, Constant.TANK_SPEED.getValue());
	}
}
