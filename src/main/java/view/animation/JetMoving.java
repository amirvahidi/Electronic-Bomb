package view.animation;

import enums.Constant;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Explosion;
import model.Game;
import model.Jet;

public class JetMoving extends Transition {

	private Game game;
	private Jet jet;

	public JetMoving(Jet jet, Game game) {
		this.game = game;
		this.jet = jet;
		setCycleCount(-1);
		setCycleDuration(Duration.millis(10000));
	}
	@Override
	protected void interpolate(double v) {
		jet.updateAngle();
		jet.move();
		Pane pane = (Pane) jet.getParent();
		double y = jet.getY();
		if (y >= pane.getHeight() - game.getEarth().getHeight() / 2) {
			jet.explode(game);
			game.getJet().reset();
			return;
		}
	}
}
