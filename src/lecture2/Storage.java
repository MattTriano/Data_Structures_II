package lecture2;

import java.util.*;
import java.util.Scanner;

class Item {
	String key;
	String value;
	Item(String key, String value) {
		this.key =  key;
		this.value = value;
	}
}

class Node {
	Item item;
	Node prev;
	Node next;
	Node(Item item, Node prev, Node next) {
		this.item = item;
		this.prev = prev;
		this.next = next;
	}
}

public class Storage {
	private Node start;
	private int nnodes;
	Storage() {
		this.start = null;
		this.nnodes = 0;
	}
	void put(Item item) {
		this.start = new Node(item,null,this.start);
		this.nnodes++;
	}
	Node search(String key) {
		Node p = start;
		while(p!=null) {
			if(p.item.key == key) 
				return p;
			p = p.next;
		}
		return null;
	}
	boolean has(String key) {
		return search(key)!=null;
	}
	String get(String key) {
		Node p = search(key);
		if(p!=null) 
			return p.item.value;
		return null;
	}
	boolean delete(String key) {
		Node p = search(key);
		if(p!=null) {
			Node prev = p.prev;
			if(prev!=null){
				prev.next = p.next;
			} else {
				start = p.next;
			}
			if(p.next!=null) p.next.prev = prev;		
			return true;
		}
		return false;
	}
	int size() {
		return this.nnodes;
	}

	public static void main(String[] args) {

		Item first = new Item("Michael", "12");
		Item second = new Item("Stanley", "4");
		Item third = new Item("Andrew", "17");
		Item fourth = new Item("Perry", "13");

		Storage numbers = new Storage();

		numbers.put(first);
		numbers.put(second);
		numbers.put(third);
		numbers.put(fourth);

		numbers.delete(numbers.start.item.key);
		System.out.println(numbers.start.item.key);

		/*
	 int sum=0;

	 Scanner sc = new Scanner(System.in);

	 while(sc.hasNextInt()) {
	     sum += sc.nextInt();
	     System.out.println(sum);
	 }
		 */
	}
}