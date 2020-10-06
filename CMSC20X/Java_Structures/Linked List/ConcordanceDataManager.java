import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class ConcordanceDataManager implements ConcordanceDataManagerInterface {
	private static ConcordanceDataStructure hashTable;
	
	private static Scanner input;
	
	int tableSize = 350;


	public ConcordanceDataManager() {
		hashTable = new ConcordanceDataStructure(tableSize);
	}

	public ArrayList<String> createConcordanceArray(String input) {
		
		int lineNum = 1;
		
		String data; 

		while (!input.isEmpty()) 
		{
			if (input.indexOf("\n") == -1) 
			{
				data = input;
				
				input = "";
			} else {
				data = input.substring(0, input.indexOf("\n"));
				
				input = input.substring(input.indexOf("\n") + 1);
			}
			while (!data.isEmpty()) 
			{
				data = data.toLowerCase();
				
				data = data.replaceAll("[\",.?;:!_]", "");
				
				data = data.replaceAll("and |the ", "");
				
				String[] checkWords;
				
				checkWords = data.split(" ");
				
				data = "";

				if (checkWords.length != 0) 
				{
					for (int i = 0; i < checkWords.length; i++) 
					{
						if (checkWords[i].length() > 2) 
						{
							String nextString; 

							nextString = checkWords[i];
							
							hashTable.add(nextString, lineNum);
						}
					}
				}
			}
			
			lineNum++;
		}
		
		return hashTable.showAll();
	}

	@SuppressWarnings("resource")
	public boolean createConcordanceFile(File input, File output) throws FileNotFoundException {
		
		Scanner inp;
		
		inp = new Scanner(input);
		
		int lineNum = 0;  {

			String nextString;
			
			int index;
			
			Scanner lineScanner;
			
			while(inp.hasNextLine()) 
			{

				lineScanner = new Scanner(inp.nextLine());
				
				lineNum++;
				while(lineScanner.hasNext()) 
				{
					nextString = lineScanner.next();
					
					index=nextString.length();

					while((nextString.length() > 0) && !Character.isLetter(nextString.charAt(index-1))) 
					{
						nextString = nextString.substring(0,index-1);
						
						index--;
					}

					while((nextString.length() > 0)&& !Character.isLetter(nextString.charAt(0))) 
					{
						nextString = nextString.substring(1);
					}

					if ((nextString.length() > 2) && (!nextString.equalsIgnoreCase("the")) && (!nextString.equalsIgnoreCase("and"))) 
					{
						hashTable.add(nextString.toLowerCase(),lineNum);
					}
				}
			}

			PrintWriter out = new PrintWriter(output);

			ArrayList<String> words = hashTable.showAll();
			for(String x : words) 
			{
				out.print(x);
			}
			
			out.close();
			
			return true;

		}
	}
}
