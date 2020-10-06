import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter
{
	// instance to make translations
	private static MorseCodeTree t = new MorseCodeTree();
	
	
	public MorseCodeConverter()
	{
		
	}

		
	/**
	 * Converts Morse code into English. Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’. 
	 * Example: 
	 * code = ".... . .-.. .-.. --- / .-- --- .-. .-.. -.." 
	 * string returned = "Hello World"
	 * 
	 * @param code the morse code
	 * @return the English translation
	 */
	public static String convertToEnglish(String code)
	{
		String output = "";
		String[] wrd; // will hold single words 
		String[] let; // will hold single words
		
		// split each word in the current line into a new array. 
		wrd = code.split(" / ");

		// loops through words in line
		for(int i = 0; i < wrd.length; i++)
		{	
			
			
			// split letters into new array 
			let = wrd[i].split(" ");
			
			for(int j = 0; j < let.length; j++)
			{
				output += t.fetch(let[j]);	
			}
			
			//adding space after words finished translated
			output += " ";
		}	
		
		// trimming start and end
		output = output.trim();
		
		return output;
	}
	
	
	
	/**
	 * Converts Morse code into English. Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’. 
	 * Example: 
	 * code = ".... . .-.. .-.. --- / .-- --- .-. .-.. -.." 
	 * string returned = "Hello World"
	 * 
	 * @param codefile name of the File that contains Morse Code
	 * @return the English translation of the file
	 * @throws java.io.FileNotFoundException
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException
	{
		String output = "";
		ArrayList<String> line = new ArrayList<String>();
		String[] word; 
		String[] letter;
		
		Scanner inputFile;
		inputFile = new Scanner(codeFile);
		
		// reads from file and adds into arraylist
		while (inputFile.hasNext())
		{	
			line.add(inputFile.nextLine());
		}
			
		inputFile.close();
		
		// loops through arraylist
		for(int i = 0; i < line.size(); i++)
		{
			// split individual words
			word = line.get(i).split(" / ");
			
			// loop through  array with words
			for(int j = 0; j < word.length; j++)
			{
				// splitting words
				letter = word[j].split(" ");
				
				for(int k = 0; k < letter.length; k++)
				{

					output += t.fetch(letter[k]);	
				}
				
				//adding space after word translated
				output += " ";
			}
		}
	
		
		output = output.trim();
		
		return output;
	}

	
	/**
	 * Returns a string with all the data in the tree in LNR order with an space in between them. Uses the toArrayList method in MorseCodeTree.
	 * It should return the data in this order: "h s v i f u e l r a p w j  b d x n c k y t z g q m o" 
	 * Note the extra space between j and b - that is because there is an empty string that is the root, and in the LNR traversal, the root would
	 * come between the right most child of the left tree (j) and the left most child of the right tree (b). This is used for testing purposes to make
	 * sure the MorseCodeTree has been built properly
	 * 
	 * @return the data in the tree in LNR order separated by a space.
	 */
	public static String printTree()
	{
		// create new arraylist with the strings
		ArrayList<String> dataTree = new ArrayList<String>();
		
		dataTree = t.toArrayList();
		
		String str = "";
		
		for(int i = 0; i < dataTree.size(); i ++)
		{
			str += dataTree.get(i) + " ";	
		}
		
		return str;
	}
}