// An enum to specify direction
public enum Direction {
	// Each constant is given the shift coordinates we would need to add to a cell to get to its neighbour in the specified direction
	// Example: We add NORTH(0, -1) to the Point coordinates of a cell to get the Point coordinates of its neighbour to the NORTH
	NORTH(0, -1),
	 NORTHEAST(1, -1),
	 SOUTHEAST(1, 0),
	 SOUTH(0, 1),
	 SOUTHWEST(-1, 1),
	 NORTHWEST(-1, 0);
	
	public final int xShift;
	public final int yShift;
	
	
	Direction(int xShift, int yShift) {
		// TODO Auto-generated constructor stub
		this.xShift= xShift;
		this.yShift = yShift;
	}

	public static Point getNeighbourPoint(CellStructure cell, Direction direction) {
		// TODO Auto-generated method stub
		Point currentPoint = cell.getCoordinates();
		int xShifted = currentPoint.getX() + direction.xShift;
		int yShifted = currentPoint.getY() + direction.yShift;
		Point neighbourPoint = new Point(xShifted, yShifted);
		return neighbourPoint;
	}
	
	// CLOCKWISE Direction used later on to go clockwise in an array, starting from the top hexagon, when taking in the honeycomb input
	 public static Direction[] CLOCKWISE = new Direction[] { SOUTHEAST, SOUTH, SOUTHWEST, NORTHWEST, NORTH, NORTHEAST};
	 
	 // EXPLORE direction specifies the order in which neighbouring cells are explored in the bfs for word search
	 public static Direction[] EXPLORE = new Direction[] { 
			 // Neighbours are explored clockwise starting from the neighbour to the NORTH
			 NORTH,
			 NORTHEAST,
			 SOUTHEAST,
			 SOUTH,
			 SOUTHWEST,
			 NORTHWEST};
	 
}
