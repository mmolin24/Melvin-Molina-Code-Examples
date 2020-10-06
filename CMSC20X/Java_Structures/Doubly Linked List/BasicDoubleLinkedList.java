
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import javafx.scene.Node;

/**
 * 
 * @author Melvin Molina
 * CMSC204
 * @param <T>
 */

public class BasicDoubleLinkedList<T> extends Object implements Iterable<T> {

	protected Node<T> start;
	protected Node<T> end;
	protected int size;

	/**
	 * The method containing the variable keeping track of size
	 * @return the size of the linked list
	 */

	public int getSize(){
		return size;
	}

	/**
	 * constructor for a basic doublelinkedlist
	 * @param starting element
	 * @param ending element
	 * @param size
	 */
	public BasicDoubleLinkedList() {

		this.start= null;
		
		this.end = null;
		
		this.size = 0;
	}

	/**
	 * adds generic to the end of the list
	 * @param data from node
	 * @return address of current object
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {

		Node<T> newNode = new Node<T>(data);

		if (getSize() == 0) { 

			start = newNode;
			end = newNode;
		} 
		else if(getSize()>0) {

			start.next = newNode;
			end = newNode;
		}

		size++;
		return this;
	}

	/**
	 * adds to the front of the list
	 * @param data from node within list
	 * @return  address of current object
	 */
	public BasicDoubleLinkedList<T> addToFront(T data){

		Node<T> newNode = new Node<T>(data);


		if (size == 0) { 

			start = newNode; 
			
			end = newNode;
		} 
		else {

			newNode.next = start; 
			start = newNode;
		}

		size++;
		return this;
	}
	
	/**
	 * returns the start node, if none then return false
	 * @return  the data element or null
	 */
	public T getFirst() {
		if(size>0) 
			return start.data;
		else
			return null;

	}	

	/**
	 * returns the first elements from the list if none return false
	 * @return data element or null
	 */
	public T retrieveFirstElement() {

		if (size > 0) {

			Node<T> currentNode = start;

			start = start.next; 
			
			size--; 

			return currentNode.data;
		} 
		else {

			return null;
		}

	}

	/**
	 * returns last element on the list if none return false
	 * @return  the data element or null

	 */
	public T getLast() {

		if(getSize()>0) 
			return end.data;
		else
			return null;
	}


	/**
	 * Removes and returns the last element from the list 
	 * If there are no elements the method returns null
	 * @return last element data or null
	 */
	public T retrieveLastElement()
	{

		
		if (size > 0) {
			
			Node<T> currentNode = start;
			
			Node<T> previousNode = null;


			while (currentNode != null) {

				if (currentNode.equals(end)) {
					
					end = previousNode;
					
					break;
				}

				previousNode = currentNode;
				
				currentNode = currentNode.next;
				
			}

			size--;
			return currentNode.data;
		} 
		else {
			return null;
		}
	}


	/**
	 * returns an arraylist of all the data respectively
	 * @return an arraylist of the items in the list
	 */
	public ArrayList<T> toArrayList() {

		ArrayList<T> newList = new ArrayList<T>();

		
		BasicDoubleLinkedList<T>.Node<T> currentNode = start;

		// iterate through nodes list
		
		while (currentNode != null) {

			newList.add((T) currentNode.data);

			currentNode = currentNode.next;
		}

		return newList;
	}


	
	public  class DoubleLinkedListIterator implements ListIterator<T>
	{
		private Node <T> ip;
		private Node<T> ipPrev;

		public DoubleLinkedListIterator() {
			ip = start;
			ipPrev = null;
		}

		public T next() {
			if(ip != null) {
				T returnData = (T) ip.data;
				ipPrev = ip;
				ip = ip.next;

				if(ip != null) 
					ip.previous = ipPrev;
				return returnData;
			}
			else
				throw new NoSuchElementException();
		}

		public boolean hasNext() {
			if(ip==null)
				return false;
			else
				return true;
		}

		public T previous()	{
			if(ipPrev != null) {

				ip = ipPrev;
				ipPrev= ip.previous;
				T returnData = ip.data;
				return returnData;
			}
			else
				throw new NoSuchElementException();
		}

		public boolean hasPrevious() {
			if(ipPrev==null)
				return false;
			else
				return true;
		}

		//errors 
		public void remove( ) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		
		public void add(T arg0) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		
		public int  previousIndex()  throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		
		public void  set(T arg0) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

	} 

	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
		return new DoubleLinkedListIterator();
	}

	/**
	 * Removes the first instance of the targetData from the list. Notice that you must remove the elements by performing a single traversal over the list.
	 * @param element - the data element to be removed
	 * @param comparator the comparator to determine equality of data elements
	 * @return data element or null
	 */
	public BasicDoubleLinkedList<T> remove(T element, Comparator<T> comparator) {

		Node<T> currentNode = start;
		Node<T> previousNode = null;

		// Iterate through node list
		while (currentNode != null) {
			// Check if current node data matches query
			if (comparator.compare(currentNode.data, element) == 0) {
				// Check if current node is first/last/middle node
				if (currentNode.equals(start)) {
					// Replace current first node
					start = start.next;
				} else if (currentNode.equals(end)) {
					// Replace current last node
					start = previousNode;
				} else {
					// Replace current middle node
					previousNode.next = currentNode.next;
				}
				// Note: Remove return to have algorithm delete all nodes with given node data instead of just first
			}

			previousNode = currentNode; // Update node pointers
			currentNode = currentNode.next;
		}
		// End routine
		size--;
		return this;

	}

	@SuppressWarnings("hiding")
	protected class Node<T> {
		protected T data;
		protected Node<T> previous;
		protected Node <T> next;

		public Node(T data ) {
			this.data= data;

		}
		public Node () {

			data=null;
			next=null;

		}

		public Node(T data, Node<T> node)
		{
			this.data = data;
			this.next = node;
		}

	}

}
