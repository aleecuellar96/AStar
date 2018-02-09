import java.util.*;

public class Main {

	public static int level[][];

	public static int normalCost = 1;
	public static float diagonalCost = 1.414f;

	public static List openList;
	public static List closedList;

	public static Cell[][] world;

	public static void randomWorld (int size) {
		world = new Cell[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				world[i][j] = new Cell (i, j);
			}
		}
	}

	public static void worldFromTemplate (int[][] template, int size) {
		world = new Cell[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Cell cell = new Cell (i, j);
				cell.valid = template[i][j] == 0;
				world[i][j] = cell;
			}
		}
	}

	public static void main (String args[]) {

		int [][] template = new int[][]{
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 1, 0, 1, 1, 1, 1, 1, 1, 0},
			{0, 1, 0, 1, 1, 1, 0, 0, 1, 0},
			{0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 1, 1, 1, 0},
			{0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
			{0, 1, 0, 1, 1, 1, 0, 0, 1, 0},
			{0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		};

		worldFromTemplate (template, 10);

		Cell start = new Cell (0, 0);
		Cell goal = new Cell (0, 6);

		boolean possible = aStar (start, goal);

		if (possible) {
			System.out.println ("There's a solution!");
		} else {
			System.out.println ("No solution");
		}
	}

	public static boolean aStar (Cell start, Cell goal) {
		openList = new List ();
		openList.append (start);

		closedList = new List ();

		start.g = 0;
		start.f = start.g + start.heuristic (goal);

		while (openList.size () != 0) {
			Cell current = (Cell) openList.min();

			if (current.equals (goal)) {
				constructPath (current);
				return true;
			}

			System.out.println (current);

			openList.remove (current);
			closedList.append (current);

			ArrayList<Cell> neighbors = neighbors (current);

			for (Cell neighbor : neighbors) {
				if (!closedList.contains (neighbor)) {
					neighbor.f = neighbor.g + neighbor.heuristic (goal);
					if (!openList.contains (neighbor)) {
						openList.append (neighbor);
					} else {
						Cell openNeighbor = (Cell) openList.find (neighbor);
						if (neighbor.g < openNeighbor.g) {
							openNeighbor.g = neighbor.g;
							openNeighbor.parent = neighbor.parent;
							System.out.println (neighbor.parent);
						}
					}
				}
			}
		}
		return false;
	}

	private static boolean isDiagonal (Cell cell, int x, int y) {
		return (cell.x + 1 == x && cell.y + 1 == y)
				|| (cell.x + 1 == x && cell.y - 1 ==y)
				|| (cell.x - 1 == x && cell.y + 1 ==y)
				|| (cell.x - 1 == x && cell.y - 1 ==y);
	}

	private static boolean isNormal (Cell cell, int x, int y) {
		return (cell.x + 1 == x && cell.y == y)
				|| (cell.x == x && cell.y + 1 ==y)
				|| (cell.x - 1 == x && cell.y ==y)
				|| (cell.x == x && cell.y - 1 ==y);
	}

	public static ArrayList<Cell> neighbors (Cell root) {
		ArrayList<Cell> cells = new ArrayList<Cell> ();
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world.length; j++) {
				Cell cell = world[i][j].clone ();
				if (cell.valid) {
					if (isDiagonal (root, i, j)) {
						cell.g = root.g + diagonalCost;
						cell.parent = root;
					cells.add (cell);
					} else if (isNormal (root, i, j)) {
						cell.g = root.g + normalCost;
						cell.parent = root;
						cells.add (cell);
					}

				}
			}
		}
		return cells;
	}

	public static ArrayList<Cell> constructPath (Cell cell) {
		ArrayList<Cell> path = new ArrayList <Cell> ();
		while (cell.parent != null ) {
			//System.out.println (cell);
			path.add (cell);
			cell = cell.parent;
		}
		return path;
	}
}