package model;

public class Game {
	private Jet jet;
	private Setting setting;

	public Game(Jet jet) {
		this.jet = jet;
		this.setting = new Setting(App.getCurrentUser().getSetting());
	}


}
