package view.menu;

import controller.GameMenuController;
import controller.GeneralController;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;
import view.AppView;
import view.animation.*;

import java.net.URL;

public class GameMenuViewController extends Application {
	public Game game;
	public Label killCount;
	public Label livesCount;
	public Label nukeCount;
	public Label clusterCount;
	public Label waveField;
	public Pane root;

	@Override
	public void start(Stage stage) throws Exception {
		GameMenuController.setViewController(this);
		game = new Game();
		Game.setInstance(game);
		URL url = getClass().getResource("/FXML/GameMenu.fxml");
		if (url == null) {
			System.out.println("Couldn't find file: GameMenu.fxml");
			System.exit(-1);
		}
		Pane root = FXMLLoader.load(url);
		game.setRoot(root);
		Rectangle earth = new Rectangle(0,337 - 38,600, 38);
		earth.setStrokeWidth(0);
		earth.setFill(new Color(91/255.0, 187/255.0, 0, 1));
		game.setEarth(earth);
		root.getChildren().add(earth);
		Group grasses = new Group();
		for (int i = 0; i < 20; i++) {
			Grass grass = new Grass();
			grass.setY(earth.getY() + Math.random() * earth.getHeight());
			grass.setX(earth.getX() + Math.random() * earth.getWidth());
			grasses.getChildren().add(grass);
		}
		root.getChildren().add(grasses);
		Jet jet = new Jet();
		game.setJet(jet);
		root.getChildren().add(jet);
		GameMenuController.createWave();
		jet.setOnKeyPressed(this::keyPressed);
		jet.setOnKeyReleased(this::keyReleased);
		Transition jetMoving = new JetMoving(jet, game);
		game.addAnimation(jetMoving);
		jetMoving.play();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		jet.requestFocus();
	}

	@FXML
	public void initialize() throws Exception {
		GameMenuController.setClusterCount(clusterCount);
		GameMenuController.setKillCount(killCount);
		GameMenuController.setLivesCount(livesCount);
		GameMenuController.setNukeCount(nukeCount);
		GameMenuController.setWaveField(waveField);
		GameMenuController.setValues();
		Setting setting = GeneralController.getSetting();
		if (setting.isBlackAndWhite()) {
			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setSaturation(-1);
			colorAdjust.setBrightness(-0.5);
			root.setEffect(colorAdjust);
		}
	}

	public void keyPressed(KeyEvent keyEvent) {
		Setting setting = GeneralController.getSetting();
		Jet jet = (Jet) keyEvent.getSource();
		if (setting.isArrowKeys()){
			switch (keyEvent.getCode()) {
				case UP:
					jet.setGoingUp(true);
					break;
				case DOWN:
					jet.setGoingDown(true);
					break;
				case LEFT:
					jet.setGoingLeft(true);
					break;
				case RIGHT:
					jet.setGoingRight(true);
					break;
			}
		} else {
			switch (keyEvent.getCode()) {
				case W:
					jet.setGoingUp(true);
					break;
				case S:
					jet.setGoingDown(true);
					break;
				case A:
					jet.setGoingLeft(true);
					break;
				case D:
					jet.setGoingRight(true);
					break;
			}
		}
		switch (keyEvent.getCode()) {
			case SPACE:
				GameMenuController.shootMissile();
				break;
			case C:
				GameMenuController.shootCluster();
				break;
			case R:
				GameMenuController.shootNuke();
				break;
			case P:
				GameMenuController.skipWave();
				break;
			case G:
				GameMenuController.addNuke();
				break;
			case CONTROL:
				GameMenuController.addCluster();
				break;
			case T:
				GameMenuController.createTank();
				break;
			case H:
				GameMenuController.addLive();
				break;
		}
	}


	public void keyReleased(KeyEvent keyEvent) {
		Jet jet = (Jet) keyEvent.getSource();
		Setting setting = GeneralController.getSetting();
		if (setting.isArrowKeys()){
			switch (keyEvent.getCode()) {
				case UP:
					jet.setGoingUp(false);
					break;
				case DOWN:
					jet.setGoingDown(false);
					break;
				case LEFT:
					jet.setGoingLeft(false);
					break;
				case RIGHT:
					jet.setGoingRight(false);
					break;
			}
		} else {
			switch (keyEvent.getCode()) {
				case W:
					jet.setGoingUp(false);
					break;
				case S:
					jet.setGoingDown(false);
					break;
				case A:
					jet.setGoingLeft(false);
					break;
				case D:
					jet.setGoingRight(false);
					break;
			}
		}
	}
}
