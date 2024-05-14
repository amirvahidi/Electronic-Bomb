package enums;

public enum Constant {
	JET_SPEED(0.3),
	JET_WIDTH(27),
	JET_HEIGHT(9),
	MISSILE_WIDTH(11),
	MISSILE_HEIGHT(5),
	CLUSTER_WIDTH(11),
	CLUSTER_HEIGHT(5),
	BOMBLET_WIDTH(5),
	BOMBLET_HEIGHT(3),
	NUKE_WIDTH(14),
	NUKE_HEIGHT(7),
	BOMB_ACCELERATION(0.0005),
	MISSILE_EXPLOSION_WIDTH(68),
	MISSILE_EXPLOSION_HEIGHT(61),
	BOMBLET_EXPLOSION_WIDTH(15),
	BOMBLET_EXPLOSION_HEIGHT(14);

	private final double value;

	Constant(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}
}
