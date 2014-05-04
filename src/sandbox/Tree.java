package sandbox;


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
        Item hello = new Item("HELLO", null);
        Item bye = new Item("BYE", null);
	Item night = new Item("NIGHT", "MORNING");

        Tree a1 = new Tree();
      	
        a1.root = new Node(hello);
        a1.root.children.add(new Node(bye)); 	 
        a1.root.children.get(0).children.add(new Node(night));
        System.out.println(a1.root.children.toString());
    }
}

