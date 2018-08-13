package MapUse;

import java.util.ArrayList;

public class Map<K,V> {
	ArrayList<MapNode<K,V>> buckets;
	int size;
	int numBuckets;
	public Map(){
		numBuckets = 5;
		buckets = new ArrayList<>();
		for(int i =0;i<20;i++){
			buckets.add(null);
		}
	}
	
	public int getBucketIndex(K key){
		int hashCode = key.hashCode();
		return hashCode % numBuckets;
	}
	
	public void insert(K key, V value){
		int bucketIndex = getBucketIndex(key);
		MapNode<K,V> head = buckets.get(bucketIndex);
		while(head!=null){
			if(head.key.equals(key)){
				head.value = value;
				return;
			}
			
			head = head.next;
		}
		head = buckets.get(bucketIndex);
		MapNode<K,V> node = new MapNode<>(key, value);
		node.next = head;
		buckets.set(bucketIndex, node);
		size++;
		double loadFactor = (1.0*size)/numBuckets;
		if(loadFactor>0.7){
			rehash();
		}
		
	}
	public double loadFactor(){
		return (1.0*size)/numBuckets;
	}
	
	private void rehash() {
		// TODO Auto-generated method stub
		System.out.println("Rehashing: buckets "+ numBuckets + " size: "+size );
		ArrayList<MapNode<K, V>> temp = buckets;
		buckets = new ArrayList<>();
		for(int i =0;i<numBuckets*2;i++){
			buckets.add(null);
		}
		size= 0;
		numBuckets *= 2;
		for(int i = 0; i<temp.size();i++){
			MapNode<K, V> head = temp.get(i);
			while(head!=null){
				K key = head.key;
				V value = head.value;
				insert(key,value);
				head = head.next;
			}
		}
		
		
	}

	public V getValue(K key){
		int bucketIndex = getBucketIndex(key);
		MapNode<K,V> head = buckets.get(bucketIndex);
		while(head!=null){
			if(head.key.equals(key)){
				return head.value;
			}
		}
		return null;
	}
	
	public V removeKey(K key){
		int bucketIndex = getBucketIndex(key);
		MapNode<K,V> head = buckets.get(bucketIndex);
		MapNode<K,V> prev = null;
		while(head!=null){
			if(head.key.equals(key)){
				if(prev == null){
					buckets.set(bucketIndex,head.next);
				}
				else{
					prev.next = head.next;
				}
				size--;
				return head.value;
			}
			prev = head;
			head = head.next;
		}
		return null;
		
	}
	
	public int size(){
		return size;
	}
}
