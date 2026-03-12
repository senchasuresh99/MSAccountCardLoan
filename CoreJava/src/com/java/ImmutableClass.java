package com.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ImmutableClass {
	private int id;
	private String name;
	private final List<String> address;

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ImmutableClass [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

	public String getName() {
		return name;
	}

	public List<String> getAddress() {
		return address;
	}

	public ImmutableClass(int id, String name, List<String> address) {
		this.id = id;
		this.name = name;
		this.address = Collections.unmodifiableList(address);
	}
	public static void main(String args[]) {
		ImmutableClass addr = new ImmutableClass(1, "John Doe", Arrays.asList("Street 1", "City", "Country"));
        System.out.println(addr);
        addr.getAddress().add("New Street");

	}
}
