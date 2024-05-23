package view.menu;

import controller.AvatarMenuController;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.App;
import model.Setting;
import view.AppView;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;

public class AvatarMenuViewController extends Application {

	public ImageView background;
	public ScrollPane scrollPane;
	public ImageView iconField;
	public Rectangle dragField;

	private String iconPath;

	@Override
	public void start(Stage stage) throws Exception {
		AppView.setStage(stage);
		URL url = getClass().getResource("/FXML/AvatarMenu.fxml");
		if (url == null) {
			System.out.println("Couldn't find file: AvatarMenu.fxml");
			System.exit(-1);
		}
		Pane root = FXMLLoader.load(url);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void initialize() throws Exception {
		VBox vBox = new VBox();
		File[] files = new File(AvatarMenuViewController.class.getResource("/assets/icon").getPath()).listFiles();
		for (File file : files) {
			Image image = SwingFXUtils.toFXImage(ImageIO.read(file), null);
			ImageView imageView = new ImageView(image);
			imageView.setUserData(file.getPath());
			imageView.setFitWidth(180);
			imageView.setFitHeight(180);
			imageView.setOnMouseClicked(this::imageClicked);
			vBox.getChildren().add(imageView);
		}
		scrollPane.setContent(vBox);
		iconField.setImage(App.getCurrentUser().getIcon());
		iconPath = App.getCurrentUser().getPathOfIcon();
		Setting setting = App.getCurrentUser().getSetting();
		if (setting.isBlackAndWhite()) {
			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setSaturation(-1);
			colorAdjust.setBrightness(-0.5);
			AppView.getStage().getScene().getRoot().setEffect(colorAdjust);
		}
	}

	public void imageClicked(MouseEvent mouseEvent) {
		ImageView imageView = (ImageView) mouseEvent.getSource();
		iconField.setImage(imageView.getImage());
		iconPath = (String) imageView.getUserData();
		System.out.println(iconPath);
	}

	public void dragDropped(DragEvent dragEvent) throws Exception {
		Dragboard db = dragEvent.getDragboard();
		File file;
		String format;
		boolean success = false;
		if (db.hasFiles()) {
			success = true;
			file = dragEvent.getDragboard().getFiles().get(0);
			int dotIndex = file.getName().lastIndexOf('.');
			format = (dotIndex == -1) ? "" : file.getName().substring(dotIndex + 1);
			if (!format.equals("png") && !format.equals("jpg")) {
				success = false;
			}
			if (success) {
				Image image = SwingFXUtils.toFXImage(ImageIO.read(new File(file.getPath())), null);
				iconField.setImage(image);
				iconPath = file.getPath();
			}
		}
		dragEvent.setDropCompleted(success);
		dragEvent.consume();
	}

	public void dragOver(DragEvent dragEvent) {
		if (dragEvent.getGestureSource() != dragField && dragEvent.getDragboard().hasFiles()) {
			dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		}
		dragEvent.consume();
	}

	public void chooseFile(MouseEvent mouseEvent) throws Exception {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Select File");
		filechooser.setInitialDirectory(new File(System.getProperty("user.home")));
		FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
		FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
		filechooser.getExtensionFilters().add(pngFilter);
		filechooser.getExtensionFilters().add(jpgFilter);
		File file = filechooser.showOpenDialog(AppView.getStage());
		if (file != null) {
			Image image = SwingFXUtils.toFXImage(ImageIO.read(new File(file.getPath())),null);
			iconField.setImage(image);
			iconPath = file.getPath();
		}
	}

	public void save(MouseEvent mouseEvent) throws Exception {
		Image image = iconField.getImage();
		AvatarMenuController.changeAvatar(image, iconPath);
		AppView.setCurrentMenu(new ProfileMenuViewController());
		AppView.getCurrentMenu().start(AppView.getStage());
	}

	public void back(MouseEvent mouseEvent) throws Exception {
		AppView.setCurrentMenu(new ProfileMenuViewController());
		AppView.getCurrentMenu().start(AppView.getStage());
	}
}
