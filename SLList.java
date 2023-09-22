public class SLList<Item> implements List61B<Item>{
	private class IntNode {
		public Item item;
		public IntNode next;

		public IntNode(Item i, IntNode n) {
			item = i;
			next = n;
		}
	}

	/* The first item (if it exists) is at sentinel.next. */
	private IntNode sentinel;
	private int size;

	/** Creates an empty timingtest.SLList. */
	public SLList() {
		sentinel = new IntNode(null, null);
		size = 0;
	}

	public SLList(Item x) {
		sentinel = new IntNode(null, null);
		sentinel.next = new IntNode(x, null);
		size = 1;
	}

	/** Overrides print method to print SLList efficiently */
	@Override
	public void print(){
		for (IntNode p = sentinel.next; p != null; p = p.next){
			System.out.print(p.item + " ");
		}
		System.out.println();
	}

	/** Adds x to the beginning of the list. */
	public void addFirst(Item x) {
		sentinel.next = new IntNode(x, sentinel.next);
		size = size + 1;
	}

	/** Adds x to the end of the list. */
	public void addLast(Item x) {
		size = size + 1;

		IntNode p = sentinel;

		/* Advance p to the end of the list. */
		while (p.next != null) {
			p = p.next;
		}

		p.next = new IntNode(x, null);
	}

	/** Returns the first item in the list. */
	public Item getFirst() {
		return sentinel.next.item;
	}

	/** returns last item in the list */
	public Item getLast() {
		IntNode p = sentinel;

		/* Advance p to the end of the list. */
		while (p.next != null) {
			p = p.next;
		}

		return p.item;
	}

	/** Removes the last item in the list
	 * and returns the removed item. */
	public Item removeLast(){
		Item x = getLast();
		IntNode point = sentinel;
		while (point.next.next != null){
			point = point.next;
		}
		point.next = null;

		size--;
		return x;
	}

	/** Gets the ith item in the list. */
	public Item get(int x){
		IntNode p = sentinel;
		if (x < size && x >= 0) {
			for (int i = 0; i < x + 1; i++) {
				p = p.next;
			}
			return p.item;
		}
		return null;
	}

	/** Returns the size of the list. */
	public int size() {
		return size;
	}

}
