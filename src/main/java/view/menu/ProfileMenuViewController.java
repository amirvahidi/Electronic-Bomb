package view.menu;

import controller.ProfileMenuController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.App;
import model.Result;
import model.Setting;
import view.AlertView;
import view.AppView;

import java.net.URL;

public class ProfileMenuViewController extends Application {

	public PasswordField passwordField;
	public TextField usernameField;
	public ImageView background;

	@Override
	public void start(Stage stage) throws Exception {
		URL url = getClass().getResource("/FXML/ProfileMenu.fxml");
		if (url == null) {
			System.out.println("Couldn't find file: ProfileMenu.fxml");
			System.exit(-1);
		}
		Pane newRoot = FXMLLoader.load(url);
		Scene newScene = new Scene(newRoot);
		AppView.getStage().setScene(newScene);
	}

	@FXML
	public void initialize() throws Exception {
		usernameField.setText(App.getCurrentUser().getUsername());
		passwordField.setText(App.getCurrentUser().getPassword());
		usernameField.setFocusTraversable(false);
		Setting setting = App.getCurrentUser().getSetting();
		if (setting.isBlackAndWhite()) {
			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setSaturation(-1);
			colorAdjust.setBrightness(-0.5);
			background.setEffect(colorAdjust);
		}
	}

	public void changeUsername(MouseEvent mouseEvent) {
		String newUsername = usernameField.getText();
		Result result = ProfileMenuController.changeUsername(newUsername);
		AlertView.showResult("Change Username", result);
	}

	public void changePassword(MouseEvent mouseEvent) {
		String newPassword = passwordField.getText();
		Result result = ProfileMenuController.changePassword(newPassword);
		AlertView.showResult("Change Password", result);
	}

	public void changeAvatar(MouseEvent mouseEvent) throws Exception{
		AppView.setCurrentMenu(new AvatarMenuViewController());
		AppView.getCurrentMenu().start(AppView.getStage());
	}

	public void deleteAccount(MouseEvent mouseEvent) throws Exception {
		Result result = ProfileMenuController.deleteAccount();
		AlertView.showResult("Delete Account", result);
		AppView.setCurrentMenu(new LoginMenuViewController());
		AppView.getCurrentMenu().start(AppView.getStage());
	}

	public void logout(MouseEvent mouseEvent) throws Exception {
		Result result = ProfileMenuController.logout();
		AlertView.showResult("Logout", result);
		AppView.setCurrentMenu(new LoginMenuViewController());
		AppView.getCurrentMenu().start(AppView.getStage());
	}

	public void back(MouseEvent mouseEvent) throws Exception {
		AppView.setCurrentMenu(new MainMenuViewController());
		AppView.getCurrentMenu().start(AppView.getStage());
	}

}
