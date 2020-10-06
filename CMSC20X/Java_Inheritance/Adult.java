/**
 * a subclass of ticket that will add a ticket to the array with specific pricing
 * @author melvi
 *
 */
public class Adult extends Ticket{

	// incase if the theater changes their pricing, all the values have been made private and have accessor and mutator
	//methods. 
	private double NightPrice = 13.50;
	private double MorningPrice= 10.50;
	private double IMAXPrice = 3.00;
	private double three_DPrice = 2.50;
	private final double TAXFEE = 0.096;
	
	
	/**
	 * @param name
	 * @param movie rating
	 * @param day of movie
	 * @param time of movie
	 * @param format Type
	 */
	public Adult(String name, String rating, int day, int time, Format format) {
		super(name, rating, time, day, format);
		
	}
	@Override
	public double calcualteTicketPrice() {
		double price=0;
		double ticket =0;
	
		//morning is from 6 to 17;
		if(super.getTime() > 6 && super.getTime() <18) {
			ticket = MorningPrice;
		}
		// nigh is from 18 to 23 and from 0 to 6.
		if(super.getTime() >= 18 && super.getTime() <=23) {
			ticket = NightPrice ;
		}

		
		if(super.getFormat() == Format.IMAX ) {
			ticket += IMAXPrice;
		} else if(super.getFormat() == Format.THREE_D) {
			ticket += three_DPrice;
		}else if (super.getFormat() == Format.NONE) {
			price = ticket * (1 + TAXFEE);
		}
		
		price = ticket * (1 + TAXFEE); 

		return price;
	}

	@Override
	public int getID() {
	
		return -1;
	}
	/**
	 * @return the night price
	 */
	public double getNightPrice() {
		return NightPrice;
	}
	/**
	 * @param NightPrice the NightPrice to set
	 */
	public void setNightPrice(double NightPrice) {
		this.NightPrice = NightPrice;
	}
	/**
	 * @return the day price
	 */
	public double getMorningPrice() {
		return MorningPrice;
	}
	/**
	 * @param  set Morning price
	 */
	public void setMorningPrice(double MorningPrice) {
		this.MorningPrice = MorningPrice;
	}
	/**
	 * @return IMAXPRICE
	 */
	public double getIMAXPrice() {
		return IMAXPrice;
	}
	/**
	 * @param set IMAXPRICE
	 */
	public void setIMAXPrice(double iMAXPrice) {
		IMAXPrice = iMAXPrice;
	}
	/**
	 * @return three_Dprice
	 */
	public double getThree_DPrice() {
		return three_DPrice;
	}
	/**
	 * @param set three_DPrice
	 */
	public void setThree_DPrice(double three_DPrice) {
		this.three_DPrice = three_DPrice;
	}

	@Override
	public String toString () {
		
		return "Adult" + super.toString();
	}
}
