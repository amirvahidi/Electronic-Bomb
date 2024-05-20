package view.animation;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.AttackingBomb;
import model.Game;

public class AttackingBombAnimation extends Transition {
	private Game game;
	private AttackingBomb bomb;

	public AttackingBombAnimation(AttackingBomb bomb, Game game) {
		this.game = game;
		this.bomb = bomb;
		setCycleCount(-1);
		setCycleDuration(Duration.millis(1000));
	}

	@Override
	protected void interpolate(double v) {
		int idx = 0;
		if (v < 0.5){
			idx = 0;
		}
		else {
			idx = 1;
		}
		bomb.setFill(Game.AnimationPictures.ATTACKING_BOMB_ANIMATION.getValue()[idx]);
		bomb.move();
		double x = bomb.getX();
		double y = bomb.getY();
		if (x < -bomb.getWidth() || x >= game.getRoot().getWidth()){
			System.out.println("Bomb out of bounds");
			game.getRoot().getChildren().remove(bomb);
			game.removeAttackingBomb(bomb);
			game.removeAnimation(this);
			this.stop();
		}
		if (y < 0 || y >= game.getRoot().getHeight()){
			System.out.println("Bomb out of bounds");
			game.getRoot().getChildren().remove(bomb);
			game.removeAttackingBomb(bomb);
			game.removeAnimation(this);
			this.stop();
		}
		if (game.getJet().intersects(bomb.getBoundsInLocal())){
			System.out.println("Jet hit by bomb");
			game.getJet().explode(game);
			game.getJet().reset();
		}
	}
}
