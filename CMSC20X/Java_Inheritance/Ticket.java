/**
 * abstract ticket to build other subclasses on
 * @author melvi
 *
 */
public abstract class Ticket {

	// all required fields
	private Format format;
	private String name;
	private String rating;
	private int day;
	private int time;
	private int ID;
	private double ticketPrice;
	
	/**
	 * a no arg constructor
	 */
	public Ticket() {
		
		name = "";
		
		rating ="";
		
		time = 0;
		
		day = 0;
		
		format = null;
		
		ID = 0;
		
	}
	
	/**
	 * constructor with parameters
	 * @param name of movie
	 * @param movie rating
	 * @param time movie is at
	 * @param day the movie was watched
	 * @param type of movie
	 */
	public Ticket(String name, String rating, int time, int day, Format format) {
		
		this.name = name;
		
		this.rating = rating;
		
		this.time = time;
		
		this.day = day;
		
		this.format = format;
		
		this.ID = 0;
		
	}
	
	/**
	 * constructor with parameters and ID for employee or movie pass
	 * @param name of movie
	 * @param rating of the movie
	 * @param time of the movie 
	 * @param day the movie is
	 * @param type of movie
	 * @param id of moviepass or employee
	 */
	public Ticket(String name, String rating, int time, int day, Format format, int ID) {
		
		this.name = name;
		
		this.rating = rating;
		
		this.time = time;
		
		this.day = day;
		
		this.format = format;
		
		this.ID = ID;
		
	}
	
	public abstract double calcualteTicketPrice();
	
	
	/**
	 * a set method for the name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * A get method that returns the name of the movie
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * set movie rate
	 * @param rating
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	/**
	 * gets movie rate
	 * @return
	 */
	public String getRating() {
		return rating;
	}
	

	/**
	 * @return Type of movie
	 */
	public Format getFormat() {
		return format;
	}

	/**
	 * @param sets type of movie
	 */
	public void setFormat(Format format) {
		this.format = format;
	}

	/**
	 * @return day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param sets day
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * @return gets time of day
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param sets id
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * return ID
	 */
	public abstract int getID();
	
	/**
	 * set ticket price
	 * @param ticketPrice
	 */
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	/**
	 * 
	 * @return ticket price
	 */
	public double getTicketPrice() {
		return ticketPrice;
	}
	
	/**
	 * override the tostring
	 */
	@Override
	public String toString() {
		
		return "Movie: " + name + " Rating: " +  rating + " Day: " + day + " Time: " + time + " Format: " + format +  " Price: " + ticketPrice  ;
		
	}

	
}
