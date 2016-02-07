import java.util.*;
/**
 * 
 */

/**
 * @author MoeezASyed
 *
 */
public class PhPerms {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		permutations(args[0]);
	}
	
	public static void permutations(String s){
        //Construct an array for all the values to be stored. This is defined
        String[] charV = new String[10];
        //This is going to be needed to be hardcoded
        charV[0] = "";
        charV[1] = "";
    	 charV[2] = "abc";
    	 charV[3] = "def";
    	 charV[4] = "ghi";
    	 charV[5] = "jkl";
    	 charV[6] = "mno";
    	 charV[7] = "pqrs";
    	 charV[8] = "tuv";
    	 charV[9] = "wxyz";
    	 
    	 ArrayList<String> output = new ArrayList<String>();
    	 ArrayList<Integer> strokes = new ArrayList<Integer>();
    	 //Create an ArrayList for the input
    	 for(int i = 0; i<s.length(); i++){
    	 	if(s.charAt(i) - '0' != 0 && s.charAt(i) - '0' != 1){
    	 	strokes.add(s.charAt(i) - '0');
    	 	}
    	 }
    	 // System.out.println(strokes.toString());
    	 
    	 //Add the first character to the output
    	 for(int i =0; i<charV[strokes.get(0)].length(); i++){
			String p = "" + charV[strokes.get(0)].charAt(i);
    	 	output.add(p);
    	 }
    	      //	 System.out.println(output.toString());

    	 
    	 
    	 int n =1;
    	 while(n < strokes.size()){
    	 	//Get the next char from the numpad
    	 	String list = charV[strokes.get(n)];
    	 	// System.out.println(list);
    	 	//Iterate through its characters
    	 	for(int j = 0; j<list.length(); j++){
    	 		for(int x = 0; x<output.size(); x++){
    	 			if(output.get(x).length() == n){
    	 				//System.out.println(output.toString());
    	 				String next = "" + output.get(x) + list.charAt(j);
    	 				output.add(next);
    	 				//System.out.println(next);
    	 			} 
    	 		}
    	 	}
    	 	//want to remove those
    	 	for(int x = 0; x<output.size(); x++){
    	 			if(output.get(x).length() == n){     	 				
    	 				output.remove(x);
    	 				x--;
    	 			} 
    	 	}
    	 
    	 n = n+1;	
    	 }
    	 
    	 System.out.println(output.toString());
    	      
    	 
    }

}
