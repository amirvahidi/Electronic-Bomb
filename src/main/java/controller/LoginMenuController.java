package controller;

import model.App;
import model.Result;
import model.User;

public class LoginMenuController {

	public static Result Register(String username, String password) throws Exception {
		if (App.getUserByUsername(username) != null) {
			return new Result(false, "Username already exists");
		}
		User newUser = new User(username, password, false);
		App.addUser(newUser);
		return new Result(true, "User registered successfully");
	}

	public static Result Login(String username, String password) {
		User user = App.getUserByUsername(username);
		if (user == null) {
			return new Result(false, "User not found");
		}
		if (!user.getPassword().equals(password)) {
			return new Result(false, "Incorrect password");
		}
		App.setCurrentUser(user);
		return new Result(true, "User logged in successfully");
	}

	public static Result Guest() throws Exception {
		User guest = new User("Guest", "Guest", true);
		App.setCurrentUser(guest);
		return new Result(true, "Guest logged in successfully");
	}
}
