package model;

import enums.Constant;
import javafx.animation.Transition;
import view.animation.BonusAnimation;

public class Bunker extends Target {

	public Bunker(double x, double y) {
		super(x, y, Constant.BUNKER_WIDTH.getValue(), Constant.BUNKER_HEIGHT.getValue());
	}

	@Override
	public void destroy(Game game) {
		ClusterBonus clusterBonus = new ClusterBonus(getX(), getY());
		game.addBonus(clusterBonus);
		game.getRoot().getChildren().add(clusterBonus);
		BonusAnimation clusterBonusAnimation = new BonusAnimation(game, clusterBonus);
		game.addAnimation(clusterBonusAnimation);
		clusterBonus.setAnimation(clusterBonusAnimation);
		clusterBonusAnimation.play();
		super.destroy(game);
	}
}
