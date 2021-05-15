package graphic;

import java.util.ArrayList;
import java.util.Collection;

import events.BasicDispatcher;
import events.Dispatcher;
import events.Register;
import general.Config;
import general.Main;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;

public class PlayStageMaster implements Output{

	private BorderPane root;
	private GridPane grid;
	private Sign[] currentRow;
	private int index;
	private Polygon arrow;
	
	private Dispatcher<HitEvent> hitDispatch;
	
	public PlayStageMaster() {
		
		hitDispatch = new BasicDispatcher<HitEvent>();
		
		root = new BorderPane();
		root.setBackground(new Background(new BackgroundFill(Config.getBackgroundColor(), CornerRadii.EMPTY, Insets.EMPTY)));
		
		arrow = createArrow(Config.getExtentGridObjects() * 0.7,Config.getExtentGridObjects()/2);
		arrow.setFill(Config.getObjectColor());
		arrow.prefHeight(30);
		
		grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);
		grid.add(arrow, 0, 1);
		
		VBox vert = new VBox();
		vert.setAlignment(Pos.CENTER);
		vert.setPadding(new Insets(0,20,0,20));
		vert.getChildren().add(grid);

		root.setCenter(vert);
		
		//set Events
		grid.setFocusTraversable(true);
		grid.setOnKeyPressed(e -> {
						
			if(e.getCode().equals(KeyCode.DIGIT0)) {
				hitDispatch.invoke(new HitEvent(0));
			}else if(e.getCode().equals(KeyCode.DIGIT1)) {
				hitDispatch.invoke(new HitEvent(1));
				
			}else if(e.getCode().equals(KeyCode.ESCAPE)) {
				
				//TODO should not be handled by main
				Main.stageHandler.tryCloseStage();
			}
		});
	}
	
	public BorderPane getRoot() {
		return root;
	}

	@Override
	public void newRow(tech.Sign[] row) {
		
		if(currentRow != null) {
			for(Sign sign : currentRow) {
				grid.getChildren().remove(sign);
			}
		}
		
		currentRow = new Sign[row.length];
		grid.getColumnConstraints().clear();
		
		index = 0;
		
		ArrayList<ColumnConstraints> collList = new ArrayList<ColumnConstraints>();
		
		for(int t1 = 0; t1 < row.length; t1++) {
			Sign newSign = SignData.getGraphicSignById(row[t1].getSignId());
			
			if(newSign == null) {
				System.out.println("Could not find graphic sign for id " + row[t1].getSignId());
				return;
			}
						
			ColumnConstraints col = new ColumnConstraints();
			col.setPercentWidth(100.0/row.length);
			col.setHalignment(HPos.CENTER);		
			collList.add(col);
			
			currentRow[t1] = newSign;
		}
		
		grid.getColumnConstraints().addAll(collList);
		for(int t1 = 0; t1 < currentRow.length; t1++) {
			grid.add(currentRow[t1], t1, 0);
		}
	}
	
	@Override
	public void moveIndex(int toIndex) {
		GridPane.setColumnIndex(arrow, index = toIndex);
	}
	
	private Polygon createArrow(double height, double width) {
		
		double capHeight = 0.3;
		
		return new Polygon(
					width/2,0, width,height*capHeight, width*0.7,height*capHeight,
					width*0.7,height, width*0.3,height, width*0.3,height*capHeight, 0,height*capHeight
				);
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println("PlayStageMaster is dead");
	}

	@Override
	public Register<HitEvent> getHitEventRegister() {
		return hitDispatch.getRegister();
	}

}
