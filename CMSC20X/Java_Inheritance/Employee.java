/**
 * a subclass of ticket to add specific price to array
 * @author gabis
 *
 */
public class Employee extends Ticket{

	private double NightPrice = 13.50/2.0;
	private double morningPrice= 10.50/2.0;
	private double IMAXPrice = 3.00/2.0;
	private double three_DPrice = 2.5/2.0;
	private final double TAX = 0.096;
	private int ID;
	
	public Employee (String name, String rating, int day, int time, Format format, int ID) {
		super(name, rating, time, day, format, ID);
		this.ID = ID;
	}
	
	@Override
	public double calcualteTicketPrice() {
		double price=0;
		double ticket =0;
	
		// morning is from 6 to 17
		if(super.getTime() > 6 && super.getTime() <18) {
			ticket = morningPrice;
		}
		// night is 18 to 23 and from 0 to 6.
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
		return "Employee" + ID + super.toString();
	}

}
