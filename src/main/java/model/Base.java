package model;

import enums.Constant;
import javafx.animation.Transition;
import view.animation.BonusAnimation;

public class Base extends Target {
	public Base(double x, double y) {
		super(x, y, Constant.BASE_WIDTH.getValue(), Constant.BASE_HEIGHT.getValue());
	}

	@Override
	public void destroy(Game game) {
		NukeBonus nukeBonus = new NukeBonus(getX(), getY());
		game.addBonus(nukeBonus);
		game.getRoot().getChildren().add(nukeBonus);
		BonusAnimation nukeBonusAnimation = new BonusAnimation(game, nukeBonus);
		game.addAnimation(nukeBonusAnimation);
		nukeBonus.setAnimation(nukeBonusAnimation);
		nukeBonusAnimation.play();
		super.destroy(game);
	}
}
