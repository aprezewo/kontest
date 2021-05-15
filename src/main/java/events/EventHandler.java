package events;

import java.util.EventListener;

public interface EventHandler<T extends Event> extends EventListener {
	
	void handle(T event);

}
