package controller;

import model.App;
import model.Result;

public class ProfileMenuController {

	public static Result changeUsername(String newUsername) {
		if (App.getCurrentUser().isGuest()){
			return new Result(false, "Guests cannot change their username");
		}
		if (App.getCurrentUser().getUsername().equals(newUsername)) {
			return new Result(false, "Username is the same as the current username");
		}
		App.getCurrentUser().setUsername(newUsername);
		return new Result(true, "Username changed successfully");
	}

	public static Result changePassword(String newPassword) {
		if (App.getCurrentUser().isGuest()){
			return new Result(false, "Guests cannot change their password");
		}
		if (App.getCurrentUser().getPassword().equals(newPassword)) {
			return new Result(false, "Password is the same as the current password");
		}
		App.getCurrentUser().setPassword(newPassword);
		return new Result(true, "Password changed successfully");
	}
	public static Result deleteAccount() {
		App.deleteUser(App.getCurrentUser());
		App.setCurrentUser(null);
		return new Result(true, "User deleted successfully");
	}
	public static Result logout() {
		App.setCurrentUser(null);
		return new Result(true, "User logged out successfully");
	}

}
