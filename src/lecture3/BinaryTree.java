package lecture3;
/*
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
	Node parent;
	Node left;
	Node right;
	Node(Item item, Node parent, Node left, Node right) {
		this.item = item;
		this.left = left;
		this.right = right;
	}
	public int countChildren() {
		int n = 0;
		if(this.left!=null) n+=1;
		if(this.right!=null) n+=1;
		return n;
	}
	public Node smallestKeyNode() {
		Node p = this;
		while(p.left!=null) p=p.left;
		return p;
	}
	public Node largestKeyNode() {
		Node p = this;
		while(p.right!=null) p=p.right;
		return p;
	}
	public String toString() {
		String s = "";
		if(this.left!=null) s=s+this.left.toString()+",";
		s = s+this.item.key+":"+this.item.value;
		if(this.right!=null) s=s+","+this.right.toString();
		return s;	
	}
	public String search(String key) { 
		if(this.item.key==key) {
			return this.item.value;
		} else if(this.item.key.compareTo(key)<0) {
			if(this.right==null) return null;
			return this.right.search(key);
		} else {
			if(this.left==null) return null;
			return this.left.search(key);
		}
	}
	boolean delete(String key) {
		if(this.item.key==key) {
			int n = countChildren();
			if(n==0) {
				if(parent.left==this)
					parent.left = null;
				else if(parent.right==this)
					parent.right = null;
			}
			else if(n==1) {
				if(parent.left==this && left!=null) { 
					// case 1
					left.parent = parent;
					parent.left = left;
				}
				else if(parent.left==this && right!=null) {
					// case 2
					right.parent = parent;
					parent.left = right;
				}
				else if(parent.right==this && left!=null) {
					// case 3
					left.parent = parent;
					parent.right = left;
				}
				else if(parent.right==this && right!=null) {
					// case 4
					right.parent = parent;
					parent.right = right;
				}
			}
			// delete this
			return true;
		} else if(this.item.key.compareTo(key)<0) {
			if(this.right==null) return false;
			return this.right.delete(key);
		} else {
			if(this.left==null) return false;
			return this.left.delete(key);
		}	
	}
	void store(Item item) {
		if(this.item.key==item.key) {
			this.item.value = item.value;
		} else if(this.item.key.compareTo(item.key)<0) {
			if(this.right==null)
				this.right = new Node(item,this,null,null);
			else
				this.right.store(item);
		} else {
			if(this.left==null)
				this.left = new Node(item,this,null,null);
			else
				this.left.store(item);
		}
	}
	String dumb_search(String key) {
		String value;
		if(this.item.key==key) 
			return this.item.value;
		if(this.left!=null) {
			value = this.left.dumb_search(key);
			if(value!=null) return value;
		}
		if(this.right!=null) {
			value = this.right.dumb_search(key);
			if(value!=null) return value;
		}
		return null;    
	}
}

public class BinaryTree {
	Node root;
	BinaryTree() {
		root = null;
	}
	void store(String key, String value) {
		Item item = new Item(key,value);
		if(root==null)
			root = new Node(item,null,null,null);
		else root.store(item);
	}
	boolean delete(String key) {
		if(root==null) return false;
		int n = root.countChildren();
		if(root.item.key==key)
			if(n==0)
				root=null;
			else if(n==1) {
				if(root.left!=null) {
					root.left=null;
					root = root.left;
				} else {
					root.right=null;
					root = root.right;
				}
			} else {
				Node p = this.right;
			}
	}

public String toString() {
	if(root==null) return "";
	return root.toString();
}
String search(String key) {
	if(root==null) return null;
	return root.search(key);
}
public static void main(String[] args) {

	BinaryTree s = new BinaryTree();
	s.store("Max", "111-1111-1111");
	s.store("Tim", "111-1111-1112");
	s.store("Alex", "111-1111-1115");
	System.out.println(s.toString());
	System.out.println(s.search("Tim"));

	/*
	Item item1 = new Item("Max", "111-1111-1111");
	Item item2 = new Item("Tim", "111-1111-1112");
	Item item3 = new Item("John", "111-1111-1113");
	BinaryTree oak = new BinaryTree();

	oak.root = new Node(item1,null,null);
	oak.root.left = new Node(item2,null,null);
	oak.root.left.right = new Node(item3,null,null);
	System.out.println(oak.root.left.right.item.key);
	System.out.println(oak.root.left.right.item.value);
	System.out.println(oak.root.dumb_search("Tim"));
	 */
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
