package model;

import controller.GameMenuController;
import enums.Constant;
import javafx.scene.shape.Rectangle;

public class NukeBonus extends Bonus {

	public NukeBonus(double x, double y) {
		super(x, y, Constant.NUKE_BONUS_WIDTH.getValue(), Constant.NUKE_BONUS_HEIGHT.getValue(), Constant.NUKE_BONUS_SPEED.getValue());
		this.setFill(Game.Images.NUKE_BONUS.getValue());
	}

	@Override
	public void remove(Game game) {
		System.out.println("Nuke Bonus is collected");
		game.getBonuses().remove(this);
		game.getRoot().getChildren().remove(this);
		this.stopAnimation();
		game.removeAnimation(this.getAnimation());
		GameMenuController.addNuke();
	}
}
