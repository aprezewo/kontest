package configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

// Wertebereiche abchecken, wo? -> bei set

// "local-File" is a setting, and can be changed with set("local-File",value)

// returning true means no errors, and not necessarily that the program did, what was intended (aka. not saving due to no changes)

public class PropertiesConfig implements Configuration {
	
	ArrayList<Setting<Object>> settings;
	private boolean loaded;
	private boolean changed;
	
	public PropertiesConfig(File internalFile, File localFile) {
		initialize(internalFile,localFile);
	}
	
	private void initialize(File internalFile, File localFile) {
		settings = new ArrayList<Setting<Object>>();
		
		//the internal file with the default configuration
		settings.add(new Setting<Object>("internal-File",internalFile,false)); 
		
		// the local file, where changes will be saved to, can be changed through set("local-file", value)
		settings.add(new Setting<Object>("local-File",localFile,true));
	}
	
	private Object parseValue(String value) {
		
		//regex determination of type: 192 -> int, 192.0 -> double, 192f -> float, 00-00-00 || 00-00 -> Duration, "192" -> String, '9' -> char
		// characters without "" or with just one " -> invalid
		
		
		return new Object();
	}
	
	private Properties loadProperties(File file) {
		Properties props = new Properties();
		
		try {
			InputStream stream = new FileInputStream(file);
			props.load(stream);
			stream.close();
		}catch(IOException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
		return new Properties();
	}

	@Override
	//serves as reset
	public boolean load(){
		
		// check files for null

		//reset list
		File internal = (File)get("internal-File");
		File local = (File)get("local-File");
		
		initialize(internal,local);

		// reads internalFile, then localFile
		// settings already contained in settingsList are overwritten by later occurences -> highest priority files are read last
		
		//load internal config (formatted like: key-A=value,true|false) if not possible, return false
		Properties internalProps = loadProperties(internal);
		
		if(internalProps == null) {
			return false;
		}
		
		//loop through ALL entries
		internalProps.keys().asIterator().forEachRemaining((key) -> {
			String[] values = internalProps.getProperty((String)key).split(",");
			settings.add(new Setting<Object>( (String)key, parseValue(values[0]), values[1].equals("true")?true:false) );
		});
		
			//get Properties as array (split by ',' )
				//fill in key, value(parsed), overridable
				//add to List
		
		
		//load localFiles (formatted like: key-A=value), if File doesnt exist, skip
		//loop through entries
			//if Setting in List, whose key matches key
				//if overridable -> override, else skip
					//fill in key, origin(filepath), overridable=true
					//switch (parse value format), parse value -> fill in value
					//add to List	
			//else:
				//fill in key, origin(filepath), overridable=true
				//switch (parse value format), parse value -> fill in value
				//add to List

		
		
		
		//return true
		
		return false;
	}

	@Override
	public boolean save() {
		
		//if changed 
		// iterate through list, save every setting to its origin
		// dont write default-File + local-File
		// set changed to false
		
		return false;
	}


	public Object get(String key) {
		
		for(Setting<Object> setting : settings) 
			if(setting.key.equals(key)) 
				return setting.value;
			
		return null;
	}

	@Override
	public boolean set(String key, Object value) {
		
		//iterate through Setting List, find key
		//check, if overridable, check if type matches
		//update value, return true
		
		return false;
	}

	@Override
	public boolean isLoaded() {
		return loaded;
	}

}
