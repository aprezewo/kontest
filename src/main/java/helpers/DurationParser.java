package helpers;

import java.time.Duration;

/**
 * This class converts java.time.Duration Objects into Time strings of the format 00-00-00 (hours,minutes,seconds), and has the ability to parse such strings
 * into Duration Objects.
 * @author Alex
 *
 */
public class DurationParser {
	
	Duration duration = Duration.ZERO;
	private static final String delimiter = "-";
	
	public Duration getResult() {
		return duration;
	}
	
	/**
	 * Tries to parse the given string, if this is not possible, returns false. Desired Format is 00:00:00, formats like 0:0:0 or 0:00 are allowed too.
	 * @param String to Parse
	 * @return true if parsing worked, false if it didnt
	 */
	public boolean tryParse(String toParse) {
		if(toParse == null) {
			return false;
		}

		toParse.strip().replaceAll("[^0-9"+delimiter+"]", "");
		if(toParse.length() < 3) {
			return false;
		}
		
		toParse = delimiter+delimiter + toParse; 
		//making sure, there are at least 3 parts in the string
		//best case: --00-00-00, worst cases --,--000   , when 0-0 -> --0-0
		
		duration = parse(toParse);
		return true;
	}

	/**
	 * Use this, when sure, that the string is in the right format 00:00:00
	 * @param toParse
	 * @return
	 */
	public Duration parse(String toParse) {
		
		String[] parts = toParse.split(delimiter);
		int[] durations = new int[3];
		IntParser intP = new IntParser();
		
		for(int t1 = parts.length -1; t1 >= parts.length - 3; t1--) {
			if(parts[t1].isEmpty()) {
				parts[t1] = "00";
			}
			intP.tryParse(parts[t1]);
			durations[t1 - (parts.length-3)] = intP.number;
		}
		duration = Duration.ZERO;
		duration = duration.plusSeconds(durations[2]);
		duration = duration.plusMinutes(durations[1]);
		duration = duration.plusHours(durations[0]);
		return duration;
	}
	
	public static String ToString(Duration duration) {

		String hours = Integer.toString((int)duration.toHours()) ;
		hours = hours.length() == 1 ? "0" + hours : hours;
		
		String minutes = Integer.toString(duration.toMinutesPart()) ;
		minutes = minutes.length() == 1 ? "0" + minutes : minutes;
		
		String seconds = Integer.toString(duration.toSecondsPart()) ;
		seconds = seconds.length() == 1 ? "0" + seconds : seconds;
		
		return hours + delimiter + minutes + delimiter + seconds;
	}
}
