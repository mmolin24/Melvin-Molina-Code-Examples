/**
 * subclass of ticket that calculates the price of the tickets that are added.
 * @author melvi
 *
 */
public class MoviePass extends Ticket {

	private int ID;
	private double NightPrice = 13.50;
	private double MorningPrice= 10.50;
	private double IMAXPrice = 3.00;
	private double three_DPrice = 2.50;
	private final double TAX = 0.096;
	
	/**
	 * constructor
	 * @param name
	 * @param rating
	 * @param day
	 * @param time
	 * @param format
	 * @param ID
	 */
	public MoviePass (String name, String rating, int day, int time, Format format, int ID) {
		super(name, rating, time, day, format, ID);
		this.ID = ID;
	}
	@Override
	public double calcualteTicketPrice() {
		double price=0;
		double ticket =0;
	
		// morning from 6-17
		if(super.getTime() > 6 && super.getTime() <18) {
			
			ticket = MorningPrice;
			
		}
		
		// night from 18 to 23 and from 0 to 6.
		if(super.getTime() >= 18 && super.getTime() <=23) {
			
			ticket = NightPrice ;
			
		}
		
		if(super.getFormat() == Format.IMAX ) {
			
			ticket += IMAXPrice;
			
		} else if(super.getFormat() == Format.THREE_D) {
			
			ticket += three_DPrice;
			
		}else if (super.getFormat() == Format.NONE) {
			
			price = ticket * (1 + TAX);
			
		}
		
		price = ticket * (1 + TAX); 

		return price;
	}
	@Override
	public int getID() {
		
		return ID;
	}
	
	@Override
	public String toString() {
		
		return "MOVIEPASS-" + ID  + super.toString();
		
	}
}
