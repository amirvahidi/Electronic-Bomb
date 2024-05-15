package model;

import enums.Constant;

public class Bunker extends Target {

	public Bunker(double x, double y) {
		super(x, y, Constant.BUNKER_WIDTH.getValue(), Constant.BUNKER_HEIGHT.getValue());
	}

}
