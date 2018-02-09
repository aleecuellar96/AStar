public class Node implements Comparable<Node>{
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

	public int compareTo(Node other) {
		return this.payload.compareTo (other.payload);
	}

	public boolean equals(Object o) {
		Node other = (Node) o;
		return this.payload == other.payload;
	}
}
