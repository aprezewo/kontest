package configuration;

public interface Configuration {
	
	public boolean load();
	
	public boolean save();
	
	public Object get(String key);
	
	public boolean set(String key, Object value);
	
	public boolean isLoaded();

}
