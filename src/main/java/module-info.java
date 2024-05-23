module w {
	opens model to javafx.base,com.google.gson,com.fasterxml.jackson.databind;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.media;
	requires java.desktop;
	requires javafx.swing;
	requires com.google.gson;
	exports view;
	opens view to javafx.fxml;
	exports view.menu;
	opens view.menu to javafx.fxml;
}