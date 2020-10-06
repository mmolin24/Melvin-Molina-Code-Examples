/**
 * enum class that allows to use the enum easier than putting within other class
 * @author melvi
 *
 */
public enum Format { 
	THREE_D, NONE, IMAX;
	
	/**
	 * converter
	 * @param str a string 
	 * @return format
	 */
	static Format parseFromString(String str) {

		Format format = null;

		if(str.equals("IMAX")){
			format = Format.IMAX;
		}else if (str.equals("3D") || str.equals("THREE_D")) {
			format = Format.THREE_D;
		}else {
			format = Format.NONE;
		}
		return format;	
	}
	
	/**
	 * converts format into string
	 * @return string 
	 */
	String parseToString() {
		String x;
		
		if(this.equals(THREE_D)){
			x = "3D";
		}
		else if(this.equals(IMAX)) {
			x = "IMAX";
		}
		else {
			x = "NONE";
		}
		return x;
	}
	
}


