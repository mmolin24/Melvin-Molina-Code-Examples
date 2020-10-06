import java.util.*;

public class TownGraph implements GraphInterface<Town, Road> {

	// map that contains the roads and towns
	private Set<Town> townSet;
	private Set<Road> roadSet;

	public TownGraph() {
		
		townSet = new HashSet<Town>();
		roadSet = new HashSet<Road>();
		
	}

	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) 
	{
	if(!containsEdge(sourceVertex,destinationVertex) || sourceVertex == null || destinationVertex == null) 
	{
	return null;
	}
	else if(containsEdge(sourceVertex,destinationVertex)) 
	{
	for (Road road : roadSet)
	{
	Road testRoad = new Road(sourceVertex, destinationVertex,0,"");
	if(road.compareTo(testRoad) == 0) 
	{
	return road;
	}
	}
	}

	return null;
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {

		Road m = new Road(sourceVertex, destinationVertex, weight, description);

		if (townSet.size() == 0) {
			// adds the road into the set of roads
			roadSet.add(m);
			return m;
		} else if (!townSet.contains(sourceVertex) || !townSet.contains(destinationVertex)) {
			// checks to see if the parameter towns are inside the local townSet already
			// if they are not then throw an IllegalArgExcp
			throw new IllegalArgumentException();
		} else if (sourceVertex == null || destinationVertex == null) {
			// checks to see if the parameters are not null, if they are throw NPE
			throw new NullPointerException();
		} else {
			// adds the road into the set of roads
			roadSet.add(m);
			// returns the created road
			return m;
		}

	}

	@Override
	public boolean addVertex(Town v) {
		if (v == null) {
			return false;
		} else if (townSet.size() == 0) {
			townSet.add(v);
		}

		for (Town t : townSet) {
			if (t.equals(v)) {
				return false;
			} else {

				townSet.add(v);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		if (sourceVertex == null || destinationVertex == null) {
			return false;
		}

		// loop through the roadSet checking for matching edges
		// also checking vice versa cases as well.
		for (Road r : roadSet) {
			if (r.contains(sourceVertex) && r.contains(destinationVertex)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsVertex(Town v) {
		// checks if the current map has the vertex parameter

		for (Town t : townSet) {
			if (t.compareTo(v) == 0)
				return true;
		}
		return false;
	}

	@Override
	public Set<Road> edgeSet() {
		return this.roadSet;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> temp = new HashSet();

		// rotates through roadSet identifying the roads connected to this town
		for (Road r : roadSet) {
			if (r.getDestination().equals(vertex) || r.getSource().equals(vertex)) {
				// adds the roads found into the set
				temp.add(r);
			}
		}

		// returns local set
		return temp;
	}

	/**
	 * method that deleted the edge it receives
	 * 
	 * @param r
	 * @return the edge it receives, otherwise null
	 */
	public Road removeEdge(Road r) {

		if (roadSet.contains(r)) {
			roadSet.remove(r);
			return r;
		}

		return null;
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {

		// checks weight and description for being viable values
		if (weight < 0 || description == null) {
			return null;
		}

		// rotates through roadSet and finds the accurate road
		for (Road r : roadSet) {
			if (r.contains(sourceVertex) && r.contains(destinationVertex) && r.getWeight() == weight
					&& r.getName().equals(description)) {
				roadSet.remove(r);
				return r;
			}
		}

		// returns null if it does not find the road inside of the roadSet
		return null;
	}

	@Override
	public boolean removeVertex(Town v) {
		boolean result = false;

		// looks for the town that is equal to parameter
		for (Town t : townSet) {

			if (t.compareTo(v) == 0) {

				// looks through the roads and deletes roads that contain this town
				for (Road r : roadSet) {

					if (r.contains(t)) {

						roadSet.remove(r);
					}
				}
				// after deleting the road then deletes the town
				townSet.remove(t);
				result = true;
			}
		}

		return result;
	}

	@Override
	public Set<Town> vertexSet() {
		return townSet;
	}

	// STILL HAVE TO DO THESE
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {

		return null;
	}

	// STILL HAVE TO DO THESE
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		// TODO Auto-generated method stub

	}

}
