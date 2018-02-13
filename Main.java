/*
	Cinthya Daniela Lugo Novoa A01332942
	Alejandra Cuéllar González A01333324
*/

/*
	Formulate the problem:
	Initial state: A given Cell to start
	Actions: Moves left, right, top, down, and diagonal
	Transition: Select to move to the cell of lower cost
	Goal Test: Given cell to reach, going from A tu Goal
	Path Cost: the cost is given by f
*/

/*
	Characterize the environment: 
	Deterministic, Sequential, Static, Discrete, Known
*/

//hacer print tablero

import java.util.*;

public class Main {

	public static List open_list;
	public static List closed_list;
	public static ArrayList<Cell> finalPath = new ArrayList<Cell>();

	public static Cell[][] board;
	public static Cell[][] world;

	public static void main (String args[]){

		//using the template of class
		/*int [][] template = new int[][]{
		  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		  {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		  {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
		  {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
		  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		};

		worldFromTemplate (template, 10); 

		Cell start = new Cell (1, 0);
		Cell goal = new Cell (4, 9);

		if (aStar(start, goal)){
			System.out.println(":)");
		}else{
			System.out.println ("No solution");
		}

		printBoard(board, start);*/
		//For creating a random board of 50x50
		Cell start = new Cell (0, 0);
		
		createBoard(10);
		printBoard(world, start);
		System.out.println("-------------------");

		Scanner reader = new Scanner(System.in);
		System.out.println("Enter x goal: ");
		int x = reader.nextInt();

		System.out.println("Enter y goal: ");
		int y = reader.nextInt();

		Cell goal = new Cell (x, y);

		if (aStar(start, goal)){
			System.out.println(":)");
		}else{
			System.out.println ("No solution");
		}
		printBoard(world, start);
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
				finalPath = construct_path(current);
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
						}
					}
				}
			}
		}
		return false;
	}

	public static ArrayList<Cell> neighbors(Cell c){
		ArrayList<Cell> cells = new ArrayList<Cell>();
		for (int i = 0; i < world.length; i++){
			for (int j = 0; j < world.length; j++){
				Cell cell = world[i][j].copy();
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
		while (c.parent != null ){ //si path no contiene a c.parent
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
		world = new Cell[size][size];
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				world[i][j] = new Cell (i, j);
			}
		}
		for (int i = 0; i < world.length; i++){
			for (int j = 0; j < world.length; j++){
				int x = (Math.random() < 0.5)?0:1;
				if(i==0 && j == 0){
					world[i][j].valid = true;

				}else{
					if(x == 1){
					world[i][j].valid = false;
					}else{
						world[i][j].valid = true;
					}
				}
				
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

	public static void printBoard(Cell[][] board, Cell start){
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board.length; j++){
				Cell cell = board[i][j].copy();
				if(finalPath.contains(cell) && cell.valid){
					System.out.	print(" ->");
				}else if(cell.equals(start)){
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