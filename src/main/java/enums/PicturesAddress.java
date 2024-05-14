package enums;

public enum PicturesAddress {
	JET("/assets/Jet.png"),
	MISSILE("/assets/Missile.png"),
	CLUSTER("/assets/Cluster.png"),
	BOMBLET("/assets/Bomblet.png"),
	NUKE("/assets/Nuke.png");

	private final String value;

	PicturesAddress(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
