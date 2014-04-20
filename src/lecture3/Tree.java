package lecture3;
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
    ArrayList<Node> children;
    Node(Item item) {
	this.item = item;
	this.children = new ArrayList<Node>();
    }
}

public class Tree {
    Node root;
    Tree() {
	root = null;
    }
    public static void main(String[] args) {

	Item item1 = new Item("Max", "111-1111-1111");
	Item item2 = new Item("Tim", "111-1111-1112");
	Item item3 = new Item("John", "111-1111-1113");
	Tree oak = new Tree();

	oak.root = new Node(item1);
	oak.root.children.add(new Node(item2));
	oak.root.children.get(0).children.add(new Node(item3));
	System.out.println(
           oak.root.children.get(0).children.get(0).item.key);
	System.out.println(
           oak.root.children.get(0).children.get(0).item.value);

	/*
	Storage storage = new Storage();
	storage.put(item1);
	storage.put(item2);
	storage.put(item3);
	System.out.println(storage.size());
	
	System.out.println(storage.get("John"));
	storage.delete("John");
	System.out.println(storage.get("John"));
	System.out.println(storage.has("Tim"));
	
	
	 int sum=0;

	 Scanner sc = new Scanner(System.in);
	 
	 while(sc.hasNextInt()) {
	     sum += sc.nextInt();
	     System.out.println(sum);
	 }
	*/
     }
}