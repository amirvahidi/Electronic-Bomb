package model;

import javafx.animation.Transition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.AppView;
import view.animation.JetMoving;
import view.menu.MainMenuViewController;

import java.util.ArrayList;

public class Game {
	private static Game instance = null;
	private Jet jet;
	private Pane root;
	private Rectangle earth;

	private Label killCountField;
	private Label liveCountField;
	private Label clusterCountField;
	private Label nukeCountField;
	private Setting setting;

	private int numberOfKill = 0;
	private int numberOfShoot = 0;

	private int numberOfWaveKill = 0;
	private int numberOfWaveShoot = 0;
	private int numberOfCluster = 0;
	private int numberOfNuke = 0;
	private int numberOfLive = 3;

	private int waveNumber = 1;

	private ArrayList<Bonus> bonuses = new ArrayList<>();
	private ArrayList<Bomb> bombs = new ArrayList<>();

	private ArrayList<AttackingBomb> attackingBombs = new ArrayList<>();

	private ArrayList<Transition> animations = new ArrayList<>();

	private ArrayList<Explosion> explosions = new ArrayList<>();

	private ArrayList<Target> targets = new ArrayList<>();

	private ArrayList<Node> additions = new ArrayList<>();

	public Game() {
		this.setting = new Setting(App.getCurrentUser().getSetting());
	}

	public static Game getInstance() {
		return instance;
	}

	public static void setInstance(Game instance) {
		Game.instance = instance;
	}


	public Jet getJet() {
		return jet;
	}

	public void setJet(Jet jet) {
		this.jet = jet;
	}

	public Rectangle getEarth() {
		return earth;
	}

