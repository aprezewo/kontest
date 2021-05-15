package events;

import org.junit.platform.commons.annotation.Testable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;

@Testable
public class EventTest {
	
	Dispatcher<Event> singleDispatch;
	Event singleEvent;
	
	@Test
	public void checkSingle() {
		
		singleEvent = new Event();
		assertNotNull(singleEvent);
		
		singleDispatch = new BasicDispatcher<Event>();
		assertNotNull(singleDispatch);
		
		Register<Event> singleRegister = new Register<Event>();
		assertNotNull(singleRegister);
		
		singleDispatch.setRegister(singleRegister);
		assertSame(singleRegister, singleDispatch.getRegister());
		
		
		EventHandler<Event> singleEH = new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				checkSingleEvent(event);
			}
			
		};
		
		singleRegister.add(singleEH);
		assertEquals(1, singleRegister.subscribers.size());
		
		singleRegister.remove(singleEH);
		assertEquals(0, singleRegister.subscribers.size());
		
		assertThrows(NullPointerException.class, () -> singleDispatch.invoke());
		
		assertDoesNotThrow(() -> singleDispatch.invoke(singleEvent));
		
		singleRegister.add(singleEH);
		singleDispatch.invoke(singleEvent);
	}
	
	public void checkSingleEvent(Event e) {
		assertSame(singleEvent,e);
	}
	
	Dispatcher<Event> multipleDispatch;
	
	@Test
	public void checkMultiple() {
		
	}

}
