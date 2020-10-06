import java.util.ArrayList;
import java.util.LinkedList;

public class ConcordanceDataStructure implements ConcordanceDataStructureInterface{

	private double DoubleSize;
	
	private int size;
	
	@SuppressWarnings("unused")
	
	private int numberOfWords = 0;
	
	private int prime = 0;
	
	private boolean ifPrime;
	
	private LinkedList<ConcordanceDataElement>[] table;

	/**
	 * This constructor takes in an integer which represents the estimated number of words in the text. 
	 * Determine the size of the table by using a loading factor of 1.5 and a 4K+3 prime. 
	 * Example if you estimated 500 words, 500/1.5 = 333. The next 4K+3 prime over 333 is 347. 
	 * So you would make the table a length of 347.
	 * @param num the estimated number of words in the text
	 */
	public ConcordanceDataStructure(int num) {
		DoubleSize = (num * 1.0) / 1.5;
		size = (int)DoubleSize;
		do {
			ifPrime = true;
			
			//checking if size is even
			if (size%2==0)
				ifPrime = false;
			
			//if not, then just check the odds
			for(int i=3;i*i<=size;i+=2) 
			{
				
				if(size%i==0)
					ifPrime = false;
			}

			if(ifPrime == true) 
			{
				if(((size - 3)) % 4.0 == 0)
					prime = size;
				else
					size++;
			} else
				size++;
		} while(!ifPrime || prime == 0);
		
		table = new LinkedList[size];
	}
	/**
	 * Constructor for testing purposes The string will be "Testing" and 
	 * the int will be the size of the hash table. This is used only for testing.
	 * @param test the string "Testing"
	 * @param size the size of the hash table
	 */
	public ConcordanceDataStructure(String test, int size) {
		
		this.size = size;
		
		table = new LinkedList[size];
	}
	/**
	 * Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	 */
	@Override
	public int getTableSize() {
		
		return size;
		
	}
	/**
	 * Returns an ArrayList of the words at this index [0] of the ArrayList holds the first word in the "bucket" (index) [1] of 
	 * the ArrayList holds the next word in the "bucket", etc. This is used for testing
	 * @return an ArrayList of the words at this index
	 */
	@Override
	public ArrayList<String> getWords(int index) {
		
		ArrayList<String> words = new ArrayList<String>();
		
		for(int i = 0; i < table[index].size(); i++) 
		{
			words.add(table[index].get(i).getWord());
		}
		return words;
	}

	/**
	 * Returns an ArrayList of the Linked list of page numbers for each word at this index [0] of 
	 * the ArrayList holds the LinkedList of page numbers for the first word in the "bucket" (index) [1] of 
	 * the ArrayList holds the LinkedList of page numbers for next word in the "bucket", etc. This is used for testing
	 * @param index location within the hash table
	 */
	@Override
	public ArrayList<LinkedList<Integer>> getPageNumbers(int index) {
		ArrayList<LinkedList<Integer>> pageNumbers = new ArrayList<LinkedList<Integer>>();
		
		for(int i = 0; i < table[index].size(); i ++) 
		{
			pageNumbers.add(table[index].get(i).getList());
		}
		return pageNumbers;
	}
	/**
	 * Use the hashcode of the ConcordanceDataElement to see if it is in the hashtable. 
	 * If the word does not exist in the hashtable - Add the ConcordanceDataElement to the hashtable. 
	 * Put the line number in the linked list If the word already exists in the hashtable 1. 
	 * add the line number to the end of the linked list in the ConcordanceDataElement (if the line number is not currently there).
	 * 
	 * @param term the word to be added/updated with a line number
	 * @param lineNum the line number where the word is found
	 */
	@Override
	public void add(String word, int lineNum) {
		int index;
		boolean added = false;
		ConcordanceDataElement element = new ConcordanceDataElement(word);
		index = element.hashCode() % size;
		
		if(index < 0)
			index = index * -1;
		
		if(table[index] != null) 
		{
			for(int i = 0; i < table[index].size(); i ++) 
			{
				if(table[index].get(i).compareTo(element) == 0) 
				{
					table[index].get(i).addPage(lineNum);
					
					numberOfWords++;
					
					added = true;
				}
			}
		}
		
		else {
			LinkedList<ConcordanceDataElement> listAdd = new LinkedList<ConcordanceDataElement>();
			
			listAdd.add(element);
			
			table[index] = listAdd;
			
			table[index].getFirst().addPage(lineNum);
			
			numberOfWords++;
			
			added = true;
		}
		
		if(added == false) 
		{
			table[index].add(element);
			table[index].getLast().addPage(lineNum);
			numberOfWords++;
		}
	}
	/**
	 * Display the words in Alphabetical Order followed by a :, followed by the line numbers in numerical order, 
	 * followed by a newline here's an example: after: 129, 175 agree: 185 agreed: 37 all: 24, 93, 112, 175, 203 always: 90, 128
	 * @return an ArrayList of Strings. Each string has one word, followed by a :, 
	 * followed by the line numbers in numerical order, followed by a newline.
	 */
	@Override
	public ArrayList<String> showAll() {
		
		ArrayList<String> string = new ArrayList<String>();
		
		ArrayList<ConcordanceDataElement> listCombined = new ArrayList<ConcordanceDataElement>();
		
		ConcordanceDataElement copy;
		
		int index = 0;
		
		for(int i = 0; i < size; i++) 
		{
			if(table[i] != null)
				for(int j = 0; j < table[i].size(); j++) 
				{
					
					copy = table[i].get(j);
					
					listCombined.add(copy);
					
					index++;
				}
		}

		ConcordanceDataElement smallest;
		
		ConcordanceDataElement temp;
		int smallestIndex;
		
		for(int i = 0; i < listCombined.size(); i++) 
		{
			smallestIndex = i;
			
			smallest = listCombined.get(i);
			
			for(int j = i; j < listCombined.size(); j++) 
			{
				if(listCombined.get(j).compareTo(smallest) < 0) 
				{
					smallest = listCombined.get(j);
					
					smallestIndex = j;
				}
			}
			
			temp = listCombined.get(i);
			
			listCombined.remove(i);
			
			listCombined.add(i, listCombined.get(smallestIndex - 1));
			
			listCombined.remove(smallestIndex);
			
			listCombined.add(smallestIndex, temp);
		}

		for(int i = 0; i < listCombined.size(); i++) 
		{
			string.add(listCombined.get(i).toString() + "\n");
		}

		return string;
	}
}
