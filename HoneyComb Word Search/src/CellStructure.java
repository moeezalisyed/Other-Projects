
//CellStructure is an object to represent Cells
public class CellStructure {
	
	// Use axial co-ordinates to represent Honeycomb instead of a square grid
	// keekerdc.com/2011/03/hexagon-grids-coordinate-systems-and-distance-calculations/
	// The discussion above talks about using axial coordinates when each hexagon is visualised as pointy top. 
	// This code visualizes each hexagon in the honeycomb as flat topped.
	
	// Use a point class for the x-y coordinates of a cell.
	private Point coordinates ;
	
	// isUSed flag lated used for bfs searching of words
	private boolean isUsed = false;
	
	// To store the character in the cell
	private final char cellCharacter;

	//isUsed getter
	public boolean isUsed() {
		return isUsed;
	}

	//isUsed setter
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	
	// this links the cell to the honeycomb in which it is present
	private final CombStructure theHoneyComb;
	
	//Constructor
	public CellStructure (Point coordinates, char cellCharacter, CombStructure Hexagon){
		this.coordinates = coordinates;
		this.cellCharacter = cellCharacter;
		this.theHoneyComb = Hexagon;
	}
	
	
	public char getCellCharacter() {
		return cellCharacter;
	}
	
	public Point getCoordinates() {
		return coordinates;
	}
	
	//Get the co-ordinates of a neighbour of this cell in the direction specified. Returns a Point
	public Point getNeightbourCoordinates(Direction direction){
		 return Direction.getNeighbourPoint(this, direction);
	}
	
	//Get the neighbour of this cell in the direction specified. Returns a CellStructure
	public CellStructure getNeighbourCell(Direction direction){
		Point neighbourPoint =  Direction.getNeighbourPoint(this, direction);
		 return theHoneyComb.getCell(neighbourPoint);
	}
	
	
	
}
