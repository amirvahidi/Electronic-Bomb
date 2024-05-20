package view.menu;

import controller.GeneralController;
import enums.Constant;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;
import view.AppView;
import view.animation.*;

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
		for (int i = 0; i < 1; i++){
			//add base
			double x = Math.random() * Constant.SCENE_WIDTH.getValue();
			double y = Constant.SCENE_HEIGHT.getValue() - earth.getHeight() / 2 - Constant.BASE_HEIGHT.getValue();
			Base base = new Base(x, y);
			root.getChildren().add(base);
			game.addTarget(base);
			Transition baseAnimation = new BaseAnimation(game, base);
			base.setAnimation(baseAnimation);
			game.addAnimation(baseAnimation);
			base.playAnimation();
		}
		for (int i = 0; i < 1; i++){
			//add bunker
			double x = Math.random() * Constant.SCENE_WIDTH.getValue();
			double y = Constant.SCENE_HEIGHT.getValue() - earth.getHeight() / 2 - Constant.BUNKER_HEIGHT.getValue();
			Bunker bunker = new Bunker(x, y);
			root.getChildren().add(bunker);
			game.addTarget(bunker);
			Transition bunkerAnimation = new BunkerAnimation(game, bunker);
			bunker.setAnimation(bunkerAnimation);
			game.addAnimation(bunkerAnimation);
			bunker.playAnimation();
		}
		for (int i = 0; i < 10; i++){
			//add tree
			double x = Math.random() * Constant.SCENE_WIDTH.getValue();
			double y = Constant.SCENE_HEIGHT.getValue() - earth.getHeight() / 2 - Constant.TREE_HEIGHT.getValue();
			//give a random from 1 to 3
			int idx = (int) (Math.random() * 3) + 1;
			Tree tree = new Tree(x, y, idx);
			root.getChildren().add(tree);
			game.addTarget(tree);
		}
		for (int i = 0; i < 2; i++){
			double x = Math.random() * Constant.SCENE_WIDTH.getValue();
			double y = Constant.SCENE_HEIGHT.getValue() - earth.getHeight() / 2 - Constant.TRUCK_HEIGHT.getValue();
			boolean goingRight = Math.random() > 0.5;
			Tank tank = new Tank(x, y, goingRight);
			root.getChildren().add(tank);
			game.addTarget(tank);
			Transition tankAnimation = new TankAnimation(game, tank);
			tank.setAnimation(tankAnimation);
			game.addAnimation(tankAnimation);
			tank.playAnimation();
		}
		for (int i = 0; i < 1; i++){
			//add truck
			double x = Math.random() * Constant.SCENE_WIDTH.getValue();
			double y = Constant.SCENE_HEIGHT.getValue() - earth.getHeight() / 2 - Constant.TRUCK_HEIGHT.getValue();
			boolean goingRight = Math.random() > 0.5;
			Truck truck = new Truck(x, y, goingRight);
			root.getChildren().add(truck);
			game.addTarget(truck);
			Transition truckMoving = new TruckMoving(truck, game);
			truck.setAnimation(truckMoving);
			game.addAnimation(truckMoving);
			truck.playAnimation();
		}
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
		Setting setting = GeneralController.getSetting();
		if (setting.isBlackAndWhite()){
			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setSaturation(-1);
			colorAdjust.setBrightness(-0.5);
			AppView.getStage().getScene().getRoot().setEffect(colorAdjust);
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
				shootMissile();
				break;
			case C:
				shootCluster();
				break;
			case R:
				shootNuke();
				break;
		}
	}

	private void shootNuke() {
		if (!game.getBombs().isEmpty() || game.getNumberOfNuke() == 0) return;
		game.setNumberOfNuke(game.getNumberOfNuke() - 1);
		double x = game.getJet().getX();
		double y = game.getJet().getY();
		double speed = game.getJet().getSpeed();
		double angle = game.getJet().getAngle();
		double xSpeed = speed * Math.cos(Math.toRadians(angle));
		double ySpeed = speed * Math.sin(Math.toRadians(angle));
		Nuke nuke = new Nuke(x, y, xSpeed, ySpeed);
		int index = game.getRoot().getChildren().indexOf(game.getJet());
		game.getRoot().getChildren().add(index, nuke);
		game.addBomb(nuke);
		Transition nukeMoving = new NukeAnimation(nuke, game);
		game.addAnimation(nukeMoving);
		nukeMoving.play();
	}

	private void shootCluster() {
		if (!game.getBombs().isEmpty() || game.getNumberOfCluster() == 0) return;
		game.setNumberOfCluster(game.getNumberOfCluster() - 1);
		double x = game.getJet().getX();
		double y = game.getJet().getY();
		double speed = game.getJet().getSpeed();
		double angle = game.getJet().getAngle();
		double xSpeed = speed * Math.cos(Math.toRadians(angle));
		double ySpeed = speed * Math.sin(Math.toRadians(angle));
		Cluster cluster = new Cluster(x, y, xSpeed, ySpeed);
		int index = game.getRoot().getChildren().indexOf(game.getJet());
		game.getRoot().getChildren().add(index, cluster);
		game.addBomb(cluster);
		Transition clusterMoving = new ClusterAnimation(cluster, game);
		game.addAnimation(clusterMoving);
		clusterMoving.play();
	}

	private void shootMissile() {
		if (!game.getBombs().isEmpty()) return;
		double x = game.getJet().getX();
		double y = game.getJet().getY();
		double speed = game.getJet().getSpeed();
		double angle = game.getJet().getAngle();
		double xSpeed = speed * Math.cos(Math.toRadians(angle));
		double ySpeed = speed * Math.sin(Math.toRadians(angle));
		Missile missile = new Missile(x, y, xSpeed, ySpeed);
		int index = game.getRoot().getChildren().indexOf(game.getJet());
		game.getRoot().getChildren().add(index, missile);
		game.addBomb(missile);
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
