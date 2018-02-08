public class Cell{
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
		return "[" + x + " , " + y + "] "+ " f: " + f + " g: " + g + "h: " + h;
	}

}
