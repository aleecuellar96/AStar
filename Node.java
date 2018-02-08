public class Node{
	public Node next;
	public Cell payload;

	public Node(Node n, Cell p){
		next = n;
		payload = p;
	}

	public Node(){
		next = null;
		payload = null;
	}

	public String toString(){
		return "N " + payload.toString() + "\n";
	}
}