	public void setEarth(Rectangle earth) {
		this.earth = earth;
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public ArrayList<Bomb> getBombs() {
		return new ArrayList<>(bombs);
	}

	public void addBomb(Bomb bomb) {
		bombs.add(bomb);
	}

	public void removeBomb(Bomb bomb) {
		bombs.remove(bomb);
	}

	public ArrayList<Transition> getAnimations() {
		return new ArrayList<>(animations);
	}

	public void addAnimation(Transition animation) {
		animations.add(animation);
	}

	public void removeAnimation(Transition animation) {
		animations.remove(animation);
	}

	public ArrayList<Explosion> getExplosions() {
		return new ArrayList<>(explosions);
	}

	public void addExplosion(Explosion explosion) {
		explosions.add(explosion);
	}

	public void removeExplosion(Explosion explosion) {
		explosions.remove(explosion);
	}

	public ArrayList<Target> getTargets() {
		return new ArrayList<>(targets);
	}

	public void addTarget(Target target) {
		targets.add(target);
	}

	public void removeTarget(Target target) {
		targets.remove(target);
	}

	public ArrayList<Node> getAdditions(){
		return new ArrayList<>(additions);
	}

	public void addAddition(Node node){
		additions.add(node);
	}

	public void removeAddition(Node node){
		additions.remove(node);
	}

	public ArrayList<Bonus> getBonuses() {
		return new ArrayList<>(bonuses);
	}

	public void addBonus(Bonus bonus) {
		bonuses.add(bonus);
	}

	public void removeBonus(Bonus bonus) {
		bonuses.remove(bonus);
	}

	public ArrayList<AttackingBomb> getAttackingBombs() {
		return new ArrayList<>(attackingBombs);
	}

	public void addAttackingBomb(AttackingBomb bomb) {
		attackingBombs.add(bomb);
	}

	public void removeAttackingBomb(AttackingBomb bomb) {
		attackingBombs.remove(bomb);
	}

	public Pane getRoot() {
		return root;
	}

	public void setRoot(Pane root) {
		this.root = root;
	}

	public int getNumberOfCluster() {
		return numberOfCluster;
	}

	public void setNumberOfCluster(int numberOfCluster) {
		this.numberOfCluster = numberOfCluster;
	}

	public int getNumberOfNuke() {
		return numberOfNuke;
	}

	public void setNumberOfNuke(int numberOfNuke) {
		this.numberOfNuke = numberOfNuke;
	}

	public int getNumberOfLive() {
		return numberOfLive;
	}

	public void setNumberOfLive(int numberOfLive) {
		this.numberOfLive = numberOfLive;
	}

	public void gameOver() {
		//TODO:
		for (Transition animation : animations) {
			animation.stop();
		}
		setInstance(null);
		Application menu = new MainMenuViewController();
		AppView.setCurrentMenu(menu);
		try {
			menu.start(AppView.getStage());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int getWaveNumber() {
		return waveNumber;
	}

	public void setWaveNumber(int waveNumber) {
		this.waveNumber = waveNumber;
	}

	public void clear() {
		ArrayList<Transition> animations2 = new ArrayList<>(animations);
		for (Transition animation: animations2){
			if (animation instanceof JetMoving) continue;
			animation.stop();
			additions.remove(animation);
		}
		for (Bomb bomb: bombs){
			getRoot().getChildren().remove(bomb);
		}
		for (Explosion explosion: explosions){
			getRoot().getChildren().remove(explosion);
		}
		ArrayList<Target> targets1 = new ArrayList<>(targets);
		for (Target target: targets1){
			target.remove(this);
		}
		for (Node addition: additions){
			getRoot().getChildren().remove(addition);
		}
		for (Bonus bonus: bonuses){
			getRoot().getChildren().remove(bonus);
		}
		for (AttackingBomb attackingBomb: attackingBombs){
			getRoot().getChildren().remove(attackingBomb);
		}
		bombs.clear();
		explosions.clear();
		targets.clear();
		additions.clear();
		bonuses.clear();
		attackingBombs.clear();
	}

	public Label getKillCountField() {
		return killCountField;
	}

	public void setKillCountField(Label killCountField) {
		this.killCountField = killCountField;
	}

	public Label getLiveCountField() {
		return liveCountField;
	}

	public void setLiveCountField(Label liveCountField) {
		this.liveCountField = liveCountField;
	}

	public Label getClusterCountField() {
		return clusterCountField;
	}

	public void setClusterCountField(Label clusterCountField) {
		this.clusterCountField = clusterCountField;
	}

	public Label getNukeCountField() {
		return nukeCountField;
	}

	public void setNukeCountField(Label nukeCountField) {
		this.nukeCountField = nukeCountField;
	}

	public int getNumberOfKill() {
		return numberOfKill;
	}

	public void setNumberOfKill(int numberOfKill) {
		this.numberOfKill = numberOfKill;
	}

	public int getNumberOfShoot() {
		return numberOfShoot;
	}

	public void setNumberOfShoot(int numberOfShoot) {
		this.numberOfShoot = numberOfShoot;
	}

	public int getNumberOfWaveKill() {
		return numberOfWaveKill;
	}

	public void setNumberOfWaveKill(int numberOfWaveKill) {
		this.numberOfWaveKill = numberOfWaveKill;
	}

	public enum Images {

		JET(new ImagePattern(new Image(Images.class.getResource("/assets/Jet.png").toExternalForm()))),
		MISSILE(new ImagePattern(new Image(Images.class.getResource("/assets/Missile.png").toExternalForm()))),
		CLUSTER(new ImagePattern(new Image(Images.class.getResource("/assets/Cluster.png").toExternalForm()))),
		BOMBLET(new ImagePattern(new Image(Images.class.getResource("/assets/Bomblet.png").toExternalForm()))),
		NUKE(new ImagePattern(new Image(Images.class.getResource("/assets/Nuke.png").toExternalForm()))),
		TRUCK(new ImagePattern(new Image(Images.class.getResource("/assets/Truck.png").toExternalForm()))),
		TREE_1(new ImagePattern(new Image(Images.class.getResource("/assets/tree/1.png").toExternalForm()))),
		TREE_2(new ImagePattern(new Image(Images.class.getResource("/assets/tree/2.png").toExternalForm()))),
		TREE_3(new ImagePattern(new Image(Images.class.getResource("/assets/tree/3.png").toExternalForm()))),
		GRASS(new ImagePattern(new Image(Images.class.getResource("/assets/Grass.png").toExternalForm()))),
		MIG(new ImagePattern(new Image(Images.class.getResource("/assets/mig.png").toExternalForm()))),
		NUKE_BONUS(new ImagePattern(new Image(Images.class.getResource("/assets/nukebonus.png").toExternalForm()))),
		CLUSTER_BONUS(new ImagePattern(new Image(Images.class.getResource("/assets/clusterbonus.png").toExternalForm())));

		private final ImagePattern value;

		Images(ImagePattern value) {
			this.value = value;
		}

		public ImagePattern getValue() {
			return value;
		}
	}

	public enum AnimationPictures {

		MISSILE_EXPLOSION(new ImagePattern[]{
				new ImagePattern(new Image(Images.class.getResource("/assets/missileexplosion/1.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/missileexplosion/2.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/missileexplosion/3.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/missileexplosion/4.png").toExternalForm()))
		}),
		BOMBLET_EXPLOSION(new ImagePattern[]{
				new ImagePattern(new Image(Images.class.getResource("/assets/bombletexplosion/1.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/bombletexplosion/2.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/bombletexplosion/3.png").toExternalForm()))
		}),
		NUKE_EXPLOSION(new ImagePattern[]{
				new ImagePattern(new Image(Images.class.getResource("/assets/nukeexplosion/1.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/nukeexplosion/2.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/nukeexplosion/3.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/nukeexplosion/4.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/nukeexplosion/NukeStem.png").toExternalForm()))
		}),
		JET_EXPLOSION(new ImagePattern[]{
				new ImagePattern(new Image(Images.class.getResource("/assets/jetexplosion/1.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/jetexplosion/2.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/jetexplosion/3.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/jetexplosion/4.png").toExternalForm()))
		}),
		BASE_ANIMATION(new ImagePattern[]{
				new ImagePattern(new Image(Images.class.getResource("/assets/base/1.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/base/2.png").toExternalForm()))
		}),
		BUNKER_ANIMATION(new ImagePattern[]{
				new ImagePattern(new Image(Images.class.getResource("/assets/bunker/1.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/bunker/2.png").toExternalForm()))
		}),
		TANK_ANIMATION(new ImagePattern[]{
				new ImagePattern(new Image(Images.class.getResource("/assets/tank/1.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/tank/2.png").toExternalForm()))
		}),
		ATTACK_TANK_ANIMATION(new ImagePattern[]{
				new ImagePattern(new Image(Images.class.getResource("/assets/attacktank/1.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/attacktank/2.png").toExternalForm()))
		}),
		FIRE_ANIMATION(new ImagePattern[]{
				new ImagePattern(new Image(Images.class.getResource("/assets/fire/1.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/fire/2.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/fire/3.png").toExternalForm()))
		}),
		ATTACKING_BOMB_ANIMATION(new ImagePattern[]{
				new ImagePattern(new Image(Images.class.getResource("/assets/attackingbomb/1.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/attackingbomb/2.png").toExternalForm()))
		});

		private final ImagePattern[] value;

		AnimationPictures(ImagePattern[] value) {
			this.value = value;
		}

		public ImagePattern[] getValue(){
			return this.value;
		}
	}
}
