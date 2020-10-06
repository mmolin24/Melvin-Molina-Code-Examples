// THIS IS A VERTEX
public class Town implements Comparable<Town>{
	
	private String name;
	
	/**
	 * copy constructor
	 * @param t
	 */
	public Town(Town other) {
		this.name = other.name;
	}
	
	/**
	 * constructor that receives just the name
	 * @param name
	 */
	public Town(String name){
		this.name = name;
	}
	
	
	public boolean equals(Town obj) {
		if(this.name.equals(obj.getName())){
			return true;
		}
		return false;
	}
	
	
	public String getName() {
		return name;
	}
	
	public int hashCode() {
		return super.hashCode();
	}
	
	
	public String toString() {
		
		return getName();
	}
	
	
	@Override
	public int compareTo(Town o) {
		if(this.name.equals(o.name)) {
			return 0;
		}
		return 1;
	}

	
}
