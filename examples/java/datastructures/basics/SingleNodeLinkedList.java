package com.revature.datastructures.basics;

/*
 * SingleNodeLinkedList is another custom node-based
 * data structure. This data structure holds data in a
 * first-in-first-out manner 
 */
public class SingleNodeLinkedList {
	Node head;

	/*
	 * the add method will insert new data to the END of the collection...
	 * 
	 * We achieve this be traversing through each node in our collection, until we reach the end!
	 */
	public boolean add(Node node) {
		/*
		 * 				  Runner
		 * 				  |
		 * head
		 * |
		 * A -> B -> C -> D 
		 */
		if (head == null) {
			head = node;
		} else {
			Node runner = head;
			while (runner.getNext() != null) {
				runner = runner.getNext();
			}
			runner.setNext(node);
		}
		return true;
	}

	public boolean add(int value) {
		Node newNode = new Node(value);
		return add(newNode);
	}

	public Integer remove() {
		if (head == null) {
			System.out.println("Empty List");
			return -1;
		} else {
			Node temp = head;
			head = head.getNext();
			temp.setNext(null);
			return temp.getData();
		}
	}

	public String toString() {
		String s = "";
		Node runner = head;
		while (runner != null) {
			s += runner + ",";
			runner = runner.getNext();
		}
		return s;
	}
}
