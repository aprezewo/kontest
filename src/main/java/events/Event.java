package events;

/**
 * Base class for all event classes. Is distributed by {@link Dispatcher} to {@link EventHandler}.
 * @author Alex
 *
 */
public class Event{

	//Only for tests
	@Override
	protected void finalize() throws Throwable {
		System.out.println("Event garbage collected");
	}
	
}
