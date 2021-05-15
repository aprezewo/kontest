package general;

import events.BasicDispatcher;
import events.Dispatcher;
import events.Event;
import events.Register;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controls access to a stage.
 * @author Alex
 *
 */
public class StageHandler {
	
	private Stage stage;
	private Dispatcher<Event> exitDispatch;

	public StageHandler(Stage stage) {
		this.stage = stage;
		exitDispatch = new BasicDispatcher<Event>(new Register<Event>(), new Event());
		
		stage.setOnCloseRequest(e -> exitDispatch.invoke());
	}
	
	protected Stage getStage() {
		return stage;
	}

	protected void setStage(Stage stage) {
		this.stage = stage;
	}
	
	/**
	 * Causes the application to change the root node of the current Scene.
	 * @param toRoot The new root Node.
	 * @return true, if root could be changed, false, if its not allowed or not possible
	 */
	public boolean changeRoot(Parent toRoot) {
		stage.getScene().setRoot(toRoot);
		stage.show();
		return true;
	}
	
	/**
	 * Changes the scene of the current stage.
	 * @param toScene The new Scene.
	 * @return false, if the scene could not be changed, or operation is not allowed at the moment.
	 */
	public boolean changeScene(Scene toScene) {
		stage.setScene(toScene);
		stage.show();
		return true;
	}
	
	public void tryCloseStage() {
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}
	
	public Register<Event> getExitRegister(){
		return exitDispatch.getRegister();
	}
}
