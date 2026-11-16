package com.revature.datastructures.iteration;

import java.util.Iterator;

/*
 * This class showcases the 'Iterable' and 'Iterator' interfaces
 *
 * A class which implements the 'Iterable' interface, must implement
 * the 'iterator()' method, which returns an Iterator<T> object.
 *
 * A class which implements the 'Iterator' interface must implement
 * the 'hasNext()' and 'next()' methods.
 */
public class GenericIterableNodeStack<T> implements Iterable<GenericNode<T>> {
	private GenericNode<T> top;

	public void push(T element) {
		GenericNode<T> node = new GenericNode<T>(element);
		push(node);
	}

	public void push(GenericNode<T> node) {
		node.setNext(top);
		this.top = (node);
	}

	public GenericNode<T> pop() {
		GenericNode<T> poppedNode = top;
		top = top.getNext();
		poppedNode.setNext(null);
		return poppedNode;
	}

	public GenericNode<T> peek() {
		return top;
	}

	// The 'Iterator' method should return an object which can be used to iterate through a collection
	@Override
	public Iterator<GenericNode<T>> iterator() {
		return new NodeStackIterator();
	}

	// We are defining a private 'NodeStackIterator' here for simplicity & organization
	private class NodeStackIterator implements Iterator<GenericNode<T>> {
		private GenericNode<T> cursor;

		// Constructor for the NodeStackIterator - starts the cursor at the 'top' of my node stack
		NodeStackIterator() {
			cursor = top;
		}

		// the 'hasNext()' implementation determines if there is more data in the collection
		@Override
		public boolean hasNext() {
			if (cursor != null)
				return true;

			return false;
		}

		// the 'next()' implementation return the current object and moves a cursor to the next item
		@Override
		public GenericNode<T> next() {
			GenericNode<T> ret = cursor;
			if (cursor != null)
				cursor = ret.getNext();

			return ret;
		}
	}

	public static void main(String[] args) {
		// Specify the Generic's Type when actually using the Class! (i.e. creating an object)
		GenericIterableNodeStack<Object> gins = new GenericIterableNodeStack<>();
		gins.push(new Object());
	}
}

// This is the 'GenericNode' class used as a 'Node' in the 'NodeStack' data structure above
class GenericNode<T> {
	private T data;
	private GenericNode<T> next;

	public GenericNode(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public GenericNode<T> getNext() {
		return next;
	}

	public void setNext(GenericNode<T> next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}
}
