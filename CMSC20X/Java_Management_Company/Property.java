class Property{

	private String city;

	private String owner;

	private String propertyName;

	private double rentAmount;

	private Plot plot;

	/**
	 * default constructor
	 */
	public Property(){

		setPropertyName("");

		setCity("");

		setRentAmount(0); 

		setOwner("");

		plot = new Plot();
	}

	/**
	 * copy constructor
	 * @param p
	 */
	public Property(Property p){

		this.city = p.getCity();

		this.owner = p.getOwner();

		this.propertyName = p.getPropertyName();

		this.rentAmount = p.getRentAmount();


	}

	/** 
	 * constructor with variable values and default plot
	 * @param propertyName
	 * @param city
	 * @param rentAmount
	 * @param owner
	 */
	public Property(String propertyName, String city, double rentAmount, String owner){

		setPropertyName(propertyName);

		setCity(city);

		setRentAmount(rentAmount); 

		setOwner(owner);
		this.plot = new Plot();

	}
	/**
	 * constructor with variable values and plot() values
	 * @param propertyName
	 * @param city
	 * @param rentAmount
	 * @param owner
	 * @param x
	 * @param y
	 * @param width
	 * @param depth
	 */
	public Property(String propertyName, String city, double rentAmount, String owner, int x, int y, int width, int depth) {
		
		setPropertyName(propertyName);

		setCity(city);

		setRentAmount(rentAmount); 

		setOwner(owner);
		
		this.plot = setPlot(x, y, width, depth);
		
	}

	/**
	 * return city
	 * @return city
	 */
	public String getCity(){

		return city;

	}

	/**
	 * returns owner name
	 * @return owner name
	 */
	public String getOwner(){

		return owner;

	}

	/**
	 * 
	 * @return property name string
	 */
	public String getPropertyName(){

		return propertyName;

	}

	/**
	 * 
	 * @return rent amount as double
	 */
	public double getRentAmount(){

		return rentAmount;

	}

	/**
	 * sets city name String
	 * @param city 
	 */
	public void setCity(String city){

		this.city = city;

	}

	/**
	 * sets owner String
	 * @param owner
	 */
	public void setOwner(String owner){

		this.owner = owner;

	}

	/**
	 * sets property name
	 * @param propertyName
	 */
	public void setPropertyName(String propertyName){

		this.propertyName = propertyName;

	}

	/**
	 * sets rent amount
	 * @param rentAmount
	 */
	public void setRentAmount(double rentAmount){

		this.rentAmount = rentAmount;

	}
	
	/**
	 * 
	 * @return plot being used
	 */
	public Plot getPlot() {
		return plot;
	}
	
	
	/**
	 * 
	 * @return name String
	 */
	public String getName() {
		return propertyName;
	}

	
	/**
	 * plot constructor with variables
	 * @param x
	 * @param y
	 * @param width
	 * @param depth
	 * @return
	 */
	public Plot setPlot(int x, int y, int width, int depth) {
		this.plot = new Plot(x, y, width, depth);
		
		return plot;
		
	}
	
	
	/**
	 * to String method to replace default
	 */
	public String toString(){

		String toString = 
		"Property Name: " + getPropertyName() + "\n" +
		"Located in " + getCity() + "\n"+
		"Belonging to: " + getOwner() + "\n" +
		"Rent Amount: " + getRentAmount() + " ";

		return toString;

	}
	
}