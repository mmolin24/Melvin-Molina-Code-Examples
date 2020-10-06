/**
 * a class of child that's  form of a ticket 
 * @author melvi
 *
 */
public class Child extends Ticket{

	private double NightPrice = 10.75;
	private double beforeSixPrice= 5.75;
	private double IMAXPrice = 2.00;
	private double three_DPrice = 1.50;
	private final double TAX = 0.096;
	

	// constructor to form a child ticket
	public Child(String name, String rating, int day, int time, Format format) {
		super(name, rating, time, day, format);
		
	}
	@Override
	public double calcualteTicketPrice() {
		double price=0;
		double ticket =0;
	
		// morning to be from 6-17;
		if(super.getTime() > 6 && super.getTime() <18) {
			ticket = beforeSixPrice;
		}
		//time is night
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
		
		return -1;
	}
	
	@Override
	public String toString() {
		
		return "Child " + super.toString();
	}

}
