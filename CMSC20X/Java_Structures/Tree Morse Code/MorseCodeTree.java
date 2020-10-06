import java.util.ArrayList;
/**
 * 
 * @author Melvin M
 * CMSC 204
 * Assignment 5
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
	
	private TreeNode<String> root = null; // root of the tree, which is set null when the tree is empty
	
	private String fetchLetter; // variable to hold the String letter, which the fetch method will return
	
	/**
	 * Constructor - calls the buildTree method
	 */
	public MorseCodeTree()
	{
		buildTree(); 
	}
	/**
	 * This is a recursive method that adds element to the correct position in the tree based on the code. A '.' (dot) means traverse to the left. A "-" (dash) means traverse to the right. The code ".-" would be stored as the right child of the left child of the root Algorithm for the recursive method: 1. if there is only one character a. if the character is '.' (dot) store to the left of the current root b. if the character is "-" (dash) store to the right of the current root c. return 2. if there is more than one character a. if the first character is "." (dot) new root becomes the left child b. if the first character is "-" (dash) new root becomes the right child c. new code becomes all the remaining charcters in the code (beyond the first character) d. call addNode(new root, new code, letter)
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of addNode
	 */
	public void addNode(TreeNode<String> root, String code, String letter)
	{
		if(code.length() ==1) 
		{ 
			if(code.equals(".")) 
			{
				
				root.leftC = new TreeNode<String>(letter); 
				
			}
			else  
			{
				
				root.rightC = new TreeNode<String>(letter);
				
			}
			return;
		}
		else
		{
			if(code.substring(0, 1).equals(".")) 
			{
				
				addNode(root.leftC, code.substring(1),letter); 
				
			}
			else 
			{
				
				addNode(root.rightC, code.substring(1),letter); 
				
				
			}
			
		}
	}
	/**
	 * Returns a reference to the root
	 * @return reference to root
	 */
	@Override
	public TreeNode<String> getRoot() {
		
		return this.root;
		
	}
	/**
	 * sets the root of the MorseCodeTree
	 * @param newNode a copy of newNode will be the new root
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		
		root = newNode;
		
	}
	/**
	 * Adds element to the correct position in the tree based on the code This method will call the recursive method addNode
	 * @param code the code for the new node to be added, example  ".-."
	 * @param letter the letter for the new node to be added, example "r"
	 * @return reference object
	 */
	@Override
	public MorseCodeTree insert(String code, String letter) {
		
		addNode(root,code,letter);
		
		return this;
	}
	/**
	 * Fetch the data in the tree based on the code This method will call the recursive method fetchNode
	 * @param code the code describes the traversals to retrieve the string (letter)
	 * @return the string (letter) that corresponds to the code
	 */
	@Override
	public String fetch(String code) {
		
		return fetchNode(root,code);
	
	}
	/**
	 * This is the recursive method that fetches the data of the TreeNode that corresponds with the code A '.' (dot) means traverse to the left. A "-" (dash) means traverse to the right. The code ".-" would fetch the data of the TreeNode stored as the right child of the left child of the root
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of addNode 
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String str) {
		if(str.length() == 1) 
		{
			if(str.equals("."))
			{
				
				fetchLetter = root.leftC.getData(); 
				
			}
			else
			{
				
				fetchLetter = root.rightC.getData();
				
			}
		}
		else
		{
			if(str.substring(0, 1).equals(".")) 
			{
				
				fetchNode(root.leftC,str.substring(1));
				
			}
			else 
			{
				
				fetchNode(root.rightC,str.substring(1)); 
				
			}
		}
		return fetchLetter;
	}
	/**
	 * This operation is not supported in the MorseCodeTree
	 * @param data data of node to be deleted
	 * @return reference to the current tree or null
	 */
	@Override
	public MorseCodeTree delete(String data) throws UnsupportedOperationException {
		return null;
	}
	/**
	 * This operation is not supported in the MorseCodeTree
	 * @throws UnsupportedOperationException
	 * @return reference to the current tree or null
	 */
	@Override
	public MorseCodeTree update() throws UnsupportedOperationException {
		return null;
	}
	/**
	 * This method builds the MorseCodeTree by inserting the nodes of the tree level by level based on the code. The root will have a value of "" (empty string) level one: insert(".", "e"); insert("-", "t"); level two: insert("..", "i"); insert(".-", "a"); insert("-.", "n"); insert("--", "m"); etc. Look at the tree and the table of codes to letters in the assignment description.
	 */
	@Override
	public void buildTree() {
		root = new TreeNode<String>(""); // empty string root
				
				// 1st
				insert(".", "e");
				insert("-", "t");
				
				//2nd
				insert("..", "i");
				insert(".-", "a");
				insert("-.", "n");
				insert("--", "m");
				
				//3rd
				insert("...", "s");
				insert("..-", "u");
				insert(".-.", "r");
				insert(".--", "w");
				insert("-..", "d");
				insert("-.-", "k");
				insert("--.", "g");
				insert("---", "o");

				//4th
				insert("....", "h");
				insert("...-", "v");
				insert("..-.", "f");
				insert(".-..", "l");
				insert(".--.", "p");
				insert(".---", "j");
				insert("-...", "b");
				insert("-..-", "x");
				insert("-.-.", "c");
				insert("-.--", "y");
				insert("--..", "z");
				insert("--.-", "q");					
			}
	/**
	 * Returns an ArrayList of the items in the linked Tree in LNR (Inorder) Traversal order Used for testing to make sure tree is built correctly
	 * @return an ArrayList of the items in the linked Tree		
	 */
	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> printTree = new ArrayList<String>();

		LNRoutputTraversal(root, printTree);		

		return printTree;
	}
	/**
	 * The recursive method to put the contents of the tree in an ArrayList in LNR (Inorder)
	 * @param root  the root of the tree for this particular recursive instance
	 * @param list  the ArrayList that will hold the contents of the tree in LNR order
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if(root != null)
		{
			// recursive method to traverse through the binary tree in LNR (Inorder)
			LNRoutputTraversal(root.leftC, list);
			list.add(root.getData());
			LNRoutputTraversal(root.rightC, list);
		}
		
	}
	
	
	
	

}
