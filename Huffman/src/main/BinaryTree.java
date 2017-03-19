package main;

import java.util.HashMap;
import java.util.PriorityQueue;

public class BinaryTree {
	private PriorityQueue<Node> queue = new PriorityQueue<Node>();
	static protected HashMap<Character, String> letters = new HashMap<Character, String>();
	protected Node root;
	String encoding;
	
	public BinaryTree() {
		createTree();
		createEncodings(root, "");
		encoding = encodeText(Loader.text);	
	}


	public void createTree(){
		queue.addAll(Loader.lettersWithFrequencies.values());
		while(queue.size() > 1){
			Node firstNode = queue.poll();
			Node parentNode = new Node(firstNode.frequency + queue.peek().frequency, firstNode, queue.poll());
			queue.offer(parentNode);
		}
		root = queue.poll();
	}
	
	public void createEncodings(Node node, String frequency){
		String encoding = frequency;
		if(node.left != null){
			String encodingLeft = encoding + '0';
			createEncodings(node.left, encodingLeft);
		}
		if(node.right != null){
			String encodingRight = encoding +'1';
			createEncodings(node.right, encodingRight);
		}
		else{
			System.out.println("character = "+node.character+ " encoding = "+ encoding);
			letters.put(node.character, encoding);
		}
	}
	
	//change to string return later
	public String encodeText(String text){
		
		String decodedText = "";
		System.out.println("length = "+text.length());
		for(int i = 0; i < text.length();i++){
			decodedText = decodedText + letters.get(text.charAt(i));
		}
		return decodedText; 
	}

}
