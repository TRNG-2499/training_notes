package com.revature.datastructures.comparisons;

// Data Implements the Comparable Interface, and passes a Reference
// the this 'Data' class as the Generic type
// i.e. 'Data' will compare itself to other 'Data' objects
public class Data implements Comparable<Data> {
	private String name;
	private int id;
	private double weight;

	/*
	 * Implementation of compareTo method inherited from
	 * Comparable interface
	 */
	public int compareTo(Data other) {
		if (other.getId() > this.id) {
			return 1;
		} else if (other.getId() < this.id) {
			return -1;
		} else { // other.getId() == this.id
			return 0;
		}
	}
	
	public Data(String name, int id, double weight) {
		this.name = name;
		this.id = id;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return "[name="+name+", id="+ id + ", weight=" + weight + "]";
	}
}
