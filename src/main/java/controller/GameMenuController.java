package controller;

import enums.Constant;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.*;
import view.animation.*;
import view.menu.GameMenuViewController;

public class GameMenuController {

	private static GameMenuViewController viewController;

	private static Label killCount;
	private static Label livesCount;
	private static Label nukeCount;
	private static Label clusterCount;

	private static Label waveField;

	public static void shootNuke() {
		addShoot();
		Game game = Game.getInstance();
		if (!game.getBombs().isEmpty() || game.getNumberOfNuke() == 0) return;
		removeNuke();
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

	private static void addShoot() {
		Game game = Game.getInstance();
		game.setNumberOfShoot(game.getNumberOfShoot() + 1);
		game.setNumberOfWaveShoot(game.getNumberOfWaveShoot() + 1);
		setValues();
	}

	public static void shootCluster() {
		addShoot();
		Game game = Game.getInstance();
		if (!game.getBombs().isEmpty() || game.getNumberOfCluster() == 0) return;
		removeCluster();
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

	public static void shootMissile() {
		addShoot();
		Game game = Game.getInstance();
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

	public static void createWave(){
		Game game = Game.getInstance();
		int waveNumber = game.getWaveNumber();
		if (waveNumber == 4) {
			game.gameOver();
			return;
		}
		for (int i = 0; i < 5; i++){
			createTree();
		}
		for (int i = 0; i < 1; i++){
			createBase();
		}
		for (int i = 0; i < 1; i++){
			createBunker();
		}
		for (int i = 0; i < 3; i++){
			createTank();
		}
		for (int i = 0; i < 1; i++){
			createTruck();
		}
		if (waveNumber == 2){
			createAttackTank();
		}
		if (waveNumber == 3){
			createMig();
		}
		setValues();
	}

	public static void createTree(){
		Game game = Game.getInstance();
		Pane root = game.getRoot();
		Rectangle earth = game.getEarth();
		double x = Math.random() * Constant.SCENE_WIDTH.getValue();
		double y = Constant.SCENE_HEIGHT.getValue() - earth.getHeight() / 2 - Constant.TREE_HEIGHT.getValue();
		int idx = (int) (Math.random() * 3) + 1;
		Tree tree = new Tree(x, y, idx);
		root.getChildren().add(tree);
		game.addTarget(tree);
	}

	public static void createBase(){
		Game game = Game.getInstance();
		Pane root = game.getRoot();
		Rectangle earth = game.getEarth();
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

	public static void createBunker(){
		Game game = Game.getInstance();
		Pane root = game.getRoot();
		Rectangle earth = game.getEarth();
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

	public static void createTank() {
		Game game = Game.getInstance();
		Pane root = game.getRoot();
		Rectangle earth = game.getEarth();
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

	public static void createTruck(){
		Game game = Game.getInstance();
		Pane root = game.getRoot();
		Rectangle earth = game.getEarth();
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

	public static void createAttackTank(){
		Game game = Game.getInstance();
		Pane root = game.getRoot();
		Rectangle earth = game.getEarth();
		double x = Math.random() * Constant.SCENE_WIDTH.getValue();
		double y = Constant.SCENE_HEIGHT.getValue() - earth.getHeight() / 2 - Constant.ATTACK_TANK_HEIGHT.getValue();
		boolean goingRight = Math.random() > 0.5;
		AttackTank attackingTank = new AttackTank(x, y, goingRight);
		root.getChildren().add(attackingTank);
		game.addTarget(attackingTank);
		root.getChildren().add(attackingTank.getRangeCircle());
		Transition attackingTankAnimation = new AttackingTankAnimation(attackingTank, game);
		attackingTank.setAnimation(attackingTankAnimation);
		game.addAnimation(attackingTankAnimation);
		attackingTank.playAnimation();
	}

	public static void createMig(){
		Game game = Game.getInstance();
		Pane root = game.getRoot();
		Rectangle earth = game.getEarth();
		double x = Math.random() * Constant.SCENE_WIDTH.getValue();
		double y = Math.random() * Constant.SCENE_HEIGHT.getValue();
		boolean goingRight = Math.random() > 0.5;
		Mig mig = new Mig(x, y, goingRight);
		root.getChildren().add(mig);
		game.addTarget(mig);
		root.getChildren().add(mig.getRangeCircle());
		Transition migMoving = new MigAnimation(game, mig);
		mig.setAnimation(migMoving);
		game.addAnimation(migMoving);
		mig.playAnimation();
	}

	public static void checkEndWave() {
		Game game = Game.getInstance();
		if (game.getTargets().isEmpty()){
			//TODO: show accuracy of this wave;
			game.setNumberOfWaveKill(0);
			game.setNumberOfWaveShoot(0);
			game.setWaveNumber(game.getWaveNumber() + 1);
			createWave();
		}
	}

	public static void skipWave() {
		Game game = Game.getInstance();
		game.clear();
		game.setWaveNumber(game.getWaveNumber() + 1);
		createWave();
	}

	public static void addNuke(){
		Game game = Game.getInstance();
		game.setNumberOfNuke(game.getNumberOfNuke() + 1);
		setValues();
	}

	public static void removeNuke(){
		Game game = Game.getInstance();
		game.setNumberOfNuke(game.getNumberOfNuke() - 1);
		setValues();
	}

	public static void addCluster(){
		Game game = Game.getInstance();
		game.setNumberOfCluster(game.getNumberOfCluster() + 1);
		setValues();
	}

	public static void removeCluster(){
		Game game = Game.getInstance();
		game.setNumberOfCluster(game.getNumberOfCluster() - 1);
		setValues();
	}

	public static void addLive() {
		Game game = Game.getInstance();
		int cnt = game.getNumberOfLive();
		if (cnt == 3) return;
		game.setNumberOfLive(cnt + 1);
		setValues();
	}

	public static void removeLive() {
		Game game = Game.getInstance();
		int cnt = game.getNumberOfLive();
		game.setNumberOfLive(cnt - 1);
		setValues();
	}

	public static void addKill(){
		Game game = Game.getInstance();
		game.setNumberOfWaveKill(game.getNumberOfWaveKill() + 1);
		game.setNumberOfKill(game.getNumberOfKill() + 1);
		setValues();
	}

	public static void removeKill(){
		Game game = Game.getInstance();
		game.setNumberOfWaveKill(game.getNumberOfWaveKill() - 1);
		game.setNumberOfKill(game.getNumberOfKill() - 1);
		setValues();

	}
	public static void setValues() {
		Game game = Game.getInstance();
		int lives = game.getNumberOfLive();
		int nuke = game.getNumberOfNuke();
		int cluster = game.getNumberOfCluster();
		int kill = game.getNumberOfKill();
		int wave = game.getWaveNumber();
		livesCount.setText(String.valueOf(lives));
		nukeCount.setText(String.valueOf(nuke));
		clusterCount.setText(String.valueOf(cluster));
		killCount.setText(String.valueOf(kill));
		waveField.setText("Wave: " + wave);
	}

	public static GameMenuViewController getViewController() {
		return viewController;
	}

	public static void setViewController(GameMenuViewController viewController) {
		GameMenuController.viewController = viewController;
	}

	public static Label getKillCount() {
		return killCount;
	}

	public static void setKillCount(Label killCount) {
		GameMenuController.killCount = killCount;
	}

	public static Label getLivesCount() {
		return livesCount;
	}

	public static void setLivesCount(Label livesCount) {
		GameMenuController.livesCount = livesCount;
	}

	public static Label getNukeCount() {
		return nukeCount;
	}

	public static void setNukeCount(Label nukeCount) {
		GameMenuController.nukeCount = nukeCount;
	}

	public static Label getClusterCount() {
		return clusterCount;
	}

	public static void setClusterCount(Label clusterCount) {
		GameMenuController.clusterCount = clusterCount;
	}

	public static void setWaveField(Label waveField) {
		GameMenuController.waveField = waveField;
	}
}
