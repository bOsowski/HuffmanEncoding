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

		//the below swaps the Driver.newLineSymbol characters from the original text to a new line character
		String newText = "";
		for(int i = 0; i<Loader.text.length(); i++){
			if(Loader.text.charAt(i) != Driver.newLineSymbol){
				newText += Loader.text.charAt(i);
			}
			else{
				newText += "\n";
			}
		}
		Loader.text = newText;
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
		//	System.out.println("character = "+node.character+ " encoding = "+ encoding);
			letters.put(node.character, encoding);
		}
	}
	
	public String encodeText(String text){
		
		String decodedText = "";
		System.out.println("length = "+text.length());
		for(int i = 0; i < text.length();i++){
			//
			if(Driver.newLineSymbol == text.charAt(i)){//makes a new line in the encoding where there is a new line in the text.
				decodedText = decodedText + "\n";
			}
			else{
				decodedText = decodedText + letters.get(text.charAt(i));
			}
		}
		return decodedText; 
	}

}
