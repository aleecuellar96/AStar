public class List{
	Node root;

	public List(Node r){
		root = r;
		root.next = null;
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
