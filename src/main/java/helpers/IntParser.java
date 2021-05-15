package helpers;

public class IntParser {

	int number;
	
	/**
	 * Tries to parse the given string, if this is not possible, returns false
	 * @param String to Parse
	 * @return true if parsing worked, false if it didnt
	 */
	public boolean tryParse(String toParse) {
		try {
			number = Integer.parseInt(toParse);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public int getResult() {
		return number;
	}
}
