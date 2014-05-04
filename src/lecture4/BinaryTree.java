package lecture4;

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
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	// countChildren() is called at the node level, so it will only tell you if a specific node has children
	public int countChildren() {
		int n = 0;
		if(this.left!=null) n+=1;
		if(this.right!=null) n+=1;
		return n;
	}
	// smallestKeyNode() is called at a node level and looks at the left child 
	// of a node (because BSTs are set up such that the left child will have 
	// a smaller key than the parent and the right node will have a larger key.
	// if there is no left child, then the current node is has the smallest key 
	// for the scope of any given call of this function
	public Node smallestKeyNode() {
		Node p = this;
		while(p.left!=null) p=p.left;
		return p;
	}
	// much like the above function, but with the larger key
	public Node largestKeyNode() {
		Node p = this;
		while(p.right!=null) p=p.right;
		return p;
	}
	// this is a recursive function that uses ternary operators.
	// each iteration will return 1 + the depth of the deepest subtree
	// for a given node.  It must work, but recursion still seems like 
	// it would return way too high of a value under this formulation
	public int depth() {
		int ldepth = (left!=null)?left.depth():0;
		int rdepth = (right!=null)?right.depth():0;
		return 1 + ((ldepth<rdepth)?rdepth:ldepth);
	}
	// if left child is not null, then it passes the left child 
	// back into this function.  If there are no left children to explore,
	// then the function checks if the right child is not null. 
	// If the right child is not null, then 
	public void inorder_traversal() {
		if(this.left!=null)
			this.left.inorder_traversal();
		System.out.println(this.item.key);
		if(this.right!=null)
			this.right.inorder_traversal();
	}
	 
	public void postorder_traversal() {
		if(this.left!=null)
			this.left.postorder_traversal();
		if(this.right!=null)
			this.right.postorder_traversal();
		System.out.println(this.item.key);
	}
	public String toString() {
		// in-order
		String s = "";
		if(this.left!=null) s=s+this.left.toString()+",";
		s = s+this.item.key+":"+this.item.value;
		if(this.right!=null) s=s+","+this.right.toString();
		return s;	
	}
	public String search(String key) { 
		// pre-order
		if(this.item.key==key) {
			return this.item.value;
		} else if(this.item.key.compareTo(key)>0) {
			if(this.left==null) return null;
			return this.left.search(key);
		} else {
			if(this.right==null) return null;
			return this.right.search(key);
		}
	}
	
	// looks at the key for the current node in the recursive call structure, compares with a specific key
	//
	boolean delete(String key) {
		if(this.item.key==key) {
			int n = countChildren();
			
			if(n==0) { 											// enters if the current node is a leaf
				if(parent.left==this)
					parent.left = null;
				else if(parent.right==this)
					parent.right = null;
			}
			else if(n==1) {										// enters if there is exactly one child
				if(parent.left==this && left!=null) { 
					// case 1: this node is the left child of a parent and has a left child 
					left.parent = parent;
					parent.left = left;
				}
				else if(parent.left==this && right!=null) {
					// case 2: this node is the left child of a parent and has a right child
					right.parent = parent;
					parent.left = right;
				}
				else if(parent.right==this && left!=null) {
					// case 3: this node is the right child of a parent and has a left child
					left.parent = parent;
					parent.right = left;
				}
				else if(parent.right==this && right!=null) {
					// case 4: this node is the right child of a parent and has a right child
					right.parent = parent;
					parent.right = right;
				}
			} else { // i think this is for nodes with both a left and a right child, but i don't understand the content of this block
				// ok, I get it a bit more after watching up to minute 29 of the lecture, so I'll try to write out my understanding
				// if there are two children for a node that is to be deleted, then the whole tree would need to be rearranged
				// however, math and logic tell us that the key that would replace the current key and maintain the defn of a BST
				// will be the smallest key on the right branch of the node to be deleted.  As the smallest key will 
				Node small = this.right.smallestKeyNode();
				this.item = small.item;
				small.delete(small.item.key);
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
		if(root.item.key==key) {
			if(n==0) {
				root=null;
				return true;
			} else if(n==1) {
				if(root.left!=null) {
					root.left.parent=null;
					root = root.left;
				} else {
					root.right.parent=null;
					root = root.right;
				}
				return true;
			} else {
				return root.delete(key);
			}
		} else {
			return root.delete(key);
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
	public int depth() {
		if(root==null) return 0;
		return root.depth();
	}
	public static void main(String[] args) {

		BinaryTree s = new BinaryTree();
		s.store("Max", "111-1111-1111");
		s.store("Alex", "111-1111-1115");
		s.store("Tim", "111-1111-1112");
		s.store("John", "111-1111-1117");
		s.store("Nate", "111-1111-1118");
		System.out.println("PREORDER");
		//s.root.preorder_traversal();
		System.out.println("INORDER");
		s.root.inorder_traversal();
		System.out.println("POSTORDER");
		s.root.postorder_traversal();
		System.out.println(s.depth());
		/*
	  System.out.println(s.toString());
	  System.out.println(s.search("Max"));
	  s.delete("Max");
	  System.out.println(s.search("Max"));
	  System.out.println(s.toString());
		 */
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
	}
}