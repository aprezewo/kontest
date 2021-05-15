package events;

import java.util.List;

/**
 * This class can distribute a given {@link Event} to a list of {@link EventHandler} using a {@link Register}. Implementing classes may make the register
 * available to subscriber classes, to let them add EventHandlers to this event.
 * @author Alex
 *
 */
public class BasicDispatcher<T extends Event> implements Dispatcher<T> {
	
	public T event;
	public Register<T> register;
	
	public BasicDispatcher() {
		register = new Register<T>();
	}
	
	public BasicDispatcher(Register<T> register, T event) {
		if((this.register = register) == null)
			register = new Register<T>();	
		this.event = event;
		
	}

	public void invoke() {
		if(event == null)
			throw new NullPointerException("No event set that could be invoked.");
		
		List<EventHandler<T>> subscribers = register.subscribers;
		try {
			for(int t1 = 0; t1 < subscribers.size(); t1++) {
				subscribers.get(t1).handle(event);
			}
		}catch(Exception e) {
			//relay information
		}
	}
	
	public void invoke(T event) {
		this.event = event;
		invoke();
	}

	@Override
	public Register<T> getRegister() {
		return register;
	}

	@Override
	public void setRegister(Register<T> register) {
		this.register = register;
	}
}
