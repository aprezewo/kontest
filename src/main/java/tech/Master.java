package tech;

import java.util.Timer;
import java.util.TimerTask;

import events.BasicDispatcher;
import events.Dispatcher;
import events.Event;
import events.EventHandler;
import events.Register;
import general.Config;
import general.Main;
import graphic.HitEvent;
import graphic.Output;
import graphic.SignData;
import javafx.stage.WindowEvent;

public class Master{
	
	// state management
	private Sign[] currentRow;
	private int hits;
	private int misses;
	private int index;
	
	// class references
	private Timer timer;
	private Output output;
	
	private Dispatcher<EndEvent> endDispatch;
	
	public Master(Output output) {
		this.output = output;
		endDispatch = new BasicDispatcher<EndEvent>();
		
		output.getHitEventRegister().add(e -> processHitEvent(e));
	}

	public void Start() {
		
		if(!SignData.initialized) {
			return;
		}
		
		hits = 0;
		misses = 0;
		
		//initiates regular ending of the game
		timer = new Timer(true);
		TimerTask task = new TimerTask() {
			public void run() {
				End(false);
			} };
		
		//Listens for early ending of the game
		Main.stageHandler.getExitRegister().add(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				End(true);
				Main.stageHandler.getExitRegister().remove(this);
			} });
		
		timer.schedule(task, (long)(Config.getDuration().getSeconds() * 1000));
		NewRow();
	}
	
	protected void Next() {
		if(++index >= Config.getSignsPerRow()) {
			NewRow();
		}else {
			output.moveIndex(index);
		}
	}
	
	protected void NewRow() {
		Sign[] row = new Sign[Config.getSignsPerRow()];
		index = 0;
		
		for(int t1 = 0; t1 < row.length; t1++) {
			
			//Random id generator (0 - (signVariations-1) )
			int id = (int)Math.round( Math.random() * (Config.getSignVariations() - 1));
			
			row[t1] = new Sign(id);
		}
		System.out.println("--------------------------------------");
		System.out.println("current hits: " + hits + ", current misses: " + misses + ", sum: " + (hits+misses) );
		System.out.print("New Row: ");
		SignData.printSignArray(row);
		currentRow = row;
		
		output.newRow(row);
		output.moveIndex(index);
	}
	
	private void processHitEvent(graphic.HitEvent hit) {
		if(hit.getType() == currentRow[index].getSignType()) {
			hits++;
		}else {
			misses++;
		}
		Next();
	}
	
	protected void End(boolean isEarly) {
		
		timer.cancel();
		endDispatch.invoke(new EndEvent(hits, misses, isEarly));
	}
	
	public Register<EndEvent> getEndRegister(){
		return endDispatch.getRegister();
	}

	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println("Master is dead");
	}
}
