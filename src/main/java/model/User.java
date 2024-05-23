package model;

import javafx.scene.image.Image;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class User {
	private String username;
	private String password;

	private final Setting setting;
	private final boolean guest;

	private String pathOfIcon;
	private transient Image icon;

	public User(String username, String password, boolean guest) throws Exception {
		this.username = username;
		this.password = password;
		this.guest = guest;
		this.setting = new Setting();
		int numberOfIcons = new File(User.class.getResource("/assets/icon").getPath()).listFiles().length;
		if (numberOfIcons == 0) {
			System.out.println("No icons found");
			System.exit(-1);
		}
		int iconNumber = (int) (Math.random() * numberOfIcons) + 1;
		File file = new File(User.class.getResource("/assets/icon/" + iconNumber + ".png").getPath());
		this.icon = SwingFXUtils.toFXImage(ImageIO.read(file), null);
		this.pathOfIcon = file.getPath();
		System.out.println(this.pathOfIcon);
		if (this.icon == null) {
			System.out.println("Couldn't find file: 1.png");
			System.exit(-1);
		}
	}


	public Image getIcon() {
		return this.icon;
	}

	public void setIcon(Image icon, String path) {
		this.icon = icon;
		this.pathOfIcon = path;
	}

	public void fixTheIcon(){
		File file = new File(pathOfIcon);
		try {
			this.icon = SwingFXUtils.toFXImage(ImageIO.read(file), null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public boolean isGuest() {
		return guest;
	}

	public Setting getSetting() {
		return setting;
	}

	public String getPathOfIcon() {
		return pathOfIcon;
	}
}
