package model;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Game {
	private Jet jet;
	private Pane root;
	private Rectangle earth;
	private Setting setting;

	private ArrayList<Bomb> bombs = new ArrayList<>();

	private ArrayList<Transition> animations = new ArrayList<>();

	private ArrayList<Explosion> explosions = new ArrayList<>();

	private ArrayList<Target> targets = new ArrayList<>();

	public Game() {
		this.setting = new Setting(App.getCurrentUser().getSetting());
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
		return bombs;
	}

	public void addBomb(Bomb bomb) {
		bombs.add(bomb);
	}

	public void removeBomb(Bomb bomb) {
		bombs.remove(bomb);
	}

	public ArrayList<Transition> getAnimations() {
		return animations;
	}

	public void addAnimation(Transition animation) {
		animations.add(animation);
	}

	public void removeAnimation(Transition animation) {
		animations.remove(animation);
	}

	public ArrayList<Explosion> getExplosions() {
		return explosions;
	}

	public void addExplosion(Explosion explosion) {
		explosions.add(explosion);
	}

	public void removeExplosion(Explosion explosion) {
		explosions.remove(explosion);
	}

	public ArrayList<Target> getTargets() {
		return targets;
	}

	public void addTarget(Target target) {
		targets.add(target);
	}

	public void removeTarget(Target target) {
		targets.remove(target);
	}

	public Pane getRoot() {
		return root;
	}

	public void setRoot(Pane root) {
		this.root = root;
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
		GRASS(new ImagePattern(new Image(Images.class.getResource("/assets/Grass.png").toExternalForm())));
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
				new ImagePattern(new Image(Images.class.getResource("/assets/bunker/1.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/bunker/2.png").toExternalForm()))
		}),
		BUNKER_ANIMATION(new ImagePattern[]{
				new ImagePattern(new Image(Images.class.getResource("/assets/bunker/1.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/bunker/2.png").toExternalForm()))
		}),
		TANK_ANIMATION(new ImagePattern[]{
				new ImagePattern(new Image(Images.class.getResource("/assets/tank/1.png").toExternalForm())),
				new ImagePattern(new Image(Images.class.getResource("/assets/tank/2.png").toExternalForm()))
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
