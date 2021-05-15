package graphic;

import java.util.Collection;

import javafx.scene.Group;
import javafx.scene.Node;

public class Sign extends Group{
	
	private int id;
	private int type;
	
	public Sign(int id) {
		super();
		this.id = id;
		this.type = SignData.getTypeForId(id);
	}
	
	public Sign(int id, Collection<Node> args) {
		super(args);
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
