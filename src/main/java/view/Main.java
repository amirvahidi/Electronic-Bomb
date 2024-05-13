package view;

import view.menu.LoginMenuViewController;

public class Main {
	public static void main(String[] args) {
		// Launch the application
		AppView.setCurrentMenu(new LoginMenuViewController());
		((LoginMenuViewController) AppView.getCurrentMenu()).run(args);
	}
}
