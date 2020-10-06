
public class Driver {

	public static void main(String[] args) {

		GraphInterface<Town, Road> graph;
		Town[] town;

		graph = new TownGraph();
		town = new Town[12];

		for (int i = 1; i < 12; i++) {
			  town[i] = new Town("Town_" + i);
			  graph.addVertex(town[i]);
		  }
		
		graph.addEdge(town[1], town[2], 2, "Road_1");
		graph.addEdge(town[1], town[3], 4, "Road_2");
		graph.addEdge(town[1], town[5], 6, "Road_3");
		graph.addEdge(town[3], town[7], 1, "Road_4");
		graph.addEdge(town[3], town[8], 2, "Road_5");
		graph.addEdge(town[4], town[8], 3, "Road_6");
		graph.addEdge(town[6], town[9], 3, "Road_7");
		graph.addEdge(town[9], town[10], 4, "Road_8");
		graph.addEdge(town[8], town[10], 2, "Road_9");
		graph.addEdge(town[5], town[10], 5, "Road_10");
		graph.addEdge(town[10], town[11], 3, "Road_11");
		graph.addEdge(town[2], town[11], 6, "Road_12");
		
		
		boolean result = graph.containsVertex(town[2]);
		System.out.println(result);
		

	}

}
