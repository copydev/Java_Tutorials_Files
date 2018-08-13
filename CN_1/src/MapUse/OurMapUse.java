package MapUse;

public class OurMapUse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String ,Integer> map = new Map<String, Integer>();
		for(int i = 0;i<20;i++){
			map.insert("abc"+i, i+1);
			
			System.out.println("i = "+ i+ " If = " + map.loadFactor());
		}
		
		map.removeKey("abc3");
		map.removeKey("abc7");
		
		for(int i =0;i<20;i++){
			System.out.println(map.getValue("abc"+i));
		}

	}

}
