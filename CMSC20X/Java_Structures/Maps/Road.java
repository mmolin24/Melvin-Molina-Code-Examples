// THIS IS AN EDGE
public class Road implements Comparable<Road> {

	private int weight;
	private Town source;
	private Town destination;
	private String name;

	public Road(Town source, Town destination, int weight, String name) {

		this.weight = weight;
		this.source = source;
		this.destination = destination;
		this.name = name;

	}

	public Road(Town source, Town destination, String name) {

		this.weight = 1;
		this.source = source;
		this.destination = destination;
		this.name = name;

	}

	// asdkjaskldjalskdjlasdjlakjsd
	public String toString() {
		return "";
	}

	public int getWeight() {
		return this.weight;
	}

	public Town getSource() {

		return this.source;
	}

	public String getName() {

		return this.name;
	}

	public Town getDestination() {

		return this.destination;
	}

	public boolean contains(Town town) {
		if (this.source.equals(town) || this.destination.equals(town)) {
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Road r) {
		if (this.name.equals(r.getName())) {
			return 0;
		}
		return 1;
	}

	public boolean equals(Road r) {
		if (this.destination.equals(r.destination)) {
			return true;
		}
		return false;
	}
}
