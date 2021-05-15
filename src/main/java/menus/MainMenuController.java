package menus;

import general.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import tech.PlayStageStarter;

/**
 * 
 * @author aprezewo
 *
 */
public class MainMenuController {
	
	public MainMenuController() {}
	
	public void start() {
		
		try {
			
			FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.this.getClass().getResource("/fxml/MainMenuRoot.fxml"));
			fxmlLoader.setController(this);
			
			Main.stageHandler.changeRoot(fxmlLoader.load());
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}		
	}
	
	@FXML
	public void play() {
		new PlayStageStarter().start();
		//new ResultMenuController().start(1, 1);
	}
	
	@FXML
	public void configuration() {
		new ConfigMenuController().start();
	}
	
	@FXML
	public void manual() {
		new ManualController().start();
	}
	
	@FXML
	public void exit() {
		Main.exit();
	}

}
