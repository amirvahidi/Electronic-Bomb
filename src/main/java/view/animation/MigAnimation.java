package view.animation;

import javafx.animation.Transition;
import model.AttackingBomb;
import model.Game;
import model.Mig;

public class MigAnimation extends Transition {

	private Game game;
	private Mig mig;

	public MigAnimation(Game game, Mig mig) {
		this.game = game;
		this.mig = mig;
		setCycleCount(-1);
		setCycleDuration(javafx.util.Duration.millis(100));
	}

	@Override
	protected void interpolate(double v) {
		mig.move();
		if (v >= 1 && mig.isInRange(game.getJet().getX(), game.getJet().getY())) {
			double dx = game.getJet().getX() - (mig.getX() + mig.getWidth() / 2);
			double dy = game.getJet().getY() - (mig.getY() + mig.getHeight() / 2);
			AttackingBomb bomb = new AttackingBomb(mig.getX() + mig.getWidth() / 2,
					mig.getY() + mig.getHeight() / 2, dx, dy);
			game.addAttackingBomb(bomb);
			game.getRoot().getChildren().add(bomb);
			AttackingBombAnimation bombAnimation = new AttackingBombAnimation(bomb, game);
			game.addAnimation(bombAnimation);
			bomb.setAnimation(bombAnimation);
			bomb.playAnimation();
		}
	}
}
