package view.animation;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Bonus;
import model.Game;
import model.NukeBonus;

public class BonusAnimation extends Transition {

	private Game game;

	private Bonus bonus;

	public BonusAnimation(Game game, Bonus Bonus) {
		this.game = game;
		this.bonus = Bonus;
		setCycleCount(-1);
		setCycleDuration(Duration.millis(1000));
	}

	@Override
	protected void interpolate(double frac) {
		bonus.move();
		if (bonus.getY() < -bonus.getHeight()) {
			System.out.println("Nuke Bonus is removed");
			game.getBonuses().remove(bonus);
			game.getRoot().getChildren().remove(bonus);
			game.removeAnimation(this);
			stop();
		}
		if (bonus.isCollidingWith(game.getJet())) {
			System.out.println("Nuke Bonus is collected");
			bonus.remove(game);
		}
	}
}
