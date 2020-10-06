import java.util.ArrayList;

public class MyQueue <T> implements QueueInterface<T>{

	int defaultCapacity;
	int size;
	ArrayList<T> list;
	
	public MyQueue() {
		defaultCapacity = 5;
		size = 0;

		list = new ArrayList<T>(defaultCapacity);
		
	}
	
	
	public MyQueue(int size) {
		this.defaultCapacity = size;
		size = 0;

		list = new ArrayList<T>(defaultCapacity);
	
	}
	
	@Override
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean isFull() {
		if(this.size == this.defaultCapacity) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException{
		if(size == 0) {
			throw new QueueUnderflowException("The Queue is empty");
		}else {
			//T deq = list.get(front);
			size-=1;
			//front = (front + 1)%defaultCapacity;
			return list.remove(0);
			
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException{
		if(size == defaultCapacity) {
			throw new QueueOverflowException("The Queue is full");
		}else {
			list.add(e);
			size+=1;
			//rear = (rear + 1)%defaultCapacity;
			return true;
			
		}
	}

	@Override
	public String toString(String delimiter) {
		String phrase = "";
		for(int i = 0; i < list.size(); i++) {
			phrase+=list.get(i);
			if(i < list.size()-1) {
				phrase += delimiter;
			}
		}
		return phrase;
	}
	public String toString() {
		String phrase = "";
		for(int i = 0; i < list.size(); i++) {
			phrase+=list.get(i);
		}
		return phrase;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void fill(ArrayList<T> array) throws QueueOverflowException{
		size = 0;
		for(int i = 0; i < array.size(); i++) {
				this.list.add(array.get(i));
				size+=1;
		}
		if(array.size() > this.defaultCapacity) {
			throw new QueueOverflowException("Queue is full!");
		}
	}
	

}