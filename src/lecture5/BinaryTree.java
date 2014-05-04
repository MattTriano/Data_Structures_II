package lecture5;

import java.util.LinkedList;
import java.util.Queue;


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
	
	public int diameter(){	
	    int lheight=0, rheight=0, ldiameter=0, rdiameter=0;
	    
		if (this.left != null){
	    	lheight = this.left.height();
	    	ldiameter = this.left.diameter();
		}
		if (this.right  != null) {
			rheight = this.right.height();
	    	rdiameter = this.right.diameter();
		}
	    return Math.max(lheight + rheight + 1, Math.max(ldiameter, rdiameter));	    
}

	public int height(){
		int lheight=0, rheight=0;
	    if(this.left != null) lheight = this.left.height();
		if(this.right != null) rheight = this.right.height();
	    return 1 + Math.max(lheight, rheight);
	}
	
	public void inorder_traversal() {
		if(this.left!=null)
			this.left.inorder_traversal();
		System.out.println(this.item.value);
		if(this.right!=null)
			this.right.inorder_traversal();
	}
	
	@Override
	public String toString() {
		return this.item.key + " " + this.item.value;
	}
	
	public void byLevel(){
		Node root = this;
		Queue<Node> level  = new LinkedList<Node>();
		level.add(root);
		int rowWidth = 1;
		int newRowWidth = 0;
		while(!level.isEmpty()){
			Node node = level.poll();
			if(node.left!= null){
				level.add(node.left);
				newRowWidth +=1;
			}
			if(node.right!= null){
				level.add(node.right);
				newRowWidth +=1;
			}
			System.out.print(node + " ");
			rowWidth--;
			if(rowWidth == 0) {
				rowWidth = newRowWidth;
				newRowWidth = 0;
				System.out.println();
			}
		}
	}
	
	
}

public class BinaryTree {
	Node root;
	BinaryTree() {
		root = null;
	}
	
	public Double sum() {
		return this.root.sumBST();
	}
	
	public Double sumSquares() {
		return this.root.sumSquaresBST();
	}
	
	void store(String key, String value) {
		Item item = new Item(value,key);
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
		
		s.store("A", "20");
		s.store("B", "5");
		s.store("C", "90");
		s.store("D", "568");
		s.store("E", "230");
		s.store("F", "2730");
		s.store("G", "3230");
		s.store("H", "250");
		s.store("I", "223");
		s.store("J", "273");
		s.store("K", "263");
		s.store("L", "423");
		s.store("M", "123");
		s.store("N", "293");
		
		
		
		//Double sum = s.sum();
		//Double sqSum = s.root.sumSquaresBST();
		//int maxDisp = s.root.diameter();
		//System.out.println("The sum for the BST is " + sum);
//		System.out.println("The sum of squares for the BST is " + sqSum);
//		System.out.println("The diameter of this tree is " + maxDisp);
		s.root.inorder_traversal();
		s.root.byLevel();
	}
}

