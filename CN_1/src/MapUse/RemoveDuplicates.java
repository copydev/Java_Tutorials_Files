package MapUse;

import java.util.ArrayList;
import java.util.HashMap;

public class RemoveDuplicates {
	
	public static ArrayList<Integer> removeDuplicates(int a[]){
		ArrayList<Integer> output = new ArrayList<>();
		HashMap<Integer,Boolean> seen =new HashMap<>();
		for(int i =0;i<a.length;i++){
			if(seen.containsKey(a[i])){
				continue;
			}
			output.add(a[i]);
			seen.put(a[i], true);
		}
		return output;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[] = {1 ,2 ,3,5,3,2,1,100,100,233};
		ArrayList<Integer> list = removeDuplicates(a);
		for(int str: list){
			System.out.println(str);
		}

	}

}
