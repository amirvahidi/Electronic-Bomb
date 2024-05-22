package model;

import enums.Constant;

public class AttackTank extends AttackingTarget {

	public AttackTank(double x, double y, boolean goingRight) {
		super(x, y, Constant.ATTACK_TANK_WIDTH.getValue(), Constant.ATTACK_TANK_HEIGHT.getValue(),
				goingRight, Constant.ATTACK_TANK_SPEED.getValue() * Game.getInstance().getSetting().getDifficulty(), Constant.ATTACK_TANK_RANGE.getValue());
	}

}
