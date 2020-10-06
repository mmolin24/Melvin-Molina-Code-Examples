import java.util.ArrayList;

public class MyStack <T> implements StackInterface<T>{
	
	private int defaultCapacity;
	private int top;
	private ArrayList<T> list;
	

	public MyStack() {
		defaultCapacity = 5;
		top = -1;
		list = new ArrayList<>(defaultCapacity);
	}
	
	public MyStack(int capacity) {
		this.defaultCapacity = capacity;
		top = -1;
		list = new ArrayList<>(defaultCapacity);
	}

	
	@Override
	public boolean isEmpty() {
		if(top == -1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if(top == (defaultCapacity-1)){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public T pop() throws StackUnderflowException{
		int topLocation;
		if(top == -1) {
			throw new StackUnderflowException("The stack is empty");
		}else {
			topLocation = top;
			top = top-1;
			T poppedElement = list.get(topLocation);
			list.remove(topLocation);
			return poppedElement;
		}
	}

	@Override
	public T peek() throws StackUnderflowException{
		int topLocation;
		if(top == -1) {
			throw new StackUnderflowException("The stack is empty");
		}else {
			topLocation = top;
			//top = top+1;
			T poppedElement = list.get(topLocation);
			return poppedElement;
		}
	}

	@Override
	public int size() {
		return top+1;
	}

	@Override
	public boolean push(T e) throws StackOverflowException{
		if(top + 1 == defaultCapacity) {
			throw new StackOverflowException("The stack is full");
		}else {
			top = top+1;
			list.add(e);
			return true;
		}
	}

	@Override
	public String toString(String delimiter) {
		String phrase = "";
		for(int i = 0; i < top+1; i++) {
			phrase+=list.get(i);
			if(i < top) {
				phrase+=delimiter;
			}
		}
		return phrase;
		
	}
	public String toString() {
		String phrase = "";
		for(int i = 0; i < top+1; i++) {
			phrase+=(list.get(i));
		}
		return phrase;
		
	}

	@Override
	public void fill(ArrayList<T> array) throws StackOverflowException{
		for(int i = 0; i < array.size(); i++) {
			list.add(array.get(i));
			top+=1;
		}
		if( array.size() > defaultCapacity) {
			throw new StackOverflowException("Stack is full!");
		}
	}

}