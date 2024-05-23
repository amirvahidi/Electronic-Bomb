package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.App;
import model.GameResult;
import model.User;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GsonController {

	private static final String PATH_OF_USERS = "src/main/resources/data/users.json";
	private static final String PATH_OF_SCORES = "src/main/resources/data/scores.json";

	public static void saveGame() {
		try{
			Gson gson = new Gson();
			FileWriter writer = new FileWriter(PATH_OF_USERS);
			String json = gson.toJson(App.getUsers());
			writer.write(json);
			writer.close();
			writer = new FileWriter(PATH_OF_SCORES);
			json = gson.toJson(App.getGameResults());
			writer.write(json);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadGame() {
		try {
			Gson gson = new Gson();
			String json = new String(Files.readAllBytes(Paths.get(PATH_OF_USERS)));
			ArrayList<User> users = gson.fromJson(json, new TypeToken<List<User>>() {
			}.getType());
			for (User user : users) {
				user.fixTheIcon();
			}
			App.setUsers(users);
			json = new String(Files.readAllBytes(Paths.get(PATH_OF_SCORES)));
			ArrayList<GameResult> gameResults = gson.fromJson(json, new TypeToken<List<GameResult>>() {
			}.getType());
			App.setGameResults(gameResults);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
