package model;

import java.util.ArrayList;

public class App {

	private static final ArrayList<User> users = new ArrayList<User>();
	private static User currentUser = null;

	public static void addUser(User user) {
		users.add(user);
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
}
