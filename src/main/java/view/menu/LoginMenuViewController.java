package view.menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Result;
import controller.LoginMenuController;
import view.AlertView;
import view.AppView;

import java.net.URL;

public class LoginMenuViewController extends Application {


	public TextField usernameField;
	public PasswordField passwordField;

	public void Register(MouseEvent mouseEvent) throws Exception {
		String username = usernameField.getText();
		String password = passwordField.getText();
		Result result = LoginMenuController.Register(username, password);
		AlertView.showResult("Register",result);
	}

	public void Login(MouseEvent mouseEvent) throws Exception{
		String username = usernameField.getText();
		String password = passwordField.getText();
		Result result = LoginMenuController.Login(username, password);
		AlertView.showResult("Login",result);
		if (result.isSuccess()) {
			switchToMainMenu();
		}
	}

	public void Guest(MouseEvent mouseEvent) throws Exception{
		Result result = LoginMenuController.Guest();
		AlertView.showResult("Guest",result);
		switchToMainMenu();
	}

	private void switchToMainMenu() throws Exception{
		AppView.setCurrentMenu(new MainMenuViewController());
		AppView.getCurrentMenu().start(AppView.getStage());
	}

	@Override
	public void start(Stage stage) throws Exception {
		AppView.setStage(stage);
		URL url = getClass().getResource("/FXML/LoginMenu.fxml");
		if (url == null) {
			System.out.println("Couldn't find file: LoginMenu.fxml");
			System.exit(-1);
		}
		Pane root = FXMLLoader.load(url);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		if (AppView.getBackgroundMusic() == null){
			Media media = new Media(getClass().getResource("/assets/sound/Background.mp3").toExternalForm());
			AppView.setBackgroundMusic(new MediaPlayer(media));
			AppView.getBackgroundMusic().setCycleCount(MediaPlayer.INDEFINITE);
			AppView.getBackgroundMusic().play();
		}
		if (AppView.getBackgroundMusic().isMute()){
			AppView.getBackgroundMusic().setMute(false);
		}
	}

	public void run(String[] args) {
		launch(args);
	}

}
