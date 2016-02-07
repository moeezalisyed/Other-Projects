import java.util.*;
public class InputGen {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int layer = Integer.parseInt(args[0]);
		System.out.println(layer);
		System.out.println(
				(char)((int)'A'+Math.random()*((int)'Z'-(int)'A'+1)));
		
		for(int i =1; i< layer; i++){
			for(int j = 0; j< i*6; j++){
				System.out.print(
						(char)((int)'A'+Math.random()*((int)'Z'-(int)'A'+1)));
			}
			System.out.println();
		}
	}

}
