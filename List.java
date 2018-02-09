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

	public void delete(int index){
		//necesito recorrer la lista hasta el index
		//necesito guardar el anterior al index
		//unir el anterio al siguiente de current
		int n = 0;
		Node current = root;
		Node prev= current;
		while(n < index){
			prev = current;
			current = current.next;
			n++;
		}
		prev.next = current.next;
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
