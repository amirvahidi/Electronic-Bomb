module w {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.media;
	requires java.desktop;
	requires javafx.swing;

	exports view;
	opens view to javafx.fxml;
	exports view.menu;
	opens view.menu to javafx.fxml;
}