package controller;

import javafx.scene.image.Image;
import model.App;

public class AvatarMenuController {

	public static void changeAvatar(Image image) throws Exception {
		App.getCurrentUser().setIcon(image);
	}
}
