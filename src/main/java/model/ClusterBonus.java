package model;

import controller.GameMenuController;
import enums.Constant;
public class ClusterBonus extends Bonus {

	public ClusterBonus(double x, double y) {
		super(x, y, Constant.CLUSTER_BONUS_WIDTH.getValue(), Constant.CLUSTER_BONUS_HEIGHT.getValue(), Constant.CLUSTER_BONUS_SPEED.getValue());
		this.setFill(Game.Images.CLUSTER_BONUS.getValue());
	}

	@Override
	public void remove(Game game) {
		System.out.println("Cluster Bonus is collected");
		game.getBonuses().remove(this);
		game.getRoot().getChildren().remove(this);
		this.stopAnimation();
		game.removeAnimation(this.getAnimation());
		GameMenuController.addCluster();
	}
}
