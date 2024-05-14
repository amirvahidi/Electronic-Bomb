package model;

import enums.Constant;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Tree extends Target {

	public Tree(double x, double y, int idx) {
		super(x, y, Constant.TREE_WIDTH.getValue(), Constant.TREE_HEIGHT.getValue());
		this.setFill(new ImagePattern(new Image(Tree.class.getResource("/assets/tree/" + idx + ".png").toExternalForm())));
	}
}
