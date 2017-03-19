package main;

import java.io.IOException;
import java.io.PrintWriter;

public class Driver {
	Loader loader;
	BinaryTree tree;
	
	public Driver() {
		loader = new Loader();	//loads from the file and processes the text into a Hashmap of nodes
		tree = new BinaryTree(); //creates a binary tree based on frequency of each node
		System.out.println("Text: " + Loader.text);
		System.out.println("Encoding: " + tree.encoding );
		writeCompressionToFile("compressed", "UTF-8");

	}
	
	private void writeCompressionToFile(String fileName, String fileType){
		try{
		    PrintWriter writer = new PrintWriter(fileName, fileType);
		    writer.print(tree.encoding);
		    writer.close();
		} catch (IOException e) {
		  System.err.println("Unable to write to file.");
		}
	}
	

	public static void main(String[] args) {
		new Driver();
	}

}