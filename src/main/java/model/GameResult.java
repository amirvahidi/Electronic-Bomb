package model;

import javafx.collections.ObservableList;

public class GameResult {
	private final String username;
	private final int score;
	private final int wave;
	private final double accuracy;
	private final int kill;



	public GameResult(Game game){
		username = App.getCurrentUser().getUsername();
		score = game.getNumberOfKill() * game.getSetting().getDifficulty();
		wave = game.getWaveNumber();
		if (game.getNumberOfShoot() == 0)
			accuracy = 0;
		else
			accuracy = (double) game.getNumberOfKill() / game.getNumberOfShoot();
		kill = game.getNumberOfKill();
	}

	public String getUsername() {
		return username;
	}

	public int getScore() {
		return score;
	}

	public int getWave() {
		return wave;
	}

	public double getAccuracy() {
		return accuracy;
	}
	public int getKill() {
		return kill;
	}

}
