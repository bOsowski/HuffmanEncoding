package main;

import java.util.ArrayList;

public class BinaryTree {

	Loader loader = new Loader();
	ArrayList<Letter> letters;
	Node firstNode;
	Node root;
	
	public BinaryTree() {
		createTree();
	}
	
	@SuppressWarnings("rawtypes")
	public void createTree(){
		letters = loader.getLetters();
		firstNode = createFirstNode();//first node is the node on the left, big node.
		Node lastNode = createLastNode();//node at the right, smallest node.

		
		combineNodes(lastNode);	
	}
	
	//recursive function that will combine the nodes from lightest to heaviest till all characters are used.
	//If all the characters are usedm make a root node combining the first nodes and the other nodes.
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void combineNodes(Node node){
		Node newNode = new Node(node.frequency + letters.get(0).getWeight(), letters.get(0),node);
		System.out.println("creating node with freq "+newNode.frequency+" and letter "+newNode.left);
		letters.remove(0);
		if(letters.size() > 0){
			combineNodes(newNode);
		}
		else{
			root = new Node(firstNode.frequency+newNode.frequency, firstNode, newNode);
		}
	}
	
	//returns a node containing the lightest letters. its data is the frequency of the 2 letters.
	private Node createLastNode(){
		Letter smallestLetter = letters.get(0);
		System.out.println("creating first smallest letter.. adding "+ letters.get(0));
		letters.remove(0);
		Letter secondSmallestLetter = letters.get(0);
		System.out.println("creating second smallest letter.. adding "+ letters.get(0));
		letters.remove(0);
		int frequency = smallestLetter.getWeight() + secondSmallestLetter.getWeight();
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Node lastNode = new Node(frequency, smallestLetter, secondSmallestLetter);
		System.out.println("lastNode created. = " + lastNode+"\n");
		return lastNode;
	}	
	
	//returns a node containing the second and third heaviest letters. its data is the frequency of the 2 letters.
	private Node createFirstNode(){
		Letter secondBiggestLetter = letters.get(letters.size()-2);
		System.out.println("creating first biggest letter.. adding "+ letters.get(letters.size()-2));
		letters.remove(letters.size()-2);
		Letter thirdBiggerLetter = letters.get(letters.size()-2);
		System.out.println("creating first biggest letter.. adding "+ letters.get(letters.size()-2));
		letters.remove(letters.size()-2);

		int frequency = secondBiggestLetter.getWeight() + thirdBiggerLetter.getWeight();
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Node firstNode = new Node(frequency, thirdBiggerLetter, secondBiggestLetter);
		System.out.println("firstNode  = " + firstNode+"\n");
		return firstNode;
	}

	
	class Node<T> {
	    T left, right;
	    int frequency;
	    

	    public Node(int frequency, T left, T right) {
	        this.frequency = frequency;
	        this.left = left;
	        this.right = right;
	    }

		@Override
		public String toString() {
			//return "Node [left=" + left + ", right=" + right + ", data=" + data + "]";
			return "[ = "+frequency+" left = "+left+" right = "+right+"]";
		}
	}
}
