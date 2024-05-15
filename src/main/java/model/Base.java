package model;

import enums.Constant;

public class Base extends Target {
	public Base(double x, double y) {
		super(x, y, Constant.BASE_WIDTH.getValue(), Constant.BASE_HEIGHT.getValue());
	}
}
