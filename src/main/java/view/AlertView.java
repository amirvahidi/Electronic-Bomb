package view;

import javafx.scene.control.Alert;
import model.Result;

public class AlertView {

	public static void showResult(String alertName, Result result) {
		Alert alert;
		if (result.isSuccess()) {
			alert = new Alert(Alert.AlertType.INFORMATION);
		} else {
			alert = new Alert(Alert.AlertType.ERROR);
		}
		alert.setTitle(alertName);
		alert.setHeaderText(result.getMessage());
		alert.showAndWait();
	}
}
