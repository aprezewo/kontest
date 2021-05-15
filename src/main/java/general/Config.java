package general;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

import helpers.DurationParser;
import helpers.IntParser;
import javafx.scene.paint.Color;

public class Config {
	/**
	 * When adding properties (that should end up in config file):
	 *  - add getter/setter
	 *  - add to update methods
	 */
	
	//part of properties file
	private static int signsPerRow;
	private static int signVariations; //max 8
	private static Duration duration; //no nanoseconds
	private static int minSuccessSum;
	private static int minSuccessHitPercent; //from 100
	
	//not part of properties file
	private static double extentGridObjects = 100; //100
	private static double strokeWidth = 7; //7
	
	private static Color backgroundColor = Color.BLACK;
	private static Color objectColor = Color.WHITE;
	
	//class variables
	private static String resourcePath = "/config.properties";
	private static File getLocalFile() {
		return Paths.get(System.getProperty("user.home"),".KonTest","configuration.properties").toFile();
	}
	private static boolean loaded = false;
	private static Properties defaults;
	private static Properties inUse;		
	
	//keys
	private static String signsPerRowKey = "Signs-per-Row";
	private static String signVariationKey = "Sign-Variations";
	private static String durationKey = "duration";
	private static String minSuccessSumKey = "min-success-sum";
	private static String minSuccessPercentKey = "min-success-hit-percent";
	
	public static void setSignsPerRow(int signsPerRow) {
		Config.signsPerRow = Math.min( Math.max(1, signsPerRow), 10); // range 1 - 10 //currently
	}
	public static void setSignVariations(int signVariations) {
		Config.signVariations = Math.max(1, Math.min(signVariations, 8)); //range 1 - 8
	}
	public static void setDuration(Duration duration) {
		Config.duration = duration.isNegative() ? Duration.ZERO : duration; //range 0 - n
	}
	public static void setMinSuccessSum(int minSuccessSum) {
		Config.minSuccessSum = Math.max(0, minSuccessSum); // range 0 - n
	}
	public static void setMinSuccessHitPercent(int minSuccessHitPercent) {
		Config.minSuccessHitPercent = Math.max(0, Math.min(100, minSuccessHitPercent)); //range 0 - 100
	}
	
	/* do i need these setters???
	 
	public static void setExtentGridObjects(double extentGridObjects) {
		Config.extentGridObjects = extentGridObjects;
	}
	public static void setStrokeWidth(double strokeWidth) {
		Config.strokeWidth = strokeWidth;
	}
	public static void setBackgroundColor(Color backgroundColor) {
		Config.backgroundColor = backgroundColor;
	}
	public static void setObjectColor(Color objectColor) {
		Config.objectColor = objectColor;
	}
	
	*/
	
	public static int getSignsPerRow() {
		return signsPerRow;
	}
	public static int getSignVariations() {
		return signVariations;
	}
	public static Duration getDuration() {
		return duration;
	}
	public static int getMinSuccessSum() {
		return minSuccessSum;
	}
	public static int getMinSuccessHitPercent() {
		return minSuccessHitPercent;
	}
	public static double getExtentGridObjects() {
		return extentGridObjects;
	}
	public static double getStrokeWidth() {
		return strokeWidth;
	}
	public static Color getBackgroundColor() {
		return backgroundColor;
	}
	public static Color getObjectColor() {
		return objectColor;
	}
	
	/** Tries to load the configuration into the config class. The values are then accessible through the getters the config class exposes.
	 * @return false, when loading failed. */
	public static boolean load() {
		
		//loads internal
		//loads local
		if(!( loadInternal() | loadLocal() )) {
			return false;
		}
		updateVariables(); //write values (filter out wrong values, confine values)
		updateProperties(); //write poperties with the corrected values
		inUse.list(System.out); //print out corrected values
		return loaded = true;
	}
	
	/** loads internal configuration into defaults
	 * @return false, when loading failed */
	private static boolean loadInternal() {
		//
		defaults = new Properties();
		
		try {
			InputStream stream = Config.class.getResourceAsStream(resourcePath);
			defaults.load(stream);
			stream.close();
		}catch(IOException | SecurityException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/** loads local file system configuration (if there is) into "inUse", adds defaults as fallback configuration
	 * @return false, when loading failed, or file does not exist */
	private static boolean loadLocal() {
		
		inUse = new Properties(defaults);
		File localFile = getLocalFile();
		
		if(!localFile.exists()) {
			return false;
		}
		
		try {
			InputStream stream = new FileInputStream(localFile);
			inUse.load(stream);
			stream.close();
		}catch(IOException | SecurityException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/** updates "inUse" with variable values, saves "inUse" to local Filesystem,	is not to be used before "load" has been called
	 * @return false, if there are no values to save, or saving failed */
	public static boolean save() {
		
		if(!loaded) {
			return false;
		}
		updateProperties();
		
		try {
			File localFile = getLocalFile();
			
			localFile.getParentFile().mkdir();
			localFile.createNewFile();
			
			FileWriter stream = new FileWriter ( localFile );
			inUse.store(stream,null);
			stream.close();
		}catch(IOException | SecurityException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**extracts values to variables :
	 * try to find key in properties
	 *		if found: tryParse
	 *			if worked: done
	 *			else: use default property
	 * not found: tryParse default value
	 * 
	 * normalizes values, as it uses the setters, which confine the values of the variables
	 */
	private static void updateVariables() {
		IntParser intP = new IntParser();
		DurationParser duP = new DurationParser();
		
		setSignsPerRow( intP.tryParse(inUse.getProperty(signsPerRowKey)) ? intP.getResult() : Integer.parseInt(defaults.getProperty(signsPerRowKey)) );
		setSignVariations( intP.tryParse(inUse.getProperty(signVariationKey)) ? intP.getResult() : Integer.parseInt(defaults.getProperty(signVariationKey)) );
		
		duP.tryParse(defaults.getProperty(durationKey));
		Duration defaultDuration = duP.getResult();
		setDuration( duP.tryParse(inUse.getProperty(durationKey)) && !duP.getResult().isZero() ? duP.getResult() :  defaultDuration  );
		
		setMinSuccessSum( intP.tryParse(inUse.getProperty(minSuccessSumKey)) ? intP.getResult() : Integer.parseInt(defaults.getProperty(minSuccessSumKey)) );
		setMinSuccessHitPercent( intP.tryParse(inUse.getProperty(minSuccessPercentKey)) ? intP.getResult() : Integer.parseInt(defaults.getProperty(minSuccessPercentKey)) );
	}
	
	/** Updates Property list with the values of the variables */
	private static void updateProperties() {
		
		inUse.setProperty(signsPerRowKey, Integer.toString(signsPerRow));
		inUse.setProperty(signVariationKey, Integer.toString(signVariations));
		
		inUse.setProperty(durationKey, DurationParser.ToString(duration));
		
		inUse.setProperty(minSuccessSumKey, Integer.toString(minSuccessSum));
		inUse.setProperty(minSuccessPercentKey,	Integer.toString(minSuccessHitPercent));
	}

	
	/**  Emptys Property file, then updates the variables with the configuration of the internal Properties */
	public static void resetToDefault() {
		inUse = new Properties(defaults);
		updateVariables();
	}

}
