package controller;

import model.App;
import model.Setting;

public class SettingMenuController {
	public static void changeDifficulty(int difficulty) {
		Setting setting = App.getCurrentUser().getSetting();
		setting.setDifficulty(difficulty);
	}

	public static void changeColoring() {
		Setting setting = App.getCurrentUser().getSetting();
		setting.setBlackAndWhite(!setting.isBlackAndWhite());
	}

	public static void changeKeys() {
		Setting setting = App.getCurrentUser().getSetting();
		setting.setArrowKeys(!setting.isArrowKeys());
	}

	public static void changeSound() {
		Setting setting = App.getCurrentUser().getSetting();
		setting.setSound(!setting.isSound());
	}
}
