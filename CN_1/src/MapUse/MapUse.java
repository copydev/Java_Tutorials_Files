package MapUse;

import java.util.HashMap;
import java.util.Set;

public class MapUse {
	public static int maxFrequencyNumber(int[] arr){
		/* Your class should be named Solution
		 * Don't write main().
		 * Don't read input, it is passed as function argument.
		 * Return output and don't print it.
	 	 * Taking input and printing output is handled automatically.
		*/
      int max= 0;
      int num = 0;
      HashMap<Integer,Integer> map = new HashMap<>();
      for(int i = 0;i<arr.length;i++){
        if(map.containsKey(arr[i])){
          int times = map.get(arr[i])+1;
          map.put(arr[i],times);
          if(map.get(arr[i])>max){
            max = map.get(arr[i]);
            num = arr[i];
          }
        }
        else{
          map.put(arr[i],1);
        }
      }
      return num;
		
	}

	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<>();
		// insert

		map.put("abc", 1);
		map.put("def", 2);
		
		System.out.println("Size: " + map.size());

		if (map.containsKey("abc")) {
			System.out.println("Has abc");
		}
		if (map.containsKey("abc1")) {
			System.out.println("Has abc1");
		}

		if (map.containsKey("abc1")) {
			int v = map.get("abc1");
			System.out.println(v);
		}
		
		if(map.containsValue(2)){
			System.out.println("Map has value 2"); // Expensive Function
		}
		
		int s = map.remove("abc");
		System.out.println(s);
		
		Set <String> keys = map.keySet();
		for(String str:keys){
			System.out.println(str);
		}
		//Similarly Value Set
		
		int arr[] = {1,1,2,3,4,5,1,2,3,1,5,2,3,4,1,8};
		System.out.println(maxFrequencyNumber(arr)+"maxFreq");
	}

}
