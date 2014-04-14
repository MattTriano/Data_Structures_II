package lecture2;

import java.util.*;
import java.util.Scanner;

class Item {
	String key;
	String value;
	Item(String key, String value) {
		this.key = key;
		this.value = value;
	}
}

public class Storage {
	private Item[] items;
	private int nitems;
	Storage(int N) {
		this.items = new Item[N];
		this.nitems = 0;
	}
	void put(Item item) {
		this.items[this.nitems] = item;
		this.nitems++;
	}
	
	int search(String key) {
		for(int k=0; k<this.size(); k++){
			if(this.items[k].key == key) {
				return k;
			}
		}
		return -1;
	}
	
	boolean has(String key) {
		return search(key) >= 0;
	}
	
	String get(String key) {
		int k = search(key);
		if(k>=0) 
			return this.items[k].value;
		return null;
	}
	
	boolean delete(String key) {
		int k = search(key);
		if(k>= 0 && k!=nitems-1)
			this.items[k] = this.items[nitems-1];
		nitems--;
		return (k>=0);
	}
	
	int size() {
		return this.nitems;
	}
	
	public static void main(String[] args) {
		Item item1 = new Item("Max", "313-867-5309");
		Item item2 = new Item("Tim", "111-111-1111");
		Item item3 = new Item("Jim", "222-222-2222");
		Storage storage = new Storage(1000);
		storage.put(item1);
		storage.put(item2);
		storage.put(item3);
		System.out.println(storage.size());
		System.out.println(storage.get("Tim"));
		storage.delete("Tim");
		System.out.println(storage.get("Tim"));
		System.out.println(storage.get("Alex"));
		System.out.println(storage.has("Tim"));
		
		/*int sum = 0;
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNextInt()) {
			sum += sc.nextInt();
			System.out.println(sum);
		}
		*/
	}
}
