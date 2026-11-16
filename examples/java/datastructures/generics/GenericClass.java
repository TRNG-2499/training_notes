package com.revature.datastructures.generics;

/*
 * When declaring Generics for a class, it is common practice to
 * use a single letter. Typical letters include:
 * K - key
 * T - Type
 * V - Value
 * U - Value (2nd)
 */
public class GenericClass<K, Type> {
	private K key;
	private Type value;
	
	public Type getValue() {
		return value;
	}	
	public void setValue(Type value) {
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}	
	public void setKey(K key) {
		this.key = key;
	}

	public static void main(String[] args) {
		GenericClass<Integer, String> gc = new GenericClass<Integer, String> ();
		gc.setKey(5);
		gc.setValue("Five");
	}
}

interface Example<T> {
	void toSomeGenericStuff(T name);
}
