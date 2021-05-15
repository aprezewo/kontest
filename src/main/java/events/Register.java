package events;

import java.util.ArrayList;
import java.util.List;

/**
 * Class, that can be made public to allow the subscription of EventHandlers. An {@link Dispatcher} can then distribute the event
 * with the list of this register.
 * @author Alex
 *
 * @param <T>
 */
public class Register<T extends Event> {
	
	protected List<EventHandler<T>> subscribers;
		
	public Register() {
		subscribers = new ArrayList<EventHandler<T>>();
	}
	
	public boolean add(EventHandler<T> toAdd) {
		return subscribers.add(toAdd);
	}
	
	public boolean remove(EventHandler<T> toRemove) {
		return subscribers.remove(toRemove);
	}
}
