package general;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menus.MainMenuController;

/**
 * This class holds authority over the Stage and the application - every change to application
 * state, stage or root content of stage must be requested here.
 * @author aprezewo
 *
 */
public class Main extends Application{
	
	public static StageHandler stageHandler;
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		Config.load();
//		Config.resetToDefault();	

		primaryStage.setScene(new Scene(new Pane(),1200,600));
		stageHandler = new StageHandler(primaryStage);
		
		//dumb shit - renaming the underlying Pulse thread from Thread-2 to Pulse out of stubborness
		Thread[] threads = new Thread[8];
		Thread.enumerate(threads);
		for(Thread thread : threads) {
			if(thread != null && thread.getName().equals("Thread-2")) {
				thread.setName("Pulse");
				break;
			}
		}
		
		new MainMenuController().start();
		System.out.println("Initialized");
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	public static void exit() {
		Config.save();
		Platform.exit();
	}

}
