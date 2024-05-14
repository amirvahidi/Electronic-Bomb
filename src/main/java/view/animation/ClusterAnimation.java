package view.animation;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Bomblet;
import model.Cluster;
import model.Game;

public class ClusterAnimation extends Transition {

	private Game game;

	private Cluster cluster;

	public ClusterAnimation(Cluster cluster, Game game) {
		this.game = game;
		this.cluster = cluster;
		setCycleCount(-1);
		setCycleDuration(Duration.millis(10000));
	}

	@Override
	protected void interpolate(double v) {
		double x = cluster.getX();
		double y = cluster.getY();
		double xSpeed = cluster.getXSpeed();
		double ySpeed = cluster.getYSpeed();
		double acceleration = cluster.getAcceleration();
		x += xSpeed;
		y -= ySpeed;
		ySpeed -= acceleration;
		if (x < -cluster.getWidth() || x >= game.getRoot().getWidth()){
			game.getRoot().getChildren().remove(cluster);
			game.removeBomb(cluster);
			game.removeAnimation(this);
			this.stop();
		}
		double height = game.getRoot().getHeight();
		if (y >= height - height / 3) {
			for (int i = 0; i < 10; i++) {
				Bomblet bomblet = new Bomblet(x, y, Math.random() - 0.5, ySpeed);
				game.addBomb(bomblet);
				game.getRoot().getChildren().add(bomblet);
				BombletAnimation bombletAnimation = new BombletAnimation(bomblet, game);
				game.addAnimation(bombletAnimation);
				bombletAnimation.play();
			}
			game.getRoot().getChildren().remove(cluster);
			game.removeBomb(cluster);
			game.removeAnimation(this);
			this.stop();
		}
		cluster.setX(x);
		cluster.setY(y);
		cluster.setRotate(-Math.toDegrees(Math.atan2(ySpeed, xSpeed)));
		cluster.setXSpeed(xSpeed);
		cluster.setYSpeed(ySpeed);
	}
}
