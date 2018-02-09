public class List {

	Node root;

	public List () {
		
	}

	public Cell remove(Cell n){
		Node current = root;
		Node prev= current;
		while (current != null) {
			if (current.payload.equals(n)) {
				prev.next = current.next;
				return (Cell) current.payload;
			}
			prev = current;
			current = current.next;
		}
		return null;
	}

	public boolean contains (Cell value) {
		Node current = root;
		while (current.next != null) {
			if (current.payload.equals(value)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	public Cell find(Cell n){
		Node current = root;
		while (current.next != null) {
			if (current.payload.equals(n)) {
				return (Cell) current.payload;
			}
			current = current.next;
		}
		return null;
	}

	public void append(Cell n){
		Node newNode = new Node(n);
		if(root !=null){
			Node current = root;
			while(current.next != null){
				current = current.next;
			}
			current.next = newNode;
		}else{
			root = newNode;
		}
	}

	public int size() {
		int count = 1; 
		Node current = root;
		while(current.next != null){
			current = current.next;
			count++;
		}
		return count;
	}

	public Cell deleteFirst() {
		if (root != null) {
			Node first = root;
			root = root.next;
			return (Cell) first.payload;
		}
		return null;
	}

	public Cell min(){
		float min = root.payload.f;
		Node current = root;
		while(current.next != null){
			if(current.payload.f < min){
				min = current.payload.f;
			}
			current = current.next;
		}
		return (Cell) current.payload;
	}

	@Override
	public String toString() {
		String result = "";
		Node current = root;
		result += current.toString () + "\n";
		while (current.next != null) {
			current = current.next;
			result += current.toString () + "\n";
		}
		return result;
	}
}
