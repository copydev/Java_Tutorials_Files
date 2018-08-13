package priority_queue;

import java.util.ArrayList;

public class MaxPriorityQueue {
	private ArrayList<Integer> heap;
	
	public MaxPriorityQueue() {
		// TODO Auto-generated constructor stub
		heap = new ArrayList<>();
	}
	
	int getSize(){
		return heap.size();
	}
	
	boolean isEmpty(){
		return getSize()==0;
	}
	
	void insert(int ele){
		heap.add(ele);
		int childIndex = heap.size()-1;
		int parentIndex = (childIndex-1)/2;
		
		while(childIndex>0){
			if(heap.get(childIndex)>heap.get(parentIndex)){
				int temp = heap.get(childIndex);
				heap.set(childIndex,heap.get(parentIndex) );
				heap.set(parentIndex, temp);
				childIndex = parentIndex;
				parentIndex = (childIndex-1)/2;
			}
			else{
				return;
			}
		}
	}
	
	int getMax() throws PriorityQueueEmptyException{
		if(isEmpty()){
			throw new PriorityQueueEmptyException();
		}
		return heap.get(0);
	}
	
	int removeMax() throws PriorityQueueEmptyException{
		if(isEmpty()){
			throw new PriorityQueueEmptyException();
		}
		int ele = heap.get(0);
		int lastEle = heap.get(heap.size()-1);
		heap.remove(heap.size()-1);
		if(heap.size()==0){
			return ele;
		}
		heap.set(0,lastEle);
		int childIndex;
		int parentIndex = 0;
		while(parentIndex<heap.size()){
			int leftChildIndex = parentIndex*2+1 ;
			int rightChildIndex= parentIndex*2+2 ;
			if(leftChildIndex>=getSize()&&rightChildIndex>=getSize()){
				break;
			}
			else if(rightChildIndex>=getSize()){
				childIndex = leftChildIndex;
			}
			else if(heap.get(leftChildIndex)>heap.get(rightChildIndex)){
				childIndex = leftChildIndex;
			}
			else{
				childIndex = rightChildIndex;
			}
			
			if(heap.get(parentIndex)<heap.get(childIndex)){
			int temp = heap.get(childIndex);
			heap.set(childIndex, heap.get(parentIndex));
			heap.set(parentIndex, temp);
			parentIndex = childIndex;
			}
			else{
				break;
			}
			
		}
		return ele;
	}
	

}
