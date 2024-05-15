package model;

import enums.Constant;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Tree extends Target {

	public Tree(double x, double y, int idx) {
		super(x, y, Constant.TREE_WIDTH.getValue(), Constant.TREE_HEIGHT.getValue());
		if (idx == 1){
			this.setFill(Game.Images.TREE_1.getValue());
		}
		else if (idx == 2){
			this.setFill(Game.Images.TREE_2.getValue());
		}
		else if (idx == 3){
			this.setFill(Game.Images.TREE_3.getValue());
		}
	}
}
