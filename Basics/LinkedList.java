public class LinkedList {
	//  main interface in the code
	public interface Linkable {
		public Linkable getNext();
		public void setNext(Linkable node);
	}

	Linkable head;

	public synchronized Linkable getHead() { return head; }

	public synchronized void insertAtHead(Linkable node) {
		node.setNext(head);
		head = node;
	}

	public synchronized void insertAtTail(Linkable node) {
		if(head == null) head = node;
		else {
			Linkable p, q;
			for(p = head; (q = p.getNext()) != null; p = q);
			p.setNext(node);
		}
	}

	public synchronized Linkable removeFromHead() {
		Linkable node = head;
		if(node != null) {
			head = node.getNext();
			node.setNext(null);
		}
		return node;
	}

	public synchronized Linkable removeFromTail() {
		if (head == null) return null;

		Linkable p = head, q = null, next = head.getNext();

		if (next == null) {
			head = null;
			return p;
		}

		while((next = p.getNext()) != null) {
			q = p;
			p = next;
		}

		q.setNext(null);
		return p; 
	}

	public synchronized void remove(Linkable node) {
		if(head == null) return;
		if(node.equals(head)) {
			head = head.getNext();
			return;
		}

		Linkable p = head, q = null;
		while((q = p.getNext()) != null) {
			if(node.equals(q)) {
				p.setNext(q.getNext());
				return;
			}
			p = q; // = p.getNext()
		}
	}

	public void show() {
		for(Linkable n = head; n != null; n = n.getNext())
			System.out.println(n);
	} 

	public static class Test {

		static class LinkableInteger implements Linkable {
			int i;
			Linkable next;

			public LinkableInteger(int i) { this.i = i; }

			public Linkable getNext() { return next; }
			public void setNext(Linkable node) { next = node; }
			public String toString() { return i + ""; }

			public boolean equals(Object o) { //  Why type Object?
				if(this == o) return true;
				if(!(o instanceof LinkableInteger)) return false;
				if( ((LinkableInteger)o).i == this.i) return true;
				return false;
			}
		}
			public static void main(String[] args) {
				LinkedList ll = new LinkedList();
				ll.insertAtHead(new LinkableInteger(1));
				ll.insertAtHead(new LinkableInteger(2));
				ll.insertAtHead(new LinkableInteger(3));
				ll.insertAtHead(new LinkableInteger(4));
				ll.insertAtTail(new LinkableInteger(5));
				ll.insertAtTail(new LinkableInteger(6));
				System.out.println(ll.removeFromHead());
				System.out.println(ll.removeFromTail());
				ll.remove(new LinkableInteger(2));
				ll.show();
			}
	}
} 