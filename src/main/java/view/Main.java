package view;

import model.App;
import view.AppView;
import view.LoginMenuViewController;

public class Main {
	public static void main(String[] args) {
		// Launch the application
		AppView.setCurrentMenu(new LoginMenuViewController());
		((LoginMenuViewController) AppView.getCurrentMenu()).run(args);
	}
}
