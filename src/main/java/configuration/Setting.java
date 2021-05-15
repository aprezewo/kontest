package configuration;

public class Setting<T> {
	
	public String key;
	public T value;
	public boolean overridable;
	
	Setting(String key, T value){
		this(key,value,false);
	}
	
	Setting(String key, T value, boolean overridable){
		this.key = key;
		this.value = value;
		this.overridable = overridable;
	}

}
