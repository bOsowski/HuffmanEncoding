package main;

import java.util.ArrayList;
import java.util.Collections;

public class Driver {

	Loader loader;
	
	public Driver() {
		loader = new Loader();
		
		
		
		//print out the letters and weights.
		for(int i =0; i<loader.getLetters().size(); i++){
			System.out.println("Letter"+i+" = "+loader.getLetters().get(i).getCharacter()+"   weight = "+ loader.getLetters().get(i).getWeight());
		}
		BinaryTree tree = new BinaryTree();
		//BTreePrinter printer = new BTreePrinter();
		//printer.printNode(tree.root);
		System.out.println("root = " + tree.root.frequency);
	}
	
	
	

	public static void main(String[] args) {
		new Driver();
	}

}