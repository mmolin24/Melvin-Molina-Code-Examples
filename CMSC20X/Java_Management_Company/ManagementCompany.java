class ManagementCompany{

	private final int MAX_PROPERTY = 5;

	private double mgmFeePer;

	private String name;

	private String taxID;

	private int MAX_WIDTH;

	private int MAX_DEPTH;

	private Plot plot;

	private Property[] properties;

	/**
	 * default constructor
	 */
	ManagementCompany(){

		this.name = "";

		this.taxID = "";

		this.plot = new Plot();

		Property properties[] = new Property[MAX_PROPERTY];
		
	}

	
	/**
	 * constructor with name taxid and mgmfee but default plot
	 * @param name
	 * @param taxID
	 * @param mgmFee
	 */
	public ManagementCompany(String name, String taxID, double mgmFee) {
		
		this.name = name;
		
		this.taxID = taxID;
		
		this.mgmFeePer = mgmFee;
		
		this.plot = new Plot();
		
		Property properties[] = new Property[MAX_PROPERTY];
		
	}
	
	
	/**
	 * constructor with all variables and sets width and depth
	 * @param name
	 * @param taxID
	 * @param mgmFee
	 * @param width
	 * @param depth
	 */
	public ManagementCompany(String name, String taxID, double mgmFee, int width, int depth){

		this.name = name; 

		this.taxID = taxID;

		this.mgmFeePer = mgmFee;
	
		this.MAX_WIDTH = width;

		this.MAX_DEPTH = depth;
		
		Property properties[] = new Property[MAX_PROPERTY];
		
	}

	/**
	 * constructor that includes all variables including variables to construct a plot
	 * @param name
	 * @param taxID
	 * @param mgmFee
	 * @param x
	 * @param y
	 * @param width
	 * @param depth
	 */
	public ManagementCompany(String name, String taxID, double mgmFee, int x, int y, int width, int depth) {
		
		this.name = name;
		
		this.taxID = taxID;
		
		this.mgmFeePer = mgmFee;
		
		plot = new Plot(x, y, width, depth);
		
		Property properties[] = new Property[MAX_PROPERTY];
		
	}
	
	/**
	 * copy constructor
	 * @param other
	 */
	public ManagementCompany(ManagementCompany other){

		this.name = other.getName();

		this.taxID = other.getTaxID();

		this.mgmFeePer = other.getMgmFeePer();
		
		this.plot = other.getPlot();
	
		properties = new Property[MAX_PROPERTY];

	}
	
	/**
	 * constructor with all variables
	 * @param x
	 * @param y
	 * @param width
	 * @param depth
	 * @param name
	 * @param taxID
	 * @param mgmFeePer
	 */
	ManagementCompany(int x, int y, int width, int depth, String name, String taxID, double mgmFeePer){
		plot = new Plot(x,y,width,depth);
		this.name = name;
		this.taxID = taxID;
		this.mgmFeePer = mgmFeePer;
		
	}
	
	/**
	 * add property with only information about the company
	 * @param name
	 * @param city
	 * @param rent
	 * @param owner
	 * @return
	 */
	public int addProperty(String name, String city, double rent, String owner) {
		int index = 0;
		
		Property p = new Property(name, city, rent, owner);
		
		for(int i = 0; i < MAX_PROPERTY; i ++) {
			if(p == null) {
				index = -2;
			}
			else if((properties[i] == null) && (properties[i].getPlot().overlaps(plot) == false) && 
					(properties[i].getPlot().encompasses(plot) == true) ){
				properties[i] = p;
				index = i;
			}
			else if(properties[MAX_PROPERTY-1] != null) {
				index = -1;
			}
			else if(properties[i].getPlot().overlaps(plot)) {
				index = -4;
			}
			else if(properties[i].getPlot().encompasses(plot) == false) {
				index = -3;
			}
			
		}
		
		
		return index;
		
	}
	
	/**
	 * addproperty with a previous existing property
	 * @param p
	 * @return
	 */
	public int addProperty(Property p) {
		int index= 0;
		for(int i = 0; i < MAX_PROPERTY; i ++) {
			if(p == null) {
				index = -2;
			}
			else if(properties[i] == null) {
				properties[i] = p;
				index = i;
			}
			else if(properties[MAX_PROPERTY-1] != null) {
				index = -1;
			}

			
		}
		
		return index;
	}
	
	
	/**
	 * addproperty with values to create a new plot aswell
	 * @param name
	 * @param city
	 * @param rent
	 * @param owner
	 * @param x
	 * @param y
	 * @param width
	 * @param depth
	 * @return
	 */
	public int addProperty(String name, String city, double rent, String owner, int x, int y, int width, int depth) {
		int index = 0;
		for(int i = 0; i < MAX_PROPERTY;i++) {
			
			if(properties[MAX_PROPERTY -1 ] != null) {
				index = -1;
			}
			else if(properties[i] == null) {
				properties[i] = new Property(name, city, rent, owner, x, y, width, depth);
				if(properties[i].getPlot().encompasses(this.plot) == false) {
					index = -3;
				}
				index = i;
				
			}
			else if(properties[i].getPlot().overlaps(this.plot))
				index = -4;
		}
		return index;
	}

	
	/**
	 * displays orioerty at certain index
	 * @param i
	 * @return
	 */
	public String displayPropertyAtIndex(int i) {
		
		return properties[i].toString();
		
	}
	
	/**
	 * returns index of property with largest rent
	 * @return
	 */
	public int maxPropertyRentIndex() {
		int maxRent = 0;
		for(int i = 0; i < MAX_PROPERTY; i++) {
			if(properties[i].getRentAmount() > properties[i+1].getRentAmount())
				maxRent = i;
			
		}
		
		return maxRent;
	}
	
	/**
	 * returns the amount of the largest rent charged in all properties
	 * @return
	 */
	public double maxPropertyRent() {
		
		double maxRent = 0;
		for(int i = 0; i < MAX_PROPERTY; i++) {
			if(properties[i].getRentAmount() > properties[i+1].getRentAmount())
				maxRent =  properties[i].getRentAmount();
			
		}
		
		return maxRent;
	}
	
	/**
	 * calculcates the total amount of rent
	 * @return sum of rent
	 */
	public double totalRent() {

		double totalRent = 0;
		
		for(int i = 0; i < MAX_PROPERTY; i++) {
			totalRent += properties[i].getRentAmount();
		}
		
		return totalRent;
	}
	
	/**
	 * sets mgmFeePer
	 * @param mgmFeePer
	 */
	public void setMgmFeePer(double mgmFeePer){

		this.mgmFeePer = mgmFeePer;

	}

	/**
	 * sets name
	 * @param name
	 */
	public void setName(String name){

		this.name = name;

	}

	
	/**
	 * sets taxID
	 * @param taxID
	 */
	public void setTaxID(String taxID){

		this.taxID = taxID;

	}	

	
	/**
	 * returns mgm fee per
	 * @return
	 */
	public double getMgmFeePer(){

		return mgmFeePer;

	}

	/**
	 * 
	 * @return name String
	 */
	public String getName(){

		return name;

	}

	/**
	 * 
	 * @return taxID String
	 */
	public String getTaxID(){

		return taxID;

	}

	/**
	 * 
	 * @return MAX_PROPERTY
	 */
	public int getMAX_PROPERTY(){

		return MAX_PROPERTY;

	}

	/**
	 * 
	 * @return plot address
	 */
	public Plot getPlot() {
		
		return this.plot;
	}

	/**
	 * toString that replaces the default toString method and displays information
	 * about all of the properties within the array properties[]
	 */
	public String toString() {

		String str = 
				"List of the properties for " + getName() + ", taxID: "+ getTaxID()+ "\n";
				
		for(int i = 0; i < MAX_PROPERTY; i++) {
			str += displayPropertyAtIndex(i) + "\n";
			
		}
		
		str += "\n total management Fee: " + totalRent();
		
		return str;
	}
	

}