

import java.io.File;
import java.io.IOException;
import java.util.*;

public interface TownGraphManagerInterface {
	
	/**
	 * Populate the Town Graph by reading from a file
	 * @param file file read from
	 * @return true if read from file was successful, else return false
	 * @throws IOException if unable to read from the file
	 */
	public boolean populateTownGraph(File file) throws IOException;
	
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	public boolean addRoad(String town1, String town2, int weight, String roadName);
	
	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	public String getRoad(String town1, String town2);
	
	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	public boolean addTown(String v);
	
	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town is in the graph, false if not
	 */
	public boolean containsTown(String v);
	
	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	public boolean containsRoadConnection(String town1, String town2);
	
	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	public ArrayList<String> allRoads();
	
	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	public boolean deleteRoadConnection(String town1, String town2, String road);
	
	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	public boolean deleteTown(String v);

	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	public ArrayList<String> allTowns();
	
	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 * They will be in the format: startTown "via" road "to" endTown weight "miles"
	 * The last string in the ArrayList will be the total miles of the path in the
	 * following format: Total miles: #totalMiles
	 * As an example: if finding path from Town_1 to Town_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Town_1 via Road_2 to Town_3 4 miles (first string in ArrayList)
	 * Town_3 via Road_5 to Town_8 2 miles (second string in ArrayList)
	 * Town_8 via Road_9 to Town_10 2 miles (third string in ArrayList)
	 * Total miles: 8 miles
	 */
	public ArrayList<String> getPath(String town1, String town2);
	
}