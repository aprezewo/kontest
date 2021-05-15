package graphic;

import events.Event;

public class HitEvent extends Event {
	
	private int hitType = -1;
	
	public HitEvent() {
		
	}
	
	public HitEvent(int type) {
		this.hitType = type;
	}
	
	public int getType(){
		return hitType;
	}
	
}
