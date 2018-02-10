public class Cell implements Comparable<Cell> {

	public int x;
	public int y;
	public Cell parent;
	public float f;
	public float g;
	public float h;

	public boolean valid;

	public Cell (int x, int y) {
		this.x = x;
		this.y = y;

		parent = null;

		f = 0;
		g = 0;
		h = 0;
	}

	public float heuristic(Cell other){ 
		float x= Math.abs(other.x-this.x);
		float yTemp = this.y + x;
		float y = Math.abs(other.y-yTemp);
		return (float) (x) + (y);
		//return (float) (Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
	}

	public Cell copy(){
		Cell cell = new Cell (x, y);
		cell.f = f;
		cell.g = g;
		cell.h = h;
		cell.valid = valid;

		return cell;
	}

	@Override
	public boolean equals(Object o){
		Cell other = (Cell) o;
		return this.x == other.x && this.y == other.y;
	}

	@Override
	public int compareTo(Cell other){
		if(this.f > other.f){
			return 1;
		}else if (this.f < other.f){
			return -1;
		}else{
			return 0;
		}
	}

	public String toString(){
		return "[" + x + " , " + y + "] "+ " f: " + f + " g: " + g + " h: " + h;
	}

}
