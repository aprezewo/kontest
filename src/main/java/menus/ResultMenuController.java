package menus;

import general.Config;
import general.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * 
 * @author aprezewo
 *
 */
public class ResultMenuController {
		
	@FXML Label successLabel;
	@FXML Label hitLabel;
	@FXML Label missLabel;
	@FXML Label sumLabel;
	@FXML Label hitPercLabel;
	@FXML Label infoLabel;
	
	int hits;
	int misses;
	
	boolean endOnExit;
	
	public ResultMenuController() {	}
	
	public void start(int hits, int misses) {
		this.hits = hits;
		this.misses = misses;
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(ResultMenuController.this.getClass().getResource("/fxml/ResultMenuRoot.fxml"));
			fxmlLoader.setController(this);
			
			Main.stageHandler.changeRoot(fxmlLoader.load());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		printValues();
	}
	
	public void startWithNewWindow(int hits, int misses) {
		this.hits = hits;
		this.misses = misses;
		endOnExit = true;
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(ResultMenuController.this.getClass().getResource("/fxml/ResultMenuRoot.fxml"));
			fxmlLoader.setController(this);
			Scene scene = new Scene(fxmlLoader.load(),700,400);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		printValues();
		
	}
	
	private void printValues() {
		
		//determine values
		double sum = hits+misses;
		int hitPerc = sum > 0 ? (int)((hits/sum)*100):100;
		boolean success = Config.getMinSuccessSum() <= sum && sum > 0 && hits/sum >= Config.getMinSuccessHitPercent()/100;
		String result = success?"won":"failed";
			
		successLabel.setText("You " + result);
		hitLabel.setText("hits: " + hits);
		missLabel.setText("misses: " + misses);
		sumLabel.setText("sum: " + sum);
		hitPercLabel.setText("hit percentage: " + hitPerc + "%");
		infoLabel.setText("minimum required actions: " + Config.getMinSuccessSum() + ", minimum hit percentage: " + Config.getMinSuccessHitPercent() + "%");
	
		//Console print
		System.out.println("---------------}}FINISHED{{-----------------------");
		System.out.println("You " + result );
		System.out.println("hits: " + hits + ", misses: " + misses + ", sum: " + (hits+misses) );
		System.out.println("hit percentage: " + hitPerc + "%");
		System.out.println("minimum required actions: " + Config.getMinSuccessSum() + ", minimum hit percentage: " + Config.getMinSuccessHitPercent() + "%");
		
	}

	public void toMainMenu() {
		if(!endOnExit) {
			new MainMenuController().start();
		}else {
			Main.exit();
		}
	}
}
