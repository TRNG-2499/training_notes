package com.revature.datastructures.generics;

/*
 * The Following Class is an altered version of the SingleNodeLinkedList class
 * in the 'basics' package which declares a Generic type
 */
public class GenericSingleNodeLinkedList<T> {
	GenericNode<T> head;
	
	public boolean add(GenericNode<T> node) {
		if (head == null) {
			head = node;
		} else {
			GenericNode<T> runner = head;
			while (runner.getNext() != null) {
				runner = runner.getNext();
			}
			runner.setNext(node);
		}
		return true;
	}

	public boolean add(T value) {
		GenericNode<T> newNode = new GenericNode<T>(value);
		return add(newNode);
	}

	public T remove() {
		if (head == null) {
			throw new NullPointerException();
		} else {
			GenericNode<T> temp = head;
			head = head.getNext();
			temp.setNext(null);
			return temp.getData();
		}
	}

	public String toString() {
		String s = "";
		GenericNode<T>runner = head;
		while (runner != null) {
			s += runner + ",";
			runner = runner.getNext();
		}
		return s;
	}
}
