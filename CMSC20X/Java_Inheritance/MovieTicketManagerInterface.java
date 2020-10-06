import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.NumberFormat;

public interface MovieTicketManagerInterface {
	
	/**
	 * Returns the number of times this patron has visited the theater this month
	 * @param name name of the patron
	 * @return the number of times this patron has visited the theater this month
	 */
	public int numVisits(int id);
	
	/**
	 * Returns the number of times the patron has seen this movie
	 * @param m the name of the movie
	 */
	public int numThisMovie(int id, String movie);
	
	/**
	 * Checks to see if this patron already saw a movie today
	 * @param name name of patron
	 * @param date current date
	 * @return number of movies the patron has seen today
	 */
	public int numMoviesToday(int id, int date);
	
	/**
	 * Adds a patron to the list and returns the ticket price
	 * @param m movie to be watched
	 * @param d date
	 * @param t time
	 * @param f feature
	 * @param type type of patron
	 * @return the price of the ticket. Returns a negative number of type is invalid
	 */
	public double addTicket(String movieN, String rating, int d, int t, String f, String type, int id);
	
	
	/**
	 * Returns the sales for the entire month
	 * @return the sales for the entire month
	 */
	public double totalSalesMonth();
	
	public String monthlySalesReport();
	
	/**
	 * Returns an arraylist of strings that represent 3D tickets sorted by day
	 * @return an arraylist of strings that represent 3D tickets sorted by day
	 */
	public ArrayList<String> get3DTickets();
	/**
	 * Returns an arrayList of strings which represent tickets, in chronological order
	 * use the toString of each Ticket in the ticketList
	 * @return an arrayList of strings which represent tickets, in chronological order
	 */
	public ArrayList<String> getAllTickets();

	/**
	 * Returns an Arraylist of string representation of MoviePass tickets sorted by movieId
	 * @return an Arraylist of string representation of MoviePass tickets sorted by movieId
	 */
	public ArrayList<String> getMoviePassTickets();
	
	/**
	 * Reads from a file and populates an arraylist of Ticket objects
	 * @param file file to be read from
	 * @throws FileNotFoundException when file is not found
	 */
	public void readFile(File file) throws FileNotFoundException;
}
