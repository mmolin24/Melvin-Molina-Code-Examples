import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface {

	private TownGraph graph;

	public TownGraphManager() {
		
		graph = new TownGraph();
		
	}

	/**
	 * Populate the Town Graph by reading from a file
	 * 
	 * @param file file read from
	 * @return true if read from file was successful, else return false
	 * @throws IOException if unable to read from the file
	 */
	@Override
	public boolean populateTownGraph(File codeFile) throws IOException{
		Scanner scan = new Scanner(codeFile);
		
		while(scan.hasNextLine()){
			String segment = scan.next();
			String[] mapInfo = segment.split("[,\\;]");
			int dist = Integer.parseInt(mapInfo[1]);
			addRoad( mapInfo[2], mapInfo[3], dist, mapInfo[0]);
		}
		
		scan.close();
		return true;
		}


	/**
	 * Adds a road with 2 towns and a road name
	 * 
	 * @param town1    name of town 1 (lastname, firstname)
	 * @param town2    name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		// adds into the graph the edge containing the parameter information
		graph.addVertex(new Town(town1));
		graph.addVertex(new Town(town2));
		graph.addEdge(new Town(town1), new Town(town2), weight, roadName);

		// returns the boolean of if it contains the edge with the parameter info
		return graph.containsEdge(new Town(town1), new Town(town2));

	}

	/**
	 * Returns the name of the road that both towns are connected through
	 * 
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null
	 *         if not
	 */
	@Override
	public String getRoad(String town1, String town2) {

		// returns a to string of the edge
		String str = graph.getEdge(new Town(town1), new Town(town2)).toString();

		return str;
	}

	/**
	 * Adds a town to the graph
	 * 
	 * @param v the town's name (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	@Override
	public boolean addTown(String v) {

		// adds a town into the graph
		graph.addVertex(new Town(v));

		// returns a boolean if the graph contains the town added
		return graph.containsVertex(new Town(v));
	}

	/**
	 * Determines if a town is already in the graph
	 * 
	 * @param v the town's name (lastname, firstname)
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String v) {
		// returns whether or not the graph contains a town of the parameter string
		return graph.containsVertex(new Town(v));
	}

	/**
	 * Determines if a road is in the graph
	 * 
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		// returns if there is an edge within the graph with said town connections
		return graph.containsEdge(new Town(town1), new Town(town2));
	}

	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * 
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads() {

		// setup an arraylist and a set
		ArrayList<String> roads = new ArrayList<>();
		Set<Road> road = graph.edgeSet();

		// loop throught the set adding the toStrings of the roads into the arraylist
		for (Road r : road) {
			roads.add(r.toString());
		}
		// use the Collections to sort the arraylist in alphabetical order
		Collections.sort(roads);

		// returns an alphabetical arraylist
		return roads;
	}

	/**
	 * Deletes a road from the graph
	 * 
	 * @param town1    name of town 1 (lastname, firstname)
	 * @param town2    name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		// method variable that will be returned
		boolean result = false;
		// method instance of road to then remove
		Road r = graph.getEdge(new Town(town1), new Town(town2));

		// gets to remove the edge only if it exists within the
		if (graph.containsEdge(new Town(town1), new Town(town2))) {

			graph.removeEdge(r);

			result = !graph.containsEdge(new Town(town1), new Town(town2));
		}

		return result;
	}

	/**
	 * Deletes a town from the graph
	 * 
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	@Override
	public boolean deleteTown(String v) {
		boolean result = false;

		// checks to see if the graph has the down
		if (graph.containsVertex(new Town(v))) {
			// removes the town
			graph.removeVertex(new Town(v));

			result = true;
		}
		return result;
	}

	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first
	 * name)
	 * 
	 * @return an arraylist of all towns in alphabetical order (last name, first
	 *         name)
	 */
	@Override
	public ArrayList<String> allTowns() {
		// creates method arraylist and set with graphs vertex's
		ArrayList<String> town = new ArrayList<>();
		Set<Town> towns = graph.vertexSet();

		// loops through the town set adding tostrings into the arrayList
		for (Town t : towns) {
			town.add(t.toString());
		}

		// calls collections sort to sort alphabetically
		Collections.sort(town);

		return town;
	}

	/**
	 * Returns the shortest path from town 1 to town 2
	 * 
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 *         towns have no path to connect them. They will be in the format:
	 *         startTown "via" road "to" endTown weight "miles" The last string in
	 *         the ArrayList will be the total miles of the path in the following
	 *         format: Total miles: #totalMiles As an example: if finding path from
	 *         Town_1 to Town_10, the ArrayList<String> would be in the following
	 *         format(this is a hypothetical solution): Town_1 via Road_2 to Town_3
	 *         4 miles (first string in ArrayList) Town_3 via Road_5 to Town_8 2
	 *         miles (second string in ArrayList) Town_8 via Road_9 to Town_10 2
	 *         miles (third string in ArrayList) Total miles: 8 miles
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		// TODO Auto-generated method stub
		return null;
	}

}
