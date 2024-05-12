package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.App;
import model.Setting;

import java.net.URL;

public class MainMenuViewController extends Application {

	public ImageView iconField;
	public Label usernameField;
	public ImageView background;

	@Override
	public void start(Stage stage) throws Exception {
		URL url = getClass().getResource("/FXML/MainMenu.fxml");
		if (url == null) {
			System.out.println("Couldn't find file: MainMenu.fxml");
			System.exit(-1);
		}
		Pane newRoot = FXMLLoader.load(url);
		Scene newScene = new Scene(newRoot);
		AppView.getStage().setScene(newScene);
	}

	@FXML
	public void initialize() throws Exception {
		usernameField.setText(App.getCurrentUser().getUsername());
		iconField.setImage(App.getCurrentUser().getIcon());
		Setting setting = App.getCurrentUser().getSetting();
		if (setting.isBlackAndWhite()) {
			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setSaturation(-1);
			colorAdjust.setBrightness(-0.5);
			background.setEffect(colorAdjust);
		}
	}

	public void startGame(MouseEvent mouseEvent) {

	}

	public void setting(MouseEvent mouseEvent) throws Exception {
		AppView.setCurrentMenu(new SettingMenuViewController());
		AppView.getCurrentMenu().start(AppView.getStage());
	}

	public void profile(MouseEvent mouseEvent) throws Exception {
		AppView.setCurrentMenu(new ProfileMenuViewController());
		AppView.getCurrentMenu().start(AppView.getStage());
	}

	public void highScore(MouseEvent mouseEvent) {
	}

	public void exit(MouseEvent mouseEvent) {
		System.exit(0);
	}

	public void newGame(MouseEvent mouseEvent) {
	}

	public void continueGame(MouseEvent mouseEvent) {
	}
}
