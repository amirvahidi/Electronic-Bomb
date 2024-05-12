package controller;

import model.App;
import model.Setting;

public class GeneralController {

	public static Setting getSetting() {
		return App.getCurrentUser().getSetting();
	}
}
