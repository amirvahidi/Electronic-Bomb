package view.menu;

import controller.GeneralController;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;
import view.AppView;
import view.animation.JetMoving;
import view.animation.MissileAnimation;

import java.net.URL;

public class GameMenuViewController extends Application {
	public Game game;

	@Override
	public void start(Stage stage) throws Exception {
		game = new Game();
		AppView.setStage(stage);
		URL url = getClass().getResource("/FXML/GameMenu.fxml");
		if (url == null) {
			System.out.println("Couldn't find file: GameMenu.fxml");
			System.exit(-1);
		}
		Pane root = FXMLLoader.load(url);
		game.setRoot(root);
		Jet jet = new Jet();
		game.setJet(jet);
		root.getChildren().add(jet);
		jet.setOnKeyPressed(this::keyPressed);
		jet.setOnKeyReleased(this::keyReleased);
		Rectangle earth = new Rectangle(0,337 - 38,600, 38);
		earth.setStrokeWidth(0);
		earth.setFill(new Color(91/255.0, 187/255.0, 0, 1));
		game.setEarth(earth);
		root.getChildren().add(earth);
		Group grasses = new Group();
		for (int i = 0; i < 20; i++){
			Grass grass = new Grass();
			grass.setY(earth.getY() + Math.random() * earth.getHeight());
			grass.setX(earth.getX() + Math.random() * earth.getWidth());
			grasses.getChildren().add(grass);
		}
		root.getChildren().add(grasses);
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
	}

	public void keyPressed(KeyEvent keyEvent) {
		System.out.println(keyEvent.getCode());
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
				shootMissile();
				break;
		}
	}

	private void shootMissile() {
		Missile missile = new Missile(game.getJet());
		int index = game.getRoot().getChildren().indexOf(game.getJet());
		game.getRoot().getChildren().add(index, missile);
		game.addMissile(missile);
		Transition missileMoving = new MissileAnimation(missile, game);
		game.addAnimation(missileMoving);
		missileMoving.play();
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
