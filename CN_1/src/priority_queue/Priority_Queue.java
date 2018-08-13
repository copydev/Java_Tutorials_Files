package priority_queue;

import java.util.ArrayList;

public class Priority_Queue {

	private ArrayList<Integer> heap;

	public Priority_Queue() {
		heap = new ArrayList<>();
	}

	public boolean isEmpty() {
		return heap.size() == 0;
	}

	public int size() {
		return heap.size();
	}

	int getMin() throws PriorityQueueEmptyException {
		if (isEmpty()) {
			throw new PriorityQueueEmptyException();
		}
		return heap.get(0);
	}

	public void insert(int element) {
		heap.add(element);
		int childIndex = heap.size() - 1;
		int parentIndex = (childIndex - 1) / 2;

		while (childIndex > 0) {
			if (heap.get(childIndex) < heap.get(parentIndex)) {
				int temp = heap.get(childIndex);
				heap.set(childIndex, heap.get(parentIndex));
				heap.set(parentIndex, temp);
				childIndex = parentIndex;
				parentIndex = (childIndex-1)/2;
			}
			else{
				return;
			}
		}
		
		
	}
	
	public int removeMin() throws PriorityQueueEmptyException{
		if(heap.isEmpty()){
			throw new PriorityQueueEmptyException();
		}
		int lastElement = heap.get(heap.size()-1);
		int element = heap.get(0);
		heap.remove(heap.size()-1);
		if(heap.isEmpty()){
			return element;
		}
		heap.set(0, lastElement);
		int parentIndex = 0;
		int childIndex;
		while(parentIndex<heap.size()){
			int leftChildIndex = parentIndex *2 +1;
			int rightChildIndex = parentIndex*2+2;
			if(rightChildIndex>=heap.size()&&leftChildIndex>=heap.size()){
				break;
			}
			else if(rightChildIndex>=heap.size()){
				childIndex = leftChildIndex;
			}
			else if(heap.get(leftChildIndex)>heap.get(rightChildIndex)){
				childIndex = rightChildIndex;
			}
			else{
				childIndex = leftChildIndex;
			}
			if(heap.get(childIndex)<heap.get(parentIndex)){
				int temp = heap.get(childIndex);
				heap.set(childIndex, heap.get(parentIndex));
				heap.set(parentIndex, temp);
				parentIndex = childIndex;
				
			}
			else{
				break;
			}
		}
		
		return element;
		
	}
	
	

}
