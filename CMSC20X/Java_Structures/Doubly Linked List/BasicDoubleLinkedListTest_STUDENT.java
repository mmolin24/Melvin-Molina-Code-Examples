import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BasicDoubleLinkedListTest_STUDENT {
	BasicDoubleLinkedList<Double> linkedDouble;
	DoubleComparator comparatorD;

	@Before
	public void setUp() throws Exception {
		
		//STUDENT: Use the linkedDouble for the STUDENT tests
		linkedDouble = new BasicDoubleLinkedList<Double>();
		//add doubles to the linkedDouble list
		//Example: linkedDouble.addToEnd(15.0);
		linkedDouble.addToEnd(20.5);
		
		linkedDouble.addToEnd(19.5);
		
		linkedDouble.addToEnd(95.5);
		
		comparatorD = new DoubleComparator();
	}

	@After
	public void tearDown() throws Exception {
		linkedDouble = null;
		comparatorD = null;
	}

	@Test
	public void testGetSize() {
		
		assertEquals(3, linkedDouble.getSize());
		
	}
	
	@Test
	public void testAddToEndSTUDENT(){
		//test addToEnd for the linkedDouble
		assertEquals(95.5, linkedDouble.getLast(), .0001);

		linkedDouble.addToEnd(150.5);
		assertEquals(150.5, linkedDouble.getLast(), .0001);
		
	}

	@Test
	public void testAddToFrontSTUDENT(){
		
		assertEquals(20.5, linkedDouble.getFirst(), .001);
		
		linkedDouble.addToFront(5.2);
		
		assertEquals(5.2, linkedDouble.getFirst(), .0001);
		
	}

	@Test
	public void testGetFirstSTUDENT(){
		//test getFirst for the linkedDouble
		assertEquals(20.5, linkedDouble.getFirst(), .001);
		
		linkedDouble.addToFront(194.5);
		
		assertEquals(194.5, linkedDouble.getFirst(), .001);
		
	}

	@Test
	public void testGetLastSTUDENT(){
		assertEquals(95.5, linkedDouble.getLast(),0.001);
		linkedDouble.addToEnd(45.65);
		assertEquals(45.65, linkedDouble.getLast(),0.001);
	}
	
	@Test
	public void testToArraySTUDENT(){
		//test toArray for the linkedDouble
		
		ArrayList<Double> list;
		
		
		list = linkedDouble.toArrayList();
		
		assertEquals(20.5, list.get(0),0.001);
		
	}
	
	@Test
	public void testIteratorSuccessfulNextSTUDENT(){
		//test the iterator for the linkedDouble
		//be throughal, use the corresponding test method in the provided 
		//JUnit test an example
		
		linkedDouble.addToFront(266.6);
		
		linkedDouble.addToEnd(20.59);
		
		ListIterator<Double> iterator = linkedDouble.iterator();
		
		assertEquals(true, iterator.hasNext());
		
		assertEquals(266.6, iterator.next(),0.001);
		
	}
	
	@Test
	public void testIteratorSuccessfulPreviousSTUDENT(){
		//test the iterator for the linkedDouble
		//be throughal, use the corresponding test method in the provided 
		//JUnit test an example
		linkedDouble.addToFront(52.9);

		
		ListIterator<Double> iterator = linkedDouble.iterator();
		
		assertEquals(true, iterator.hasNext());
		
		assertEquals(52.9, iterator.next(),0.001);
		
		assertEquals(true,iterator.hasPrevious());
		
		assertEquals(52.9, iterator.previous(), .001);
		
	}
	
	
	@Test
	public void testIteratorNoSuchElementExceptionNextSTUDENT(){
		//test the iterator for the linkedDouble.  Exception raised
		//when next is called after last element.
		//be throughal, use the the corresponding test method in the provided 
		//JUnit test an example
		linkedDouble.addToFront(87.25);
		linkedDouble.addToEnd(251.25);
		ListIterator<Double> iterator = linkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(87.25, iterator.next(), 0.0001);

		try{
			//no more elements in list
			iterator.next();
			iterator.next();

			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionPreviousSTUDENT(){
		//test the iterator for the linkedDouble.  Exception raised
		//when prvious is called when before the first element.
		//be throughal, use the the corresponding test method in the provided 
		//JUnit test an example
		linkedDouble.addToFront(124.0);
		linkedDouble.addToEnd(864.01);
		ListIterator<Double> iterator = linkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(124.0, iterator.next(),0.001);
		assertEquals(864.01, iterator.next(),0.001);
		assertEquals(864.01, iterator.previous(), .001);
		try{
			//no more elements in list
			iterator.previous();
			iterator.previous();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}

	@Test
	public void testIteratorUnsupportedOperationExceptionSTUDENT(){
		//test the remove method for the iterator for the linkedDouble
		//be throughal, use the the corresponding test method in the provided 
		//JUnit test an example
		linkedDouble.addToFront(123.0);
		linkedDouble.addToEnd(125.14);
		ListIterator<Double> iterator = linkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(123.0, iterator.next(),0.001);
		assertEquals(125.14, iterator.next(),0.0001);
		
		try{
			//remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}
	
	@Test
	public void testRemoveSTUDENT(){
		//test the remove method for the linkedDouble
		//be throughal, remove from the front of the list, the
		//end of the list and the middle of the list, 
		//use the the corresponding test method in the provided 
		//JUnit test an example
		assertEquals(20.5, linkedDouble.getFirst(),0.001);
		
		assertEquals(95.5, linkedDouble.getLast(),0.0001);
		
		linkedDouble.addToFront(22.5);
		
		assertEquals(22.5, linkedDouble.getFirst(),0.0001);
		
		linkedDouble.remove(22.5, comparatorD);
		
		assertEquals(20.5, linkedDouble.getFirst(),0.001);
		
	}
	
	@Test
	public void testRetrieveFirstElementSTUDENT(){
		//test retrieveLastElement for linkedDouble
		assertEquals(20.5, linkedDouble.getFirst(),0.001);
		
		linkedDouble.addToFront(125.01);
		
		assertEquals(125.01, linkedDouble.getFirst(),0.001);
		
		assertEquals(125.01, linkedDouble.retrieveFirstElement(),0.001);
		
		assertEquals(20.5,linkedDouble.getFirst(),0.001);
		
		assertEquals(20.5, linkedDouble.retrieveFirstElement(),0.001);
		
	}
	
	@Test
	public void testRetrieveLastElementSTUDENT(){
		//test retrieveLastElement for linkedDouble
		assertEquals(95.5, linkedDouble.getLast(),0.001);
		linkedDouble.addToEnd(50.0);
		assertEquals(50.0, linkedDouble.getLast(),0.001);
		assertEquals(50.0, linkedDouble.retrieveLastElement(),0.001);


	}

	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
}
