package view;

import controller.GsonController;
import view.menu.LoginMenuViewController;

public class Main {
	public static void main(String[] args) {

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
					GsonController.saveGame();
			}
		}, "Shutdown-thread"));
		GsonController.loadGame();
		// Launch the application
		AppView.setCurrentMenu(new LoginMenuViewController());
		((LoginMenuViewController) AppView.getCurrentMenu()).run(args);
	}
}
