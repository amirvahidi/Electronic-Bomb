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

	private ArrayList<Missile> missiles = new ArrayList<>();

	private ArrayList<Transition> animations = new ArrayList<>();

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

	public ArrayList<Missile> getMissiles() {
		return missiles;
	}

	public void addMissile(Missile missile) {
		missiles.add(missile);
	}

	public void removeMissile(Missile missile) {
		missiles.remove(missile);
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

	public Pane getRoot() {
		return root;
	}

	public void setRoot(Pane root) {
		this.root = root;
	}
}
