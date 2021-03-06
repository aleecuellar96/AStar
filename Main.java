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

import java.util.*;

public class Main {

	public static List open_list;
	public static List closed_list;
	public static ArrayList<Cell> finalPath = new ArrayList<Cell>();

	public static Cell[][] board;
	public static int numWalls = 0;

	public static void main (String args[]){
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Size: ");
		int size = reader.nextInt();
		createBoard(size);
		Cell start = new Cell (0, 0);
		for(int i=0; i<board.length;i++){
			if(board[i][size-1].valid){
				start = new Cell(i,0);
				break;
			}
		}
		printBoard(board, start);
		
		Cell goal = new Cell (0, 0);
		for(int i=0; i<board.length;i++){
			if(board[i][size-1].valid){
				goal = new Cell(i,29);
				break;
			}
		}
		System.out.println("---------------------------------------");
		System.out.println("START: "+ start  + " GOAL : " + goal);
		System.out.println("--------------------------------------");

		if (aStar(start, goal)){
			System.out.println(":)");
		}else{
			System.out.println ("No solution");
		}
		printBoard(board, start);
		System.out.println(numWalls);
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
				//System.out.println(current);
				closed_list.append(current);
				finalPath = construct_path(current);
				construct_path(current);
				return true;
			}

			//System.out.println(current);

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
							//System.out.println (neighbor.parent);
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
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board.length; j++){
				int x = (Math.random() < 0.5)?0:1;
				if(numWalls<800){
					if(i==0 && j == 0){
						board[i][j].valid = true;
					}else{
						if(x == 1){
							board[i][j].valid = false;
							numWalls++;
						}else{
							board[i][j].valid = true;
						}
					}
				}else{
					board[i][j].valid = true;
				}
				/*if(i==0 && j == 0){
					board[i][j].valid = true;

				}else{
					if(x == 1){
						board[i][j].valid = false;
						numWalls++;
					}else{
						board[i][j].valid = true;
					}
				}*/
				
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