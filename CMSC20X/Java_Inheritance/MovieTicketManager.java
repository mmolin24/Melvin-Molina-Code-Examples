import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * 
 * @author melvi
 *
 */
public class MovieTicketManager implements MovieTicketManagerInterface{

	private ArrayList<Ticket> ticketList;
	private int counter = -1;
	private int three_DCount = -1;
	private int numOfTypes;
	
	// constructor constructs a list of tickets
	public MovieTicketManager () {
		ticketList = new ArrayList<>();
	}

	@Override
	public int numVisits(int id) {
		
		int numVisits =0;

		for(int i = 0; i < ticketList.size(); i++) {
			if( id == ticketList.get(i).getID()) {
				numVisits++;
			}
		}
	return numVisits;
	}

	@Override
	public int numThisMovie(int id, String movie) {

		int numThisMovie = 0;

		for(int i = 0; i < ticketList.size(); i++) {

			if(id == ticketList.get(i).getID() && movie.equals(ticketList.get(i).getName())){
				numThisMovie++;
			}
		}

		return numThisMovie;
	}

	@Override
	public int numMoviesToday(int id, int date) {

		int numMoviesToday=0;

		for(int i = 0; i < ticketList.size(); i++) {

			if(id == ticketList.get(i).getID() && date ==  ticketList.get(i).getDay()) {
				numMoviesToday++;
			}
		}


		return numMoviesToday;
	}

	@Override
	public double addTicket(String movieN, String rating, int d, int t, String f, String type, int id) {

		DecimalFormat numFormat = new DecimalFormat("0.0#");
		double ticketPrice = -1;

		Format format = Format.parseFromString(f);

		if (type.equalsIgnoreCase("ADULT")) {

			Adult x = new Adult( movieN, rating, d, t, format);
			ticketList.add(x);


			ticketPrice = x.calcualteTicketPrice();

		}else if (type.equalsIgnoreCase("CHILD")) {
			
			Child c = new Child( movieN, rating, d, t, format);
			
			ticketList.add(c);
			
			ticketPrice = c.calcualteTicketPrice();

		}else if(type.equalsIgnoreCase("EMPLOYEE")) {
			
			ticketList.add(new Employee ( movieN, rating, d, t, format, id));
			
			counter++;

			if(this.numVisits(id)<=2){
				
				ticketPrice = 0;
				
			}
			else {
				
				ticketPrice = ticketList.get(counter).calcualteTicketPrice();
				
			}

		}else if(type.equalsIgnoreCase("MOVIEPASS")) {
			
			ticketList.add(new MoviePass ( movieN, rating, d, t, format, id));
			
			counter++;
			
			if(this.numVisits(id) == 1) {
				
				ticketPrice = 9.99;
				
			}
			else if((this.numMoviesToday(id, d) == 1) && (this.numThisMovie(id, movieN) ==1) && (format == Format.NONE)){
				
				ticketPrice = 0;
				
			}else {
				
				ticketPrice = ticketList.get(counter).calcualteTicketPrice();
				
			}
		}
		
		ticketList.get(ticketList.size()-1).setTicketPrice(ticketPrice);

		return ticketPrice;
	}

	@Override
	public double totalSalesMonth() {

		double totalSalesMonth=0;


		for(int i = 0; i < ticketList.size(); i++) {
			
			totalSalesMonth += ticketList.get(i).getTicketPrice();
			
			System.out.println("this is price for " + i + " " +  ticketList.get(i).getTicketPrice() + "   " +totalSalesMonth);
			
		}
		
		System.out.println(totalSalesMonth);
		return totalSalesMonth;
	}

	@Override
	public String monthlySalesReport() {

		DecimalFormat format = new DecimalFormat("0.0#");


		return ("\tMonthly Sales Report \n" +
				"\t Sales \t Number\n" +
				"ADULT\t\t" + "$"+format.format(eachTypePrice("ADULT")) + "\t " + numOfTypes +"\n"+
				"CHILD\t\t" +"$"+format.format(eachTypePrice("CHILD")) + "\t " + numOfTypes +"\n" +
				"Employee\t" + "$"+format.format(eachTypePrice("EMPLOYEE")) + "\t " + numOfTypes +"\n"+
				"MoviePass\t" + "$"+format.format(eachTypePrice("MOVEIPASS")) + "\t " + numOfTypes +"\n");
	}

	@Override
	public ArrayList<String> get3DTickets() {

		ArrayList<String> three_DTickets = new ArrayList<>();
		
		ArrayList<Ticket> ticketList =sortByDay();

		for(int i = 0; i < ticketList.size(); i++) {

			if((ticketList.get(i).getFormat()) == Format.THREE_D){

				three_DTickets.add(ticketList.get(i).toString());
				
			}

		}
		return three_DTickets;
	}

