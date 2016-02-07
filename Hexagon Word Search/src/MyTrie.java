import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;

// MyTrie created as course work for Data Structures class. 
// The entire Trie is entirely my own work

public class MyTrie extends AbstractSet<String>{

	/**
	 * @param args
	 */
	
	boolean isWord;
	int size;
	MyTrie[] children;
	public static final int sizeAlp =  26;
	//=  new MyTrie[26];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<String> iterator() {
		ArrayList <String> words = this.toList();
		Iterator<String> i = words.iterator();
		return i;

	}

	@Override
	public int size() {
		return this.size;
	}
	
	public boolean containsEmptyString(){
		return isWord;
	}
	
	public boolean contains(String string){
		String x = string.toLowerCase();
		if(x.length() == 0){
			return isWord;
		}
		
		int position = x.charAt(0) - 'a' /*+ 1*/;
		
		if(this.children[position]  == null){
			return false;
		}else{
			return this.children[position].contains(x.substring(1,x.length()));
		}
	}
	
	public boolean containsPrefix(String prefix){
		String x = prefix.toLowerCase();
		if(x.length() == 0){
			return true;
		}
		int position = x.charAt(0) - 'a';
		if(this.children[position]  == null){
			return false;
		}else{
			return this.children[position].containsPrefix(x.substring(1,x.length()));
		}
	}
	
	public boolean add(String string){
		String x = string.toLowerCase();
		if(x.length() == 0){
			if(!this.isWord){
				this.isWord =  true;
				return true;
			}
			else{
				return false;
			}
		}
		
		for(int i =0; i < x.length(); i++){
			int pos = x.charAt(0) - 'a';
			if(pos<0 || pos>25){
				throw new IndexOutOfBoundsException ("String contains non-alphabetical data");
			}
		}
		
		
		int position = x.charAt(0) - 'a';
		if(this.children[position] == null){
			this.children[position] = new MyTrie();
		}
		
		if(children[position].add(x.substring(1, x.length()))){
			this.size++;
			return true;
		}else{
			return false;
		}
		
		
	}
	
	public boolean isEmpty(){
		if(this.size == 0){
			return true;
		}
		else return false;
	}
	
	public MyTrie(){
		this.isWord = false;
		this.children =   new MyTrie[sizeAlp];
		this.size = 0;
	}
	
	public ArrayList<String> toList(){
		return this.toListHelp("");
	}
	
	private ArrayList<String> toListHelp(String x){
		ArrayList <String> words = new ArrayList<String>();
		
		if(this.isWord == true){
			words.add(x);
		}
		
		for(int i = 0; i < sizeAlp; i++){
			
			if(children[i] != null){
				char charappend = (char) (i + 'a') ;
				String newArg = x + charappend;
				
				ArrayList <String> childWords = children[i].toListHelp(newArg);
				
				words.addAll(childWords);
			}
		}
		
	return words;	
	
	}
	
	public String toString(){
		ArrayList <String> words = this.toList();
		String x = words.toString();
		return x;
	}
	

}