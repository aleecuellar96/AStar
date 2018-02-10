/*
	Cinthya Daniela Lugo Novoa A01332942
	Alejandra Cuéllar González A01333324
*/

/*
	Formulate the problem
*/

/*
	Characterize the environment
*/

//hacer print tablero

import java.util.*;

public class Main {

	public static List open_list;
	public static List closed_list;

	public static Cell[][] board;

	public static void main (String args[]){

		int [][] template = new int[][]{ //quitar ya que este bien
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

		worldFromTemplate (template, 10); //quitar ya que este bien

		Cell start = new Cell (0, 0);
		Cell goal = new Cell (9, 5);

		if (aStar(start, goal)){
			System.out.println(":)");
		}else{
			System.out.println ("No solution");
		}

		printBoard();
	}

	public static boolean aStar(Cell start, Cell goal){
		open_list = new List();
		open_list.append(start);

		closed_list = new List();

		start.g = 0;
		start.f = start.g + start.heuristic(goal);

		while (open_list.getSize() != 0){
			Cell current = (Cell)open_list.min();

			if (current.equals(goal)){
				System.out.println(current);
				closed_list.append(current);
				construct_path(current);
				return true;
			}

			System.out.println(current);

			open_list.delete(current);
			closed_list.append(current);

			ArrayList<Cell> neighbors = neighbors(current);

			for (Cell neighbor : neighbors){
				if (!closed_list.contains (neighbor)){
					neighbor.f = neighbor.g + neighbor.heuristic (goal);
					if (!open_list.contains (neighbor)){
						open_list.append (neighbor);
					}else{
						Cell openNeighbor = (Cell) open_list.find (neighbor);
						if (neighbor.g < openNeighbor.g){
							openNeighbor.g = neighbor.g;
							openNeighbor.parent = neighbor.parent;
							System.out.println (neighbor.parent);
						}else{

						}
					}
				}
			}
		}
		return false;
	}

	public static ArrayList<Cell> neighbors(Cell c){
		ArrayList<Cell> cells = new ArrayList<Cell>();
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board.length; j++){
				Cell cell = board[i][j].copy();
				if (cell.valid){
					if (diagonal (c, i, j)){
						cell.g = c.g + 1.414f;
						cell.parent = c;
					cells.add (cell);
					}else if (notDiagonal (c, i, j)){
						cell.g = c.g + 1;
						cell.parent = c;
						cells.add (cell);
					}

				}
			}
		}
		return cells;
	}

	public static ArrayList<Cell> construct_path(Cell c){
		ArrayList<Cell> path = new ArrayList <Cell>();
		while (c.parent != null ){
			path.add (c);
			c = c.parent;
		}
		return path;
	}

	public static boolean notDiagonal(Cell cell, int x, int y){
		if((cell.x + 1 == x && cell.y == y) || (cell.x == x && cell.y + 1 ==y)
		|| (cell.x - 1 == x && cell.y ==y) || (cell.x == x && cell.y - 1 ==y)){
			return true;
		}else{
			return false;
		}
	}

	public static boolean diagonal(Cell cell, int x, int y){
		if((cell.x + 1 == x && cell.y + 1 == y) || (cell.x + 1 == x && cell.y - 1 ==y)
		|| (cell.x - 1 == x && cell.y + 1 ==y) || (cell.x - 1 == x && cell.y - 1 ==y)){
			return true;
		}else{
			return false;
		}
	}

	public static void createBoard(int size){
		board = new Cell[size][size];
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				board[i][j] = new Cell (i, j);
			}
		}
	}

	public static void worldFromTemplate (int[][] template, int size) {  //quitar ya que este bien
		board = new Cell[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Cell cell = new Cell (i, j);
				cell.valid = template[i][j] == 0;
				board[i][j] = cell;
			}
		}
	}

	public static void printBoard(){
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board.length; j++){
				Cell cell = board[i][j].copy();
				if(closed_list.contains(cell) && cell.valid){
					System.out.	print(" ->");
				}else if(!cell.valid){
					System.out.print(" | ");
				}else if(cell.valid){
					System.out.print(" * ");
				}
			}
			System.out.println();
		}
	}
}