public class List{
	Node root;

	public List(Node r){
		root = r;
		root.next = null;
	}

	public boolean contains(Node n){
		Node current = root;
		while(current.next != null){
			if(current.payload.equals(n)){
				return true;
			}
			current = current.next;
		}
		return false;
	}

	public Node deleteFirst(){
		if(root != null){
			Node first = root;
			root = root.next;
			first.next = null;
			return first;
		}else{
			return null;
		}
	}

	public Node delete(Node n){
		Node current = root;
		Node prev= current;
		while (current != null) {
			if (current.payload.equals(n)) {
				prev.next = current.next;
				return current;
			}
			prev = current;
			current = current.next;
		}
		return null;
	}

	public Node min(){
		float min = root.payload.f;
		Node current = root;
		while(current.next != null){
			if(current.payload.f < min){
				min = current.payload.f;
			}
			current = current.next;
		}
		return current;
	}

	public int getSize(){
		int count = 1; 
		Node current = root;
		while(current.next != null){
			current = current.next;
			count++;
		}
		return count;
	}

	public void append(Node n){
		Node current = root;
		while(current.next != null){
			current = current.next;
		}
		current.next = n;
	}

	public Node find(Node n){
		Node current = root;
		while (current.next != null) {
			if (current.payload.equals(n)) {
				return current;
			}
			current = current.next;
		}
		return null;
	}

	public String toString(){
		String msg = "";
		Node current = root;
		msg += current.toString();
		while(current.next != null){
			current = current.next;
			msg += current.toString();
		}
		return msg;
	}
}
