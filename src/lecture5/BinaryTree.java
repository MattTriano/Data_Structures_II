package lecture5;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


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
	public int depth() {
		int ldepth = (left!=null)?left.depth():0;
		int rdepth = (right!=null)?right.depth():0;
		return 1 + ((ldepth<rdepth)?rdepth:ldepth);
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
	public Double sumBST() {
		Node n = this;
		Double _sum = 0.0;
		if(n.left!=null) 
			_sum += n.left.sumBST();
		if(n.right!=null)
			_sum += n.right.sumBST();
		_sum += Double.parseDouble(n.item.value);
		return _sum;
	}
	
	public Double sumSquaresBST() {
		Node n = this;
		Double _sum = 0.0;
		if(n.left!=null) 
			_sum += n.left.sumSquaresBST();
		if(n.right!=null)
			_sum += n.right.sumSquaresBST();
		_sum += Math.pow(Double.parseDouble(n.item.value),2);		
		return _sum;
	}
	
	public void inorder_traversal() {
		if(this.left!=null)
			this.left.inorder_traversal();
		System.out.println(this.item.key);
		if(this.right!=null)
			this.right.inorder_traversal();
	}
}

public class BinaryTree {
	Node root;
	BinaryTree() {
		root = null;
	}
	
	public static BinaryTree fromMap(Map<String, String> map) {
		BinaryTree bt = new BinaryTree();
		for(Entry<String, String> entry : map.entrySet()) {
			bt.store(entry.getKey(), entry.getValue());
		}
		
		return bt;
	}
	
	public Double sum() {
		return this.root.sumBST();
	}
	
	public Double sumSquares() {
		return this.root.sumSquaresBST();
	}
	
	void store(String key, String value) {
		Item item = new Item(key,value);
		if(root==null)
			root = new Node(item,null,null,null);
		else root.store(item);
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
		Map<String, String> mp = new HashMap<String, String>();
		
		mp.put("x", "0.0");
		mp.put("y", "3.0");
		mp.put("z", "2.0");
		mp.put("w", "1.0");
		BinaryTree mpt = BinaryTree.fromMap(mp);
		System.out.println("The sum for the mpt BST is " + mpt.sum());
		
		
		s.store("Max", "20");
		s.store("Alex", "5");
		s.store("Tim", "90");
		s.store("John", "568");
		s.store("Nate", "230");
		Double sum = s.sum();
		Double sqSum = s.root.sumSquaresBST();
		System.out.println("The sum for the BST is " + sum);
		System.out.println("The sum of squares for the BST is " + sqSum);
	}
}

