import java.util.Comparator;
import java.util.ListIterator;

/**
 * 
 * @author Melvin Molina
 * CMSC
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

	private Comparator<T> comp;
	
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		
		this.comp=comparator;
		
	}

	/**
	 * Oparation invalid for a sorted list.
	 * @throws UnsupportedOperationException
	 */
	public void addToEnd() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
		
	/**
	 * Inserts the specified element at the correct position in the sorted list.
	 * @param data
	 * @return  a reference to the current object
	 */
	public SortedDoubleLinkedList<T> add(T data){
			
		Node<T> newNode = new Node<T>(data);
		
		Node<T> currentNode = null;
		
		if (getSize() == 0) {
			
			 start= newNode;
			 
			 end=newNode;
			 
		}
		if (getSize() > 0) {
			
			currentNode = start;
			
			Node<T>previousNode = null;
			
			
		while(currentNode != null) {	
		
			
		int compar =comp.compare( newNode.data,currentNode.data );
			
				if( compar == 0) {
					
					newNode.next = currentNode.next;
					
					currentNode.next = newNode;
					
					break;
				}
				else if (compar < 0) {
					 
					if (currentNode.equals(start)) {
						
					newNode.next = currentNode;
					
					start = newNode;
					
					}
				
					else {
						newNode.next = currentNode;
						
						previousNode.next = newNode;
						
						}
					
					break;
					}
		
				
				else if(compar > 0) {
					 
					if (currentNode.equals(end)) {
						
						end.next = newNode;
						
						end = newNode;
						
						break;
						
						}
				
					else {
						
						previousNode = currentNode;
						
						currentNode = currentNode.next;
						}
					
					}
				
				}
			}
		size++;
		
		return this;
}

	/**
	 * cals superclass
	 */
	public ListIterator<T> iterator(){
		return super.iterator();
	
	}

	/**
	 * calls the superclass remove method
	 */
	public BasicDoubleLinkedList<T> remove(T data,
			java.util.Comparator<T> comparator){
				return super.remove(data, comparator);	
	}


	public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("addToFront () is unsupported for a sorted list");
	}


	public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("addToEnd () is unsupported for a sorted list");
	}


}


