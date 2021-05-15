package menus;

import java.time.Duration;
import java.util.ArrayList;

import general.Config;
import general.Main;
import helpers.IntParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Control;

public class ConfigMenuController {

	@FXML Slider signsPerRowSlider;
	@FXML Slider signVariationsSlider;
	@FXML TextField durationMinField;
	@FXML TextField durationSecField;
	@FXML TextField minHitSumField;
	@FXML TextField minHitPercentageField;	
	
	

	
	public ConfigMenuController() {

	}
	
	public void start() {
		
		try {
			
			FXMLLoader fxmlLoader = new FXMLLoader(ConfigMenuController.this.getClass().getResource("/fxml/ConfigRoot.fxml"));
			fxmlLoader.setController(this);
			
			Main.stageHandler.changeRoot(fxmlLoader.load());
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}
		
		signsPerRowSlider.setValue( Config.getSignsPerRow() );
		signVariationsSlider.setValue(Config.getSignVariations());
		durationMinField.setText(Integer.toString( Config.getDuration().toMinutesPart() ) );
		durationSecField.setText(Integer.toString( Config.getDuration().toSecondsPart() ) );
		minHitSumField.setText(Integer.toString(Config.getMinSuccessSum()));
		minHitPercentageField.setText(Integer.toString( Config.getMinSuccessHitPercent() ) );

	}
	
	@FXML
	public void End() {
		Config.setSignsPerRow((int)signsPerRowSlider.getValue());
		Config.setSignVariations((int)signVariationsSlider.getValue());
		
		IntParser intP1 = new IntParser();
		IntParser secParser = new IntParser();
		if(intP1.tryParse(durationMinField.getText()) && secParser.tryParse(durationSecField.getText())) {
			Duration newDuration = Duration.ofMinutes(intP1.getResult()).plusSeconds(secParser.getResult());
			Config.setDuration(newDuration);
		}
		
		Config.setMinSuccessSum(intP1.tryParse(minHitSumField.getText())? intP1.getResult() : Config.getMinSuccessSum());
		Config.setMinSuccessHitPercent(intP1.tryParse(minHitPercentageField.getText())? intP1.getResult() : Config.getMinSuccessHitPercent());
		
		new MainMenuController().start();
	}
}
