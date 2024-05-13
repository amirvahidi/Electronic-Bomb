package view.menu;

import controller.GeneralController;
import controller.SettingMenuController;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Setting;
import view.AppView;

import javax.imageio.ImageIO;
import java.net.URL;

public class SettingMenuViewController extends Application {

	public RadioButton easyButton;
	public RadioButton mediumButton;
	public RadioButton hardButton;
	public RadioButton blackWhiteButton;
	public RadioButton coloringButton;
	public RadioButton wasdKeysButton;
	public RadioButton arrowKeysButton;
	public ImageView soundImageField;
	public ImageView background;

	@Override
	public void start(Stage stage) throws Exception {
		AppView.setStage(stage);
		URL url = getClass().getResource("/FXML/SettingMenu.fxml");
		if (url == null) {
			System.out.println("Couldn't find file: SettingMenu.fxml");
			System.exit(-1);
		}
		Pane root = FXMLLoader.load(url);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void initialize() throws Exception {
		Setting setting = GeneralController.getSetting();
		if (setting.getDifficulty() == 1) {
			easyButton.setSelected(true);
		} else if (setting.getDifficulty() == 2) {
			mediumButton.setSelected(true);
		} else {
			hardButton.setSelected(true);
		}
		if (setting.isBlackAndWhite()) {
			blackWhiteButton.setSelected(true);
		} else {
			coloringButton.setSelected(true);
		}
		if (setting.isArrowKeys()) {
			arrowKeysButton.setSelected(true);
		} else {
			wasdKeysButton.setSelected(true);
		}
		Image soundImage;
		if (setting.isSound()) {
			soundImage = SwingFXUtils.toFXImage(ImageIO.read(getClass().getResource("/assets/soundonbutton.png")), null);
		} else {
			soundImage = SwingFXUtils.toFXImage(ImageIO.read(getClass().getResource("/assets/soundoffbutton.png")), null);
		}
		soundImageField.setImage(soundImage);
		if (setting.isBlackAndWhite()) {
			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setSaturation(-1);
			colorAdjust.setBrightness(-0.5);
			background.setEffect(colorAdjust);
		}
	}

	public void changeDifficulty(MouseEvent mouseEvent) {
		RadioButton selectedButton = (RadioButton) mouseEvent.getSource();
		if (selectedButton.isSelected()) {
			if (selectedButton == easyButton) {
				mediumButton.setSelected(false);
				hardButton.setSelected(false);
			} else if (selectedButton == mediumButton) {
				easyButton.setSelected(false);
				hardButton.setSelected(false);
			} else {
				easyButton.setSelected(false);
				mediumButton.setSelected(false);
			}
		}
		else {
			selectedButton.setSelected(true);
		}
		int difficulty = 1;
		if (mediumButton.isSelected()) {
			difficulty = 2;
		} else if (hardButton.isSelected()) {
			difficulty = 3;
		}
		SettingMenuController.changeDifficulty(difficulty);
	}

	public void changeColoring(MouseEvent mouseEvent) {
		RadioButton selectedButton = (RadioButton) mouseEvent.getSource();
		if (selectedButton.isSelected()) {
			if (selectedButton == blackWhiteButton) {
				coloringButton.setSelected(false);
				ColorAdjust colorAdjust = new ColorAdjust();
				colorAdjust.setSaturation(-1);
				colorAdjust.setBrightness(-0.5);
				background.setEffect(colorAdjust);
			} else {
				blackWhiteButton.setSelected(false);
				background.setEffect(null);
			}
		}
		else {
			selectedButton.setSelected(true);
		}
		SettingMenuController.changeColoring();
	}

	public void changeKeys(MouseEvent mouseEvent) {
		RadioButton selectedButton = (RadioButton) mouseEvent.getSource();
		if (selectedButton.isSelected()) {
			if (selectedButton == arrowKeysButton) {
				wasdKeysButton.setSelected(false);
			} else {
				arrowKeysButton.setSelected(false);
			}
		}
		else {
			selectedButton.setSelected(true);
		}
		SettingMenuController.changeKeys();
	}

	public void changeSound(MouseEvent mouseEvent) throws Exception {
		soundImageField.setImage(SwingFXUtils.toFXImage(GeneralController.getSetting().isSound() ? ImageIO.read(getClass().getResource("/assets/soundoffbutton.png")) : ImageIO.read(getClass().getResource("/assets/soundonbutton.png")), null));
		SettingMenuController.changeSound();
		if (GeneralController.getSetting().isSound()) {
			AppView.getBackgroundMusic().setMute(false);
		} else {
			AppView.getBackgroundMusic().setMute(true);
		}
	}
	public void back(MouseEvent mouseEvent) throws Exception {
		AppView.setCurrentMenu(new MainMenuViewController());
		AppView.getCurrentMenu().start(AppView.getStage());
	}
}
