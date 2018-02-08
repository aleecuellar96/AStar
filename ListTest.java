/*
	Liga del algoritmo: http://www.growingwiththeweb.com/2012/06/a-pathfinding-algorithm.html
*/

public class ListTest{
	public static void main(String [] args){

		Cell c1 = new Cell(0,0);
		Cell c2 = new Cell(0,1);
		Cell c3 = new Cell(1,0);
		Cell c4 = new Cell(1,1);

		Node n1 = new Node(null, c1);
		Node n2 = new Node(null, c2);
		Node n3 = new Node(null, c3);
		Node n4 = new Node(null, c4);

		List list = new List(n1);
		list.append(n2);
		list.append(n3);
		list.append(n4);
		System.out.println(list);
	}
}
