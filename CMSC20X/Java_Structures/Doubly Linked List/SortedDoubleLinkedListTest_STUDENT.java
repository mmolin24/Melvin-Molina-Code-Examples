import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class SortedDoubleLinkedListTest_STUDENT {
	DoubleComparator comparatorD;
	SortedDoubleLinkedList<Double> sortedLinkedDouble;
	

	@Before
	public void setUp() throws Exception {
		
		//STUDENT - use the SortedDoubleLinkedList<Double> for your STUDENT tests
		comparatorD = new DoubleComparator();
		sortedLinkedDouble = new SortedDoubleLinkedList<Double>(comparatorD);
	}

	@After
	public void tearDown() throws Exception {
		comparatorD = null;
		sortedLinkedDouble = null;
	}

	@Test
	//test the add to end. Use the corresponding test in the JUnit test provided
	//for you as an example
	public void testAddToEndSTUDENT() {
		try {
			sortedLinkedDouble.addToEnd(125.20);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	//test the add to front. Use the corresponding test in the JUnit test provided
	//for you as an example
	public void testAddToFrontSTUDENT() {
		try {
			sortedLinkedDouble.addToFront(125.21);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}


	@Test
	//test the iterator next. Use the corresponding test in the JUnit test provided
	//for you as an example
	public void testIteratorSuccessfulDoubleNext_STUDENT() {
		sortedLinkedDouble.add(125.01);
		sortedLinkedDouble.add(56.5);
		sortedLinkedDouble.add(80.0);
		sortedLinkedDouble.add(58.8);
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(56.5, iterator.next(),0.0001);
		assertEquals(58.8, iterator.next(),0.0001);
		assertEquals(80.0, iterator.next(),0.001);
		assertEquals(true, iterator.hasNext());
	}
	
	@Test
	//test the iterator previous. Use the corresponding test in the JUnit test provided
	//for you as an example
	public void testIteratorSuccessfulDoublePrevious_STUDENT() {
		
		sortedLinkedDouble.add(584.0);
		sortedLinkedDouble.add(28.8); 
		sortedLinkedDouble.add(487.59);
		sortedLinkedDouble.add(55.4);
		
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(28.8, iterator.next(),0.0001);
		assertEquals(55.4, iterator.next(),0.0001); 
		assertEquals(487.59, iterator.next(),0.0001); 
		assertEquals(584.00, iterator.next(),0.001);
		assertEquals(true, iterator.hasPrevious());
		assertEquals(584.00, iterator.previous(),0.011);
		assertEquals(487.59, iterator.previous(),0.001); 
		assertEquals(55.4, iterator.previous(),0.0001);  
	}
	
	@Test
	//test the iterator next when at the last element in the list. 
	//Use the corresponding test in the JUnit test provided
	//for you as an example
	public void testIteratorNoSuchElementException_STUDENT() {
		sortedLinkedDouble.add(58.9);
		
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		
		try{
			//remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}
	
	
	@Test
	//test the iterator remove which is an unsupported operation. 
	//Use the corresponding test in the JUnit test provided
	//for you as an example
	public void testIteratorUnsupportedOperationExceptionString() {
		
		sortedLinkedDouble.add(87.48);

		
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		
		try {
			//remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e){
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e){
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	//test the add method 
	//Use the corresponding test in the JUnit test provided
	//for you as an example
	public void testAdd_STUDENT() {
		sortedLinkedDouble.add(55.0);
		sortedLinkedDouble.add(235.0);
		sortedLinkedDouble.add(548.0);
		assertEquals(55.0, sortedLinkedDouble.getFirst(),0.0001);
		assertEquals(548.0, sortedLinkedDouble.getLast(),0.0001);
		sortedLinkedDouble.add(98.0); 
		sortedLinkedDouble.add(236.0); 
		assertEquals(55.0, sortedLinkedDouble.getFirst(),0.001);
		assertEquals(548.0, sortedLinkedDouble.getLast(),0.001);

		
		assertEquals(548,sortedLinkedDouble.retrieveLastElement(),0.001); 
		assertEquals(236.0, sortedLinkedDouble.getLast(),0.001);
	}
	
	
	@Test
	//test the remove element at beginning of the list
	//Use the corresponding test in the JUnit test provided
	//for you as an example
	public void testRemoveFirst_STUDENT() {
		
		sortedLinkedDouble.add(578.8);
		sortedLinkedDouble.add(561.0);
		assertEquals(561.0, sortedLinkedDouble.getFirst(),0.001);
		assertEquals(578.8, sortedLinkedDouble.getLast(),0.001);
		sortedLinkedDouble.add(10.01);
		assertEquals(10.01, sortedLinkedDouble.getFirst(),0.001);
		// remove the first
		sortedLinkedDouble.remove(10.01, comparatorD);
		assertEquals(561.0, sortedLinkedDouble.getFirst(),0.0001);
		
	}
	
	@Test
	//test the remove element at end of the list
	//Use the corresponding test in the JUnit test provided
	//for you as an example
	public void testRemoveEnd_STUDENT() {
		
		sortedLinkedDouble.add(784.5);
		
		sortedLinkedDouble.add(25.5);
		
		assertEquals(25.5, sortedLinkedDouble.getFirst(),0.001);
		
		assertEquals(784.5, sortedLinkedDouble.getLast(),0.001);
		
		sortedLinkedDouble.add(255.5);
		
		assertEquals(25.5, sortedLinkedDouble.getFirst(),0.001);
		
		sortedLinkedDouble.remove(784.5, comparatorD);
		
		assertEquals(784.5, sortedLinkedDouble.getLast(),0.001);
	}
	
	@Test
	//test the remove element in middle of the list
	//Use the corresponding test in the JUnit test provided
	//for you as an example
	public void testRemoveMiddle_STUDENT() {
		sortedLinkedDouble.add(645.0);
		sortedLinkedDouble.add(561.0);
		assertEquals(561.0, sortedLinkedDouble.getFirst(),0.001);
		assertEquals(645.0, sortedLinkedDouble.getLast(),0.001);
		sortedLinkedDouble.add(235.0);
		assertEquals(235.0, sortedLinkedDouble.getFirst(),0.001);
		assertEquals(645, sortedLinkedDouble.getLast(),0.001);
		assertEquals(3,sortedLinkedDouble.getSize());
		//remove from middle of list
		sortedLinkedDouble.remove(645.0, comparatorD);
		assertEquals(561.0, sortedLinkedDouble.getFirst(),0.001);
		assertEquals(645.0, sortedLinkedDouble.getLast(),0.0001);
		assertEquals(2,sortedLinkedDouble.getSize());
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
