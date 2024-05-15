package enums;

public enum Constant {
	SCENE_WIDTH(600),
	SCENE_HEIGHT(337),
	JET_SPEED(0.3),
	JET_WIDTH(27),
	JET_HEIGHT(9),
	MISSILE_WIDTH(11),
	MISSILE_HEIGHT(5),
	MISSILE_RANGE(20),
	CLUSTER_WIDTH(11),
	CLUSTER_HEIGHT(5),
	BOMBLET_WIDTH(5),
	BOMBLET_HEIGHT(3),
	BOMBLET_RANGE(10),
	NUKE_WIDTH(14),
	NUKE_HEIGHT(7),
	NUKE_RANGE(100),
	BOMB_ACCELERATION(0.0005),
	MISSILE_EXPLOSION_WIDTH(68),
	MISSILE_EXPLOSION_HEIGHT(61),
	BOMBLET_EXPLOSION_WIDTH(15),
	BOMBLET_EXPLOSION_HEIGHT(14),
	JET_EXPLOSION_WIDTH(30),
	JET_EXPLOSION_HEIGHT(30),
	TRUCK_WIDTH(36),
	TRUCK_HEIGHT(17),
	TRUCK_SPEED(0.1),

	TANK_WIDTH(32),
	TANK_HEIGHT(16),
	TANK_SPEED(0.1),
	TREE_WIDTH(18),
	TREE_HEIGHT(35),
	BASE_WIDTH(48),
	BASE_HEIGHT(40),
	BUNKER_WIDTH(48),
	BUNKER_HEIGHT(40);

	private final double value;

	Constant(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}
}
