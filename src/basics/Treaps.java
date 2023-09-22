package basics;

import java.util.Random;
import java.util.Stack;

public class Treaps<E extends Comparable<E>> {
	public static class Node<E>{
		//data fields
		private E data;       // key for the search
		private int priority; // random heap priority
		private Node<E> left = null;
		private Node<E> right = null;
		
		//constructors
		public Node(E data, int priority) {
			super();
			if(data == null) {
				throw new IllegalStateException("Node Constructing: Given data is null");
			}
			else {
			this.priority = priority;
			this.data = data;
			
			
			}
		}
		//methods
		//"rotates" the treap according to the given example
		public Node<E> rotateRight(){
			Node<E> a = this.left;
			Node<E> b = this.right;
			a.right = this;
			this.left = b;
			return a;
		}
		//"rotates" the treap according to the given example
		 public Node<E> rotateLeft(){
			Node<E> a = this.right;
			Node<E> b = this.left;
			a.left = this;
			this.right = b;
			return a;
		}
		 
		 public String toString() {
			 StringBuilder s = new StringBuilder();
			 s.append("[");
			 s.append(data);
			 s.append(",");
			 s.append(priority);
			 s.append("]");
			 return s.toString();
		 }
		
	}
	
	//data fields
	private Random priorityGenerator;
	private Node<E> root;
	
	//constructors
	public Treaps() {
		root = null;
		this.priorityGenerator = new Random();
	}
	//initializes the generator with the given seed
	
	public Treaps(long seed) {
		root = null;
		this.priorityGenerator = new Random(seed);
	}
	

	//Creates a new node with a random priority if the current Treap is empty, otherwise it gives it a priority and uses the other add method
	public boolean add(E key) {
		if(this.root == null) {
			root = new Node<E>(key,priorityGenerator.nextInt());
			return true;
		}
		else {
			return add(key, priorityGenerator.nextInt()); //this method checks for null key value
		}
	}
	
	
	//Creates a new node if the current treap is empty, other wise it uses a stack
	
	public boolean add(E key, int priority) { 
		if(this.root == null) {
			root = new Node<E>(key, priority);
			return true;
			
		}
		if(key == null) {
			throw new IllegalStateException("add: key given was null");
		}
		
		Node<E> current = root;
		Node<E> w = new Node<E>(key, priority); //node being added
		Stack<Node<E>> temp = new Stack<Node<E>>();
		while(null != current) {
			temp.push(current);
			
			if(0>key.compareTo(current.data)) { //if the key is smaller in comparison to the the root data, then we focus on the left branch because of Treap properties
				current = current.left;
			}
			else {
				current = current.right;
			}
		}
		if (key.compareTo(temp.peek().data)>0) {
			temp.peek().right = w;
		}
		else if(key.compareTo(temp.peek().data) < 0) {
			temp.peek().left = w;
		}
		else if(key.compareTo(temp.peek().data) == 0) {  // if a key already exists.
			return false;
		}

		return true;}
	
	
		public Node<E> deleteHelp(E key, Node<E> current) {
			if(key.compareTo(current.data)<0) { //if key is less than data
				current.left = deleteHelp(key, current.right);
			}
			else if(key.compareTo(current.data) >0) {
				current.right = deleteHelp(key,current.right);
			}
			
			else {
				if(current.left == null && current.right == null) {
					return null;
				}
				else if(current.left != null && current.right != null) {
					if(current.left.priority < current.right.priority) {
						current = current.rotateLeft();
						current.left = deleteHelp(key, current.left);
					}
					else {
						current = current.rotateRight();
						current.right = deleteHelp(key, current.right);
					}
				}
				else {
					if(current.left != null) {
						current = current.left;
					}
					if(current.left == null) {
						
						current = current.right;
					}
				}
				
			}
			return current;
		}
		
		
		
	//If the helper function does not modify the treap, then nothing was removed, so false. Otherwise it modifies the root to the new node created by the helper function
		public boolean delete(E key) {
			if(root == null) {
				return false;
			}
			if(key == null) {
				return false;
			}
			else {
				if(deleteHelp(key,root).equals(root)) {
					return false;
				}
				else {
					root = deleteHelp(key, root);
					return true;
				}
			}
			
		}
		
		//recursively searches for the key efficiently by taking advantage of BST rules (left branches if key is smaller than the root data)
		private boolean find(Node<E> root, E key) {
			if(key == null) {
				throw new IllegalStateException("Find: null key");
				
			}
			else {
				if(root == null) {
					return false;
				}
				if(root.data.equals(key)) {
					return true;
				}
				if(key.compareTo(root.data)<0) {
					return find(root.left,key);
				}
				return find(root.right,key);
				
			}
			
		}
		//utilizes the private method along with the root to return true or false if the key is found
		public boolean find(E key) {
			if(key == null) {
				throw new IllegalStateException("Find: null key");
			}
			else {
				return find(root,key);
			}
			
		}
		
		
		
		//Prints out the Treap in a read-able way, using the Btree toString method
		private StringBuilder toString(Node<E> current, int i) {
			StringBuilder r = new StringBuilder() ;
			for (int j=0; j<i; j++) {
				r.append("-");
				}
			if (current==null) {
				r.append("null\n");
				} 
			else {
				r.append("(key=" + current.data.toString() + ", priority="+ current.priority + ")" +"\n");
				r.append(toString(current.left,i+1));
				r.append(toString(current.right,i+1));
				}
			return r;
			}
		
		public String toString() {
			return toString(root,0).toString();
		}
	

}
