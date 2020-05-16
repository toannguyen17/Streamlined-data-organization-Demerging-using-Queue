package app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Objects;
import java.util.function.Consumer;

public class MyQueue<E> {
	private ArrayList<E> queueArr;

	public MyQueue() {
		this.queueArr = new ArrayList();
	}

	public boolean isQueueEmpty(){
		if (size() == 0){
			return true;
		}
		return false;
	}

	public int size(){
		return queueArr.size();
	}

	public void enqueue(E item) {
		queueArr.add(item);
	}

	public E dequeue() {
		if (isQueueEmpty())
			return null;

		return queueArr.remove(0);
	}

	public void sort(Comparator<E> comparator) {
		queueArr.sort(comparator);
	}

	public void forEach(Consumer<? super E> action) {
		final int size = this.size();
		for (int i=0; i < size; i++) {
			action.accept(queueArr.get(i));
		}
	}
}
