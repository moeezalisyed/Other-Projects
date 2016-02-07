
// An object that builds up the HoneyComb given an input file as specified
import java.io.BufferedReader;
import java.io.IOException;

public class BuildHoneyComb {

	public BuildHoneyComb() {
		// TODO Auto-generated constructor stub
	}
	
	//Method that sets up the HoneyComb structure and adds the central cell, hence the first layer, to the HoneyComb
	public static CombStructure buildHoneyComb(BufferedReader reader){
		int layers = Integer.parseInt(getNextLineHelper(reader));
		CombStructure theHoneyComb = new CombStructure(layers);
		String centralLayer = getNextLineHelper(reader);
		Point centralPointCoordinates = new Point(0,0);
		char centralChar = centralLayer.charAt(0);
		theHoneyComb.setHexagonCell(centralPointCoordinates, new CellStructure(centralPointCoordinates, centralChar, theHoneyComb ));
		
		// Construct all the outer layers
		for(int layerNumber = 2; layerNumber <= layers; layerNumber++){
			buildAdditionalLayersgHelper(theHoneyComb, layerNumber, getNextLineHelper(reader));
		}
		return theHoneyComb;
		
	}

	// Method that reads every additional layer and adds it to the honeycomb in a clockwise manner
	private static void buildAdditionalLayersgHelper(CombStructure theHoneyComb, int layerNumber, String currentLine) {
		// TODO Auto-generated method stub
		
		//Create the cell to the NORTH first
		Point northCellCoordinates = new Point(0, -1*(layerNumber-1));
		CellStructure northCell = new CellStructure(northCellCoordinates, currentLine.charAt(0), theHoneyComb );
		//Add the north cell
		theHoneyComb.setHexagonCell(northCellCoordinates, northCell);
		int charIndex = 1;
		// We add CLOCKWISE direction to the current cell to get to the Point coordinates of the next cell
		CellStructure currentCell = northCell;
		
		// We want to add every cell in the layer
		
		for(Direction direction : Direction.CLOCKWISE){
			// For every layer, a respective number of cells move in the same direction. 1 for layer 2, 2 for layer 3 etc ...
			for(int dirCells = 0; dirCells < layerNumber -1; dirCells++ ){
				// Get the next neighbour cell in a clockwise direction
				Point nextNeighbouCoordinates = currentCell.getNeightbourCoordinates(direction);
				// Add cell to the HoneyComb
				CellStructure nextCell = new CellStructure(nextNeighbouCoordinates, currentLine.charAt(charIndex), theHoneyComb);
				theHoneyComb.setHexagonCell(nextNeighbouCoordinates, nextCell);
				charIndex++;
				
				//Update our currentcell so we can keep moving clockwise in the layer
				currentCell = nextCell;
				if(charIndex >= currentLine.length()){
					return;
				}
			
			
			}
		}
		
	}
	
	// A helper method to get the next line in the buffered reader - handles Errors with try/catch exceptions
	private static String getNextLineHelper(BufferedReader reader) {
		// TODO Auto-generated method stub
		
		//Try-catch to check for IO issues
        try {
            return reader.readLine();
        } catch (IOException E) {
            HoneyCombWordSearch.exitProgramWithErrorMessage("There was an error in reading in parsing the input.");
            return null;
        }
	}

}
