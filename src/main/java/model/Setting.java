package model;

public class Setting {
	private boolean sound;
	private boolean blackAndWhite;
	private boolean arrowKeys;
	private int difficulty;

	public Setting() {
		this.sound = true;
		this.blackAndWhite = false;
		this.arrowKeys = false;
		this.difficulty = 1;
	}

	public Setting(Setting setting) {
		this.sound = setting.isSound();
		this.blackAndWhite = setting.isBlackAndWhite();
		this.arrowKeys = setting.isArrowKeys();
		this.difficulty = setting.getDifficulty();
	}

	public boolean isSound() {
		return this.sound;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}

	public boolean isBlackAndWhite() {
		return this.blackAndWhite;
	}

	public void setBlackAndWhite(boolean blackAndWhite) {
		this.blackAndWhite = blackAndWhite;
	}

	public boolean isArrowKeys() {
		return this.arrowKeys;
	}

	public void setArrowKeys(boolean arrowKeys) {
		this.arrowKeys = arrowKeys;
	}

	public int getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