	@Override

	public ArrayList<String> getAllTickets() {

		ArrayList<String> allTickets = new ArrayList<>();
		
		ArrayList<Ticket> tickets = new ArrayList<>();
		
		tickets = sortByDay();


		for(int i = 0; i < ticketList.size(); i++) {

			allTickets.add(tickets.get(i).toString());
			

		}
		for(int i =0; i < allTickets.size(); i++) {
			
			System.out.println(allTickets.get(i));
			
		}

		return allTickets;
	}

	@Override

	public ArrayList<String> getMoviePassTickets() {

		ArrayList<String> moviePassTickets = new ArrayList<>();
		
		ArrayList<Ticket> TicketList = sortByID();
		
		for(int i = 0; i < TicketList.size(); i++) {
			
			Ticket k = TicketList.get(i);
			
			if(k instanceof MoviePass) {
				moviePassTickets.add(k.toString());
			}
		}
		return moviePassTickets;
	}

	@Override
	/**
	 * reads from file into arraylist
	 * @param file file to be read from
	 * @throws FileNotFoundException when file is not found
	 */
	public void readFile(File file) throws FileNotFoundException {

		Scanner read = new Scanner(file);
		String[] array= new String[6];
		
		String name, rating, type, format;
		int time, day, ID, row = 0;
		Format format1;

		ArrayList<Ticket> readTicketList = new ArrayList<>();

		
			while(read.hasNextLine()) {

				array = read.nextLine().split(":");
				
				System.out.println(Arrays.toString(array));
				
				name = array[0];

				rating = array[1];

				day = Integer.parseInt(array[2]);
				
				time = Integer.parseInt(array[3]);

				format1 = Format.parseFromString(array[4]);
				
				type = array[5];
				
				ID = Integer.parseInt(array[6]);
				
				 
				try {
					if (type.equalsIgnoreCase("CHILD")) {
	
						ticketList.add(new Child(name, rating, day ,time, format1));
						
					}else if(type.equalsIgnoreCase("Adult")) {
						
						ticketList.add(new Adult(name, rating,day ,time, format1));
						
					}
					else if(type.equalsIgnoreCase("Employee")) {
						
						ticketList.add(new Employee(name, rating,day ,time,format1,ID));
						
					}
					else if(type.equalsIgnoreCase("MoviePass")) {
						
						ticketList.add(new MoviePass(name,rating,day ,time,format1,ID));
						
					}
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
	


	}


	public ArrayList<Ticket> sortByDay(){

		Ticket y;

		for(int i = 1; i < ticketList.size(); i++)
		{
			for(int j = i; j > 0; j-- )
			{
				if(ticketList.get(j).getDay() < ticketList.get(j-1).getDay())
				{
					
					y = ticketList.get(j);
					
					ticketList.set(j, ticketList.get(j-1));
					
					ticketList.set(j-1,y);
					
				}
			}

		}

		return ticketList;
	}

	private ArrayList<Ticket> sortByID(){

		Ticket x;

		for(int i = 1; i < ticketList.size(); i++) 
		{
			for(int j = i; j > 0; j--) 
			{
				if(ticketList.get(j).getID() < ticketList.get(j-1).getID()) 
				{
					
					x = ticketList.get(j);
					
					ticketList.set(j,ticketList.get(j-1));
					
					ticketList.set((j-1),x);
					
				}

			}
		}

		return ticketList;
	}


	public ArrayList<Ticket> getTicketList(){
		
		return ticketList;
		
	}

	public double eachTypePrice(String type) {
		
		double typePrice = 0;
		
		numOfTypes = 0;

		for(int i=0; i < ticketList.size(); i++)
		{
			if( ticketList.get(i) instanceof Child && type.equalsIgnoreCase("Child")) 
			{
				
				typePrice+=ticketList.get(i).getTicketPrice();
				
				numOfTypes++;
				
			}
			else if( ticketList.get(i) instanceof Adult && type.equalsIgnoreCase("Adult")) {
				
				typePrice+=ticketList.get(i).getTicketPrice();
				
				numOfTypes++;
				
			}
			else if( ticketList.get(i) instanceof Employee && type.equalsIgnoreCase("Employee")) {
				
				typePrice+=ticketList.get(i).getTicketPrice();
				
				numOfTypes++;
				
			}else if( ticketList.get(i) instanceof MoviePass && type.equalsIgnoreCase("MoviePass")) {
				
				typePrice+=ticketList.get(i).getTicketPrice();
				
				numOfTypes++;
				
			}

		}

		return typePrice;

	}
}
