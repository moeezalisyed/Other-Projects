import java.io.*;
import java.util.*;
// Class that contains the main method and searches for the words
// Takes two arguments: the HoneyComb file and the dictionary file
public class HoneyCombWordSearch {
	
	// the HoneyComb is saved as a CombStrucutre
	private static CombStructure theHoneyComb;
	
	// We save the dictionary as a Trie
	private static MyTrie dictionary;
	private static int dictSize = 0;
	private static int outputSetSize = 0;
	
	// Output set to make sure output does not contain duplicates and is in alphabetical order
	private static Set<String> outputSet = new TreeSet<String>();

	
	//Main method
	public static void main(String [] args){
		// A strict condition of 2 arguments in order to work
		if (args.length < 2) {
            exitProgramWithErrorMessage("Incorrect Number of Arguments: This program takes in exactly two arguments.");
        }
		
		// Generate HoneyComb
		theHoneyComb = getHoneyComb(args[0]);
		
		// Build a dictionary - a trie to represent our dictionary
		dictionary = buildDictionary(args[1]);
		
		
		// Timer used for Testing
		//long startTime = System.currentTimeMillis();
		
		
		// Conduct bfs on the HoneyComb to find words
		breadthFirstSearch();
		
		// Timer Stop for Testing
		//long stopTime = System.currentTimeMillis();

		//Print out all the found words
		printOutput();
		
		// Timer Check for Testing
		//System.out.println("Elapsed time was " + (stopTime - startTime) + " miliseconds.");
	}
	
//Helper method to build the dictionary as a Trie
public static MyTrie buildDictionary(String dictInputFile){
		BufferedReader dictReader = null;
		// Try Catch Exception to check for file issues
		try {
			dictReader = new BufferedReader(new FileReader(dictInputFile));
        } catch (FileNotFoundException E) {
            exitProgramWithErrorMessage("The File: " + dictInputFile + " was not found. Please try again.");
        }
		// Setting up the trie
		MyTrie dictionary = new MyTrie();
		String nextWord = getNextLinehelper(dictReader);
		// Adding all the words to the trie
		while(nextWord != null){
			dictionary.add(nextWord);
			dictSize++;
			nextWord = getNextLinehelper(dictReader);
		}
		
		return dictionary;
		}

// Helper method to get the next line in the input file
private static String getNextLinehelper(BufferedReader reader) {
	// TODO Auto-generated method stub
	
	// Try-Catch to check for IO issues
    try {
        return reader.readLine();
    } catch (IOException E) {
        HoneyCombWordSearch.exitProgramWithErrorMessage("There was an error in reading in parsing the input.");
        return null;
    }
}
	
	//breadth-first search
	public static void breadthFirstSearch(){
		CellStructure[][] dhex = theHoneyComb.getHexagon();
		// Go over all the cells of the HoneyComb using all of them as possible startings of a word
		for(int i = 0; i<dhex.length; i++){
			for(int j = 0; j<dhex.length; j++){
				if(dhex[i][j] != null){
					CellStructure currentCell = dhex[i][j];
					// Setting the explored flag to true
					currentCell.setUsed(true);
					//In the unlikely case that a single character is also a word, for instance, a!
					if(dictionary.contains((String)Character.toString(currentCell.getCellCharacter()))){
						outputSet.add(Character.toString(currentCell.getCellCharacter()));
						outputSetSize++;
						if(outputSetSize >= dictSize){
							return;
						}
					}
					
					// if there is no word in the trie which has this character as a prefix, then we move onto the next character
					if(!dictionary.containsPrefix(Character.toString(currentCell.getCellCharacter()))){
						currentCell.setUsed(false);
						continue;
						//Do Nothing and move onto the next cell
					} else{
						//Need to explore neighbours 
						//Need to mark this cell
						currentCell.setUsed(true);
						
						// Recursive build up the prefix and keep searching for a word as long as the prefix is valid.
						prefixBFS(Character.toString(currentCell.getCellCharacter()),  currentCell);
						
						// Before moving onto the next cell, we mark the explored flag as false again as we begin to search for a different word
						currentCell.setUsed(false);
					}
				}
			}
		}
	}
	
	
	// Recursive method that searches for words by building up the prefix
	public static void prefixBFS(String prefix, CellStructure currentCell){
		/* Error check to make sure cell is not null. Is redundant because we already do a check before. Removed for Optimization
		if(currentCell == null){
			return;
		}*/
		
		// If the current prefix is a word, we add it to the output and keep looking for longer words with the same prefix
		if(dictionary.contains(prefix)){
			outputSet.add(prefix);
			outputSetSize++;
			if(outputSetSize >= dictSize){
				return;
			}
		}
		
		// If this is not a valid prefix, we exit and unmark the cells
		if(!dictionary.containsPrefix(prefix)){
			// We do not need to explore any more neighbours
			currentCell.setUsed(false);
			return;
		} else{
			// Now we need to explore the neighbours and check to see if they are contained as words by buuilding up the prefix
			
			// Find neighbours in the EXPLORE direction
			for(Direction direction : Direction.EXPLORE){
				CellStructure nextNeighbour = currentCell.getNeighbourCell(direction);
				// Only use neighbours that are not null and have not been explore before
				if(nextNeighbour != null && nextNeighbour.isUsed() == false){
					nextNeighbour.setUsed(true);
					
					prefix = prefix + Character.toString(nextNeighbour.getCellCharacter());
					
					prefixBFS(prefix, nextNeighbour);
					
					// Backtrack and delete the last character from the prefix and also set the explored flag to false
					prefix = prefix.substring(0, prefix.length() -1);
					nextNeighbour.setUsed(false);
				}
				
				
			}
		}
		
	}
	
	// Method to print out the outputSet
	public static void printOutput(){
		Iterator iter = outputSet.iterator();
		while (iter.hasNext()) {
		    System.out.println(iter.next());
		}
	}
	
	// Helper method to build up the honeyComb as a CombStructure
	public static CombStructure getHoneyComb(String combInputFile){
		
		BufferedReader combReader = null;
		
		// Use a try-catch to check for file validity
		try {
			combReader = new BufferedReader(new FileReader(combInputFile));
        } catch (FileNotFoundException E) {
            exitProgramWithErrorMessage("The File: " + combInputFile + " was not found. Please try again.");
        }
		
		return BuildHoneyComb.buildHoneyComb(combReader);
		}

	 
    public static void exitProgramWithErrorMessage(String error) {
        // Error Message is printed
    	System.err.println("ERROR: "+ error);
        // non-zero program exit
        System.exit(1);
    }
    
  
}
