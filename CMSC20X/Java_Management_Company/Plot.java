public class Plot{

	private int x;

	private int y;

	private int depth;

	private int width;

	private ManagementCompany company = new ManagementCompany();
	
	Plot(){
			x = 0;

			y = 0;

			width = 1;

			depth = 1;
	}

	Plot(Plot p){

		this.x = p.getX();
	
		this.y = p.getY();

		this.width = p.getWidth();

		this.depth = p.getDepth();

	}

	Plot(int x, int y, int width, int depth){

		this.x = x;

		this.y = y;

		this.width = width;

		this.depth = depth;

	}
	
	
	
	public void setX(int x){

		this.x = x;

	}

	public void setY(int y){

		this.y = y;

	}

	public void setWidth(int width){

		this.width = width;

	}

	public void setDepth(int depth){

		this.depth = depth;

	}


	public int getX(){

		return x;

	}

	public int getY(){

		return y;

	}



	public int getDepth(){

		return depth;

	}


	public int getWidth(){

		return width;

	}

	public boolean overlaps(Plot p){



		return false;
	}
	
	public boolean encompasses(Plot p) {
		
		if((p.x + p.width) < (getX() + getWidth()) && (p.x > getX()) 
				&& p.y > getY() && (p.y + p.depth) < (getY() + getDepth ())) {
			return true;
		}
		else
		{
			return false;
		}	

	}


	public String toString() {
		String str = 
				"Upper left: (" + getX() + "," + getY() + "); "+
				"Width: " + getWidth() + " Depth: " + getDepth();
		return str;
	}


}