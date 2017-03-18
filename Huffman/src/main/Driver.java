package main;


public class Driver {
	Loader loader;
	
	public Driver() {
		loader = new Loader();	//loads from the file and processes the text into a Hashmap of nodes
		BinaryTree tree = new BinaryTree(); //creates a binary tree based on frequency of each node
		System.out.println("Text: " + Loader.text);
		System.out.println("Encoding: " + tree.encoding );
	}
	
	
	

	public static void main(String[] args) {
		new Driver();
	}

}