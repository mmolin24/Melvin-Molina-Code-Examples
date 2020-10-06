import java.util.LinkedList;
/**
 * 
 * @author Melvin Molina
 *
 */

public class ConcordanceDataElement implements Comparable<ConcordanceDataElement> {
	private String concordanceWord;
	
	private LinkedList<Integer> pageNumbers;
	
	private int hashCodeNumber;

	/**
	 * Constructor
	 * @param word, the word for the concordance data element
	 */
	public ConcordanceDataElement(String word) {
		concordanceWord = word;
		pageNumbers = new LinkedList<Integer>();

	}

	/**
	 * Returns the word followed and page numbers
	 * @return a string in the format: 
	 * 	    word: page num, page num 
	 * 	    Example: after: 7, 9, 10
	 */
	public String toString() {

		String display;
		
		display = concordanceWord + ": ";

		for(int i = 0; i < pageNumbers.size(); i++) 
		{
			
			if(i < pageNumbers.size() - 1) 
			{
				display += pageNumbers.get(i) + ", ";
			}
			else {
				display += pageNumbers.get(i);
			}
		}
		return display;      
	}


	/**
	 * @return the word portion of the Concordance Data Element
	 */
	public String getWord(){
		
		return concordanceWord; 
		
	}


	/**
	 * The String class hashCode method may be used.
	 * @return the hashCode.
	 */
	public int hashCode(){
		
		hashCodeNumber = concordanceWord.hashCode();
		
		return hashCodeNumber;      
	}

	/**
	 * @return the linked list of integers that represent the line numbers
	 */
	public LinkedList<Integer> getList() {
		
		return pageNumbers;  
		
	}


	/**
	 * if the number doesn't exist in the list, add the page number.
	 * @param lineNum the line number to add to the linked list
	 */
	public void addPage(int lineNum) {
		if(!pageNumbers.contains(lineNum)) 
		{
			pageNumbers.addLast(lineNum);
		}
	}

	@Override
	public int compareTo(ConcordanceDataElement arg0) {
		return concordanceWord.compareTo(arg0.getWord());
	}
}