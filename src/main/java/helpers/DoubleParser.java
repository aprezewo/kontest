package helpers;

public class DoubleParser {
	
	double number;
	
	/**
	 * Tries to parse the given string, if this is not possible, returns false
	 * @param String to Parse
	 * @return true if parsing worked, false if it didnt
	 */
	public boolean tryParse(String toParse) {
		try {
			number = Double.parseDouble(toParse);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public double getResult() {
		return number;
	}
}
