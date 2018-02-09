public class Cell implements Comparable<Cell>{
	public int x;
	public int y;
	public Cell parent;
	public float f; 
	public float g; // pure cost
	public float h; // heuristic cost

	public Cell(int _x, int _y){
		int x = _x;
		int y = _y;
		parent = null;
		f = 0;
		g = 0;
		h = 0;
	}

	public String toString(){
		return "[" + x + " , " + y + "] "+ " f: " + f + " g: " + g + " h: " + h;
	}

	public float heuristic(Cell goal) { //using h(n)²
		double h= Math.pow(this.x - goal.x, 2) + Math.pow(this.y - goal.y, 2);
		return (float)h;
	}

	public int compareTo(Cell other) {
		if (this.f > other.f) {
			return 1;
		} else if (this.f < other.f) {
			return -1;
		} else {
			return 0;
		}
	}

	public boolean equals(Object o) {
		Cell other = (Cell) o;
		return this.y == other.y && this.x == other.x;
	}

	public Cell copy() {
		Cell cell = new Cell (x, y);
		cell.f = f;
		cell.g = g;
		cell.h = h;
		return cell;
	}

}
