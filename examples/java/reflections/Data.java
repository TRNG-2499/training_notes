package com.revature.reflections;

public class Data extends Parent {
	private long id;
	String name;
	protected boolean isAvailable;
	public int luckyNumber; 
	
	static int count;
	
	public Data () { }

	public Data(long id, String name, boolean isAvailable, int luckyNumber) {
		super();
		this.id = id;
		this.name = name;
		this.isAvailable = isAvailable;
		this.luckyNumber = luckyNumber;
	}
	
	private Data(long id) {
		super();
		System.out.println("Private constructor was invoked");
		this.id = id;
	}
	
	Data(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getluckyNumber() {
		return luckyNumber;
	}

	public void setluckyNumber(int luckyNumber) {
		this.luckyNumber = luckyNumber;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Data.count = count;
	}

	private int add(int a, int b) { return a + b; }

}
