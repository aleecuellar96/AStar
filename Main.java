import java.util.*;

public class Main {

	public static List open_list;
	public static List closed_list;

	public static Cell[][] board;

	public static void randomWorld (int size) {
		board = new Cell[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = new Cell (i, j);
			}
		}
	}

	public static void worldFromTemplate (int[][] template, int size) {
		board = new Cell[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Cell cell = new Cell (i, j);
				cell.valid = template[i][j] == 0;
				board[i][j] = cell;
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
		Cell goal = new Cell (2, 6);

		if (aStar (start, goal)) {
			System.out.println (":)");
		} else {
			System.out.println ("No solution");
		}
	}

	public static boolean aStar (Cell start, Cell goal) {
		open_list = new List ();
		open_list.append (start);

		closed_list = new List ();

		start.g = 0;
		start.f = start.g + start.heuristic (goal);

		while (open_list.size () != 0) {
			Cell current = (Cell) open_list.min();

			if (current.equals (goal)) {
				construct_path (current);
				return true;
			}

			System.out.println (current);

			open_list.remove (current);
			closed_list.append (current);

			ArrayList<Cell> neighbors = neighbors (current);

			for (Cell neighbor : neighbors) {
				if (!closed_list.contains (neighbor)) {
					neighbor.f = neighbor.g + neighbor.heuristic (goal);
					if (!open_list.contains (neighbor)) {
						open_list.append (neighbor);
					} else {
						Cell openNeighbor = (Cell) open_list.find (neighbor);
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
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				Cell cell = board[i][j].clone ();
				if (cell.valid) {
					if (isDiagonal (root, i, j)) {
						cell.g = root.g + 1.414f;
						cell.parent = root;
					cells.add (cell);
					} else if (isNormal (root, i, j)) {
						cell.g = root.g + 1;
						cell.parent = root;
						cells.add (cell);
					}

				}
			}
		}
		return cells;
	}

	public static ArrayList<Cell> construct_path (Cell cell) {
		ArrayList<Cell> path = new ArrayList <Cell> ();
		while (cell.parent != null ) {
			//System.out.println (cell);
			path.add (cell);
			cell = cell.parent;
		}
		return path;
	}
}