package graphic;

import events.Register;

public interface Output {

	public void newRow(tech.Sign[] row);
	
	public void moveIndex(int toIndex);
	
	public Register<HitEvent> getHitEventRegister();

}
