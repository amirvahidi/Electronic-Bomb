package model;

import java.util.ArrayList;

public class App {

	private static final ArrayList<User> users = new ArrayList<User>();

	private static final ArrayList<GameResult> gameResults = new ArrayList<GameResult>();
	private static User currentUser = null;

	public static void addUser(User user) {
		users.add(user);
	}

	public static ArrayList<User> getUsers() {
		return new ArrayList<>(users);
	}

	public static void setUsers(ArrayList<User> users) {
		App.users.clear();
		App.users.addAll(users);
	}

	public static User getUserByUsername(String username) {
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	public static void setCurrentUser(User user) {
		currentUser = user;
	}

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void deleteUser(User currentUser) {
		users.remove(currentUser);
	}

	public static void addGameResult(GameResult gameResult) {
		gameResults.add(gameResult);
	}

	public static ArrayList<GameResult> getGameResults() {
		return new ArrayList<>(gameResults);
	}

	public static void setGameResults(ArrayList<GameResult> gameResults) {
		App.gameResults.clear();
		App.gameResults.addAll(gameResults);
	}

}
