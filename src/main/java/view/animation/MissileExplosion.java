package view.animation;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import model.Explosion;
import model.Game;
import model.Missile;

public class MissileExplosion extends Transition {

	private Game game;
	private Explosion explosion;

	public MissileExplosion(Game game, Explosion explosion) {
		this.game = game;
		this.explosion = explosion;
		setCycleCount(1);
		setCycleDuration(javafx.util.Duration.millis(1300));
	}

	@Override
	protected void interpolate(double v) {
		System.out.println(v);
		int sz = 4;
		v *= sz;
		for (int i = 1; i <= sz; i++) {
			if (v < i){
				explosion.setFill(new ImagePattern(new Image(MissileExplosion.class.getResource("/assets/missileexplosion/" + i + ".png").toExternalForm())));
				break;
			}
		}
		if (v >= sz) {
			Pane root = game.getRoot();
			root.getChildren().remove(explosion);
			game.removeExplosion(explosion);
			game.removeAnimation(this);
			this.stop();
		}
	}
}
