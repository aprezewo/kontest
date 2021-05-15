package tech;

import events.Event;

public class EndEvent extends Event {
	
	int hits = 0;
	int misses = 0;
	boolean isEarly = true;
	
	public EndEvent() {}
	
	public EndEvent(int hits, int misses, boolean isEarly) {
		this.hits = hits;
		this.misses = misses;
		this.isEarly = isEarly;
	}
	
	public boolean isEarly() {
		return isEarly;
	}
	
	public int getHits() {
		return hits;
	}
	
	public int getMisses() {
		return misses;
	}

}
