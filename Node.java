public class Node implements Comparable<Node> {

	public Node next;

	public Cell payload;

	public Node (Cell payload) {
		this.payload = payload;
	}

	@Override
	public boolean equals (Object o) {
		Node other = (Node) o;
		return this.payload == other.payload;
	}

	@Override
	public int compareTo (Node other) {
		return this.payload.compareTo (other.payload);
	}

	@Override
	public String toString(){
		return "N " + payload.toString() + "\n";
	}

}