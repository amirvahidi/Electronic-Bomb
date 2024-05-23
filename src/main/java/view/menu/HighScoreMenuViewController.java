package view.menu;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.App;
import model.Game;
import model.GameResult;
import view.AppView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

public class HighScoreMenuViewController extends Application {

	public TableView highScoreTable;

	@Override
	public void start(Stage stage) throws Exception {
		URL url = getClass().getResource("/FXML/HighScoreMenu.fxml");
		if (url == null) {
			System.out.println("Couldn't find file: HighScoreMenu.fxml");
			System.exit(-1);
		}
		Pane newRoot = FXMLLoader.load(url);
		Scene newScene = new Scene(newRoot);
		AppView.getStage().setScene(newScene);
	}

	@FXML
	public void initialize() throws Exception {

		TableColumn<GameResult, String> usernameColumn = (TableColumn<GameResult, String>) highScoreTable.getColumns().get(0);
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

		TableColumn<GameResult, Integer> scoreColumn = (TableColumn<GameResult, Integer>) highScoreTable.getColumns().get(1);
		scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

		TableColumn<GameResult, Integer> waveColumn = (TableColumn<GameResult, Integer>) highScoreTable.getColumns().get(2);
		waveColumn.setCellValueFactory(new PropertyValueFactory<>("wave"));

		TableColumn<GameResult, Double> accuracyColumn = (TableColumn<GameResult, Double>) highScoreTable.getColumns().get(3);
		accuracyColumn.setCellValueFactory(new PropertyValueFactory<>("accuracy"));

		TableColumn<GameResult, Integer> killColumn = (TableColumn<GameResult, Integer>) highScoreTable.getColumns().get(4);
		killColumn.setCellValueFactory(new PropertyValueFactory<>("kill"));

		highScoreTable.setRowFactory(tv -> new TableRow<GameResult>() {
			@Override
			protected void updateItem(GameResult item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null) {
					setStyle("");
				} else if (getIndex() == 0) {
					setStyle("-fx-background-color: gold;");
				} else if (getIndex() == 1) {
					setStyle("-fx-background-color: silver;");
				} else if (getIndex() == 2) {
					setStyle("-fx-background-color: #cd7f32;");
				} else {
					setStyle("");
				}
			}
		});

		ArrayList<GameResult> gameResults = App.getGameResults();
		gameResults.sort((o1, o2) -> {
		if (o1.getScore() == o2.getScore()) return o1.getWave() - o2.getWave();
		return o2.getScore() - o1.getScore();
				});
		highScoreTable.getItems().addAll(gameResults);
	}

	public void back(MouseEvent mouseEvent) throws Exception {
		AppView.setCurrentMenu(new MainMenuViewController());
		AppView.getCurrentMenu().start(AppView.getStage());
	}
}
