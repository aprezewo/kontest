package tech;

import graphic.SignData;

public class Sign {

	private int id;
	private int type;
	
	public Sign(int id) {
		this.id = id;
		this.type = SignData.getTypeForId(id);
	}

	public int getSignId() {
		return id;
	}

	public int getSignType() {
		return type;
	}
	
}
