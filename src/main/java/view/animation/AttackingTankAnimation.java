package view.animation;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import model.AttackTank;
import model.AttackingBomb;
import model.AttackingTarget;
import model.Game;

public class AttackingTankAnimation extends Transition {

	private Game game;
	private AttackTank attackTank;

	public AttackingTankAnimation(AttackTank attackTank, Game game) {
		this.game = game;
		this.attackTank = attackTank;
		setCycleCount(-1);
		setCycleDuration(Duration.millis(100));
	}

	@Override
	protected void interpolate(double v) {
		int idx;
		if (v < 0.5){
			idx = 0;
		}
		else {
			idx = 1;
		}
		attackTank.setFill(Game.AnimationPictures.ATTACK_TANK_ANIMATION.getValue()[idx]);
		attackTank.move();


		if (v >= 1 && attackTank.isInRange(game.getJet().getX(), game.getJet().getY())){
			double dx = game.getJet().getX() - (attackTank.getX() + attackTank.getWidth() / 2);
			double dy = game.getJet().getY() - (attackTank.getY() + attackTank.getHeight() / 2);
			AttackingBomb bomb = new AttackingBomb(attackTank.getX() + attackTank.getWidth() / 2,
					attackTank.getY() + attackTank.getHeight() / 2, dx, dy);
			game.addAttackingBomb(bomb);
			game.getRoot().getChildren().add(bomb);
			AttackingBombAnimation bombAnimation = new AttackingBombAnimation(bomb, game);
			game.addAnimation(bombAnimation);
			bomb.setAnimation(bombAnimation);
			bomb.playAnimation();
		}
	}
}
