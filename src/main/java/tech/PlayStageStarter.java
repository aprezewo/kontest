package tech;

import events.Event;
import events.EventHandler;
import general.Main;
import graphic.PlayStageMaster;
import graphic.SignData;
import javafx.application.Platform;

public class PlayStageStarter{

	EventHandler<Event> exitHandler;
	
	public PlayStageStarter() {
		
		SignData.create();
		
		//debug
		System.out.print("Tech Signs: ");
		SignData.printSignArray(SignData.signsTech);
	}
	
	public void start() {
		
		//initiate Master's
		
		PlayStageMaster mGraphic = new PlayStageMaster();
		Master mTech = new Master(mGraphic);
		
		//register handler to exit-request in stageHandler
		Main.stageHandler.getExitRegister().add(exitHandler = new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				mTech.End(true);
			} });
		
		//register handler
		mTech.getEndRegister().add(new EventHandler<EndEvent>() {

			@Override
			public void handle(EndEvent event) {
				End((EndEvent) event);
				mTech.getEndRegister().remove(this);
			} });

		Main.stageHandler.changeRoot(mGraphic.getRoot());
		
		mTech.Start();
	}
	
	public void End() {
		exitHandler.handle(new Event());
	}

	private void End(EndEvent event) {

		SignData.initialized = false;
		Main.stageHandler.getExitRegister().remove(exitHandler);
		exitHandler = null;
		
		if(!event.isEarly) {
			Platform.runLater(() -> new menus.ResultMenuController().start(event.hits, event.misses));
		}else {
			Platform.runLater(() -> new menus.ResultMenuController().startWithNewWindow(event.hits, event.misses));
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println("PlayStageStarter dead");
	}


}
