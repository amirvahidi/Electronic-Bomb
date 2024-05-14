package model;

import enums.Constant;

public class Base extends Target {

	int idx;
	public Base(double x, double y, int idx) {
		super(x, y, Constant.BASE_WIDTH.getValue(), Constant.BASE_HEIGHT.getValue());
		this.idx = idx;
	}

	public int getIdx() {
		return idx;
	}
}
