package view;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class AppView {

	private static MediaPlayer backgroundMusic;

	private static Stage stage;
	private static Application currentMenu;

	public static void setStage(Stage stage) {
		AppView.stage = stage;
	}

	public static Stage getStage() {
		return AppView.stage;
	}

	public static Application getCurrentMenu() {
		return AppView.currentMenu;
	}

	public static void setCurrentMenu(Application currentMenu) {
		AppView.currentMenu = currentMenu;
	}

	public static MediaPlayer getBackgroundMusic() {
		return backgroundMusic;
	}

	public static void setBackgroundMusic(MediaPlayer backgroundMusic) {
		AppView.backgroundMusic = backgroundMusic;
	}
}
