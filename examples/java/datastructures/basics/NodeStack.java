package com.revature.datastructures.basics;

/*
 * NodeStack is a custom class created to represent
 * a node-based data structure that follows a
 * Last-In-First-Out (LIFO) pattern.
 */
public class NodeStack {
	/*
	 * Reference to the last node added to the stack
	 */
	private Node top;
	
	/*
	 * The push method allows us to add a node to the 
	 * top of our stack
	 */
	public void push(int element) {
		Node node = new Node(element);
		push(node);
	}

	/*
	 *  C - top
	 *  |
	 *  \/
	 * 	B
	 * 	|
	 * 	\/
	 * 	A
	 */	
	// Overloaded push method
	public void push(Node node) {
		node.setNext(top);
		this.top = node;
	}
	
	/*
	 * The pop method allows us to remove the topmost 
	 * node from the stack, and return it
	 */
	public Node pop() {
		Node poppedNode = top;
		top = top.getNext();
		poppedNode.setNext(null);
		return poppedNode;
	}

	// The peek method is used to view the current 'top' node
	public Node peek() {
		return top;
	}
	
	public String toString() {
		String s = "";
		Node runner = top;
		while (runner != null) {
			s += runner + ",";
			runner = runner.getNext();
		}
		return s;
	}


	public static void main(String[] args) {
		NodeStack ns = new NodeStack();
		ns.push(1);
		ns.push(2);
		ns.push(3);

		System.out.println(ns); // Node: 3, Node: 2, Node 1

		System.out.println( ns.pop() ); // Node: 3

		System.out.println( ns.peek() ); // Node: 2
	}
}
