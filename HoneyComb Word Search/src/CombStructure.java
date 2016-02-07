//CombStructure is an object to represent the honeycomb
public class CombStructure {
	// Number of layers
	private final int layers;
	
	// A 2D Array to store all the hexagonal cells in the honeycomb
	private final CellStructure[][] Hexagons;
	
	// A number used to setup the size of the 2D array
	private int arrayRange;
	
	// Constructor
	public CombStructure (int layers){
		this.layers = layers;
		 arrayRange = layers*2 -1;
		Hexagons = new CellStructure[arrayRange][arrayRange];
	}	
	
	// Getter for a cell in the honeycomb given the cell's co-ordinates
	public CellStructure getCell(Point coordinates){
		// >> Mapping of axial coordinates to indices in our 2D Array
		int arrayIndex1 = coordinates.getX() + layers - 1;
		int arrayIndex2 = coordinates.getY() + layers - 1;
		// >> Mapping of axial coordinates to indices in our 2D Array
		
		//Test to hanble index out of bounds exception
		if(arrayIndex1 >=arrayRange || arrayIndex2 >= arrayRange || arrayIndex1 < 0 || arrayIndex2 < 0 ){
			return null;
		}else{
		
		return Hexagons[arrayIndex1][arrayIndex2];
		}
		}
	
	// Setup a cell in the hexagon given the cell and its Point coordinates
	protected void setHexagonCell(Point coordinates, CellStructure currentCell){
		// >> Mapping of axial coordinates to indices in our 2D Array
		int arrayIndex1 = coordinates.getX() + layers - 1;
		int arrayIndex2 = coordinates.getY() + layers - 1;
		// >> Mapping of axial coordinates to indices in our 2D Array
		Hexagons[arrayIndex1][arrayIndex2] = currentCell;
	}
	
	// need to come back to change this so that we use an iterator
	public CellStructure[][] getHexagon(){
		return Hexagons;
	}
	
	// A tester method to see if the 2D Array was constructed correctly
	/*public void tester(){		
		for(int i = 0; i<layers*2 -1; i++){
			for(int j = 0; j<layers*2 -1; j++){
				if(Hexagons[i][j] != null){
					System.out.print("Cell: (X: " + Hexagons[i][j].getCoordinates().getX() + ", Y: " + Hexagons[i][j].getCoordinates().getY() + " char: " + Hexagons[i][j].getCellCharacter() + " )" );	
				}	
			}
			System.out.println();
		}
	}*/
	
			
			
}
