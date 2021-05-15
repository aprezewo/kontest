package menus;

import general.Config;
import general.Main;
import graphic.SignData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import tech.Sign;

public class ManualController {
	
	@FXML GridPane grid;
	
	public ManualController() { }
	
	public void start() {
		
		SignData.create();
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(ManualController.this.getClass().getResource("/fxml/ManualRoot.fxml"));
			fxmlLoader.setController(this);
			
			Main.stageHandler.changeRoot(fxmlLoader.load());
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}	
		
		for(int t1 = 0; t1 < Config.getSignVariations(); t1++) {
			int t2 = ((int)t1/4) * 2; // 0,0,0,0, 2,2,2,2, 4,4,4,4...
			grid.add(SignData.getGraphicSignById(t1), t1 % 4 , t2);
			Label l = new Label("" + SignData.getTypeForId(t1));
			GridPane.setHalignment(l, HPos.CENTER);
			GridPane.setValignment(l, VPos.CENTER);
			grid.add(l, t1 % 4, t2+1);
		}
	}
	
	public void End() {
		new MainMenuController().start();
	}

}
