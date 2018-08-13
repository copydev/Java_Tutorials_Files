package priority_queue;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class StringLength implements Comparator<String>{
	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		if( o1.length()<o2.length()){
			return -1;
		}
		else if(o1.length()>o2.length()){
			return 1;
		}
		else
		return 0;
	}
}

public class PriorityQueueUse {
	
	
	public static void sortKSorted(int arr[], int k){
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int i =0;
		for(;i<k;i++){
			pq.add(arr[i]);
		}
		for(;i<arr.length;i++){
			arr[i-k]=pq.remove();
			pq.add(arr[i]);
		}
		
		for(int j = arr.length -k;j<arr.length;j++){
			arr[j] = pq.remove();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		MaxPriorityQueue pr = new MaxPriorityQueue();
//		int arr[] = {5,9,1,2,0,3};
//		for(int i = 0;i<arr.length;i++){
//			pr.insert(arr[i]);
//			
//		}
//		while(!pr.isEmpty()){
//			try {
//				System.out.println(pr.removeMax());
//			} catch (PriorityQueueEmptyException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
//		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
//		int arr[] = {2,4,1,9,6,8};
//		for(int i =0 ;i<arr.length;i++){
//			pq.add(arr[i]);
//		}
//		sortKSorted(arr, 2);
//		//System.out.println(pq.element());
//		while(!pq.isEmpty()){
//			System.out.println(pq.remove()+" ");
//		}
		
		String arr[] = {"This", "is", "a" ,"string","array"};
		PriorityQueue<String> pq = new PriorityQueue<>(new StringLength());
		for(int i = 0;i<arr.length;i++){
			pq.add(arr[i]);
		}
		while(!pq.isEmpty()){
			System.out.println(pq.remove()+" ");
		}

	}
	
	public static void runningMedian(int arr[]) {
		/* Your class should be named Solution.
		 * Don't write main() function.
		 * Don't read input, it is passed as function argument.
		 * Print output as specified in the question
		 */
      PriorityQueue<Integer> smallHeap = new PriorityQueue<>(Collections.reverseOrder());
      PriorityQueue<Integer> bigHeap = new PriorityQueue<>();
      for(int i =0;i<arr.length;i++){
        addNumber(arr[i],smallHeap,bigHeap);
        rebalance(smallHeap,bigHeap);
        int median = getMedian(smallHeap,bigHeap);
        System.out.println(median);
      }
      
	}
  
  public static void addNumber(int num,PriorityQueue<Integer> smallHeaps,PriorityQueue<Integer> bigHeaps ){
    if(smallHeaps.size()==0||smallHeaps.peek()>num){
      smallHeaps.add(num);
    }
    else{
      bigHeaps.add(num);
    }
  }
  
  public static void rebalance(PriorityQueue<Integer> smallHeaps,PriorityQueue<Integer> bigHeaps){
    if(smallHeaps.size()==bigHeaps.size()){
      return;
    }
    PriorityQueue<Integer> smallSize = smallHeaps.size()<bigHeaps.size()?smallHeaps:bigHeaps;
    PriorityQueue<Integer> bigSize = smallHeaps.size()<bigHeaps.size()?bigHeaps:smallHeaps;
    
    if(bigSize.size()-smallSize.size()==2){
      smallSize.add(bigSize.remove());
    }
    
    
  }
  
  public static int getMedian(PriorityQueue<Integer> smallHeaps,PriorityQueue<Integer> bigHeaps ){
    PriorityQueue<Integer> smallSize = smallHeaps.size()<bigHeaps.size()?smallHeaps:bigHeaps;
    PriorityQueue<Integer> bigSize = smallHeaps.size()<bigHeaps.size()?bigHeaps:smallHeaps;
    
    if(bigSize.size()==smallSize.size()){
      return (bigSize.peek()+smallSize.peek())/2;
    }
    else{
      return bigSize.peek();
    }
    
  }

}
