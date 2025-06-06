public class LinkedList<T> {

	private Node<T> head;
	private Node<T> current;

	public LinkedList() {
		head = current = null;
	}

	public boolean full() {
		return false;
	}

	public boolean last() {
		return current.next == null;
	}

	public void findFirst() {
		current = head;
	}

	public boolean empty() {
		return head == null;
	}

	public void findNext() {
		current = current.next;
	}

	public void update(T e) {
		current.data = e;
	}

	public T retrieve() {
		return current.data;
	}

	public void insert(T e) {
		Node<T> p = new Node<T>(e);
		if (head == null)
			head = current = p;
		else {
			Node<T> tmp = current.next;

			current.next = p;

			p.next = tmp;

			current = p;
		}
	}

	public void remove () {
		if (current == head) {
			head = head.next;
		}
		else {
			Node<T> tmp = head;

			while (tmp.next != current)
				tmp = tmp.next;

			tmp.next = current.next;
		}

		if (current.next == null)
			current = head;
		else
			current = current.next;
	}


	public int length() {
		int count = 0;
		Node<T> tmp = head;
		while (tmp != null) {
			count++;
			tmp = tmp.next;
		}
		return count;
	}

// ==Additional Methods==
	public void displayList() {
		Node<T> tmp = head;
		if (empty())
			return;
		while (tmp != null) {
			System.out.println(tmp.data);
			tmp = tmp.next;
		}
	}

}
