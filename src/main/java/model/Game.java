package model;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
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

	public Pane getRoot() {
		return root;
	}

	public void setRoot(Pane root) {
		this.root = root;
	}

}
