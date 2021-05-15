package events;

/**
 * This class can distribute a given {@link Event} to a list of {@link EventHandler} using a {@link Register}. Implementing classes may make the register
 * available to subscriber classes, to let them add EventHandlers to this event.
 * @author Alex
 *
 * @param <T> Event to dispatch
 */
public interface Dispatcher<T extends Event> {
	
	public void invoke();
	
	public void invoke(T event);
	
	public Register<T> getRegister();
	public void setRegister(Register<T> toSet);

}
