package main;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Crevop
 * The file padding will be as follows:
 * 
			32bits for the file identifier.
			8 bits for the character in ascii, followed by 5 bits signifying the amount of bits the huffman encoded character will take.
			End of section would be identified as 2x the same huffman character.
			Then a new section will begin.
			
 * 										
 * 										
 */
public class Driver {
	final static char sectionDividerSymbol = '~';
	Loader loader;
	BinaryTree tree;
	
	public Driver() {
		menu();
	}
	
	/**
	 * 
	 * @param binary of an ASCII character
	 * @return the ascii character
	 */
	public static char binaryToChar(String binary){
		double number = 0.0;
		for(int i = 0, power = binary.length()-1; i < binary.length(); i++, power --){
			if(binary.charAt(i) == '1'){
				number += Math.pow(2, power);
			}
		}
		int integer = (char) number;
		return (char) integer;
	}
 
	public void menu(){
		try{
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("1) Compress a text file.");
			System.out.println("2) Decompress a binary file."); 
			System.out.println("0) Exit."); 
			System.out.print("Choice>> ");

			int choice = scanner.nextInt();
			System.out.println();
			//scanner.close();	//creates a bug when trying to open up a menu again.
			switch(choice){
			case 1:
				encode();
				new Driver();
				break;
			case 2:
				decode();
				new Driver();
				break;
			case 0:
				System.out.println("Exitting..");
				break;
			default:
				System.out.println("Incorrect choice!");
				menu();
				break;
			}
		}catch(InputMismatchException e){
			System.err.println("Invalid input.");
			menu();
		}
	}
	
	private void decode() {
		loader = new Loader("decode");
		System.out.println();
		System.out.println();
	}

	private void encode(){
		loader = new Loader("encode");	//loads from the file and processes the text into a Hashmap of nodes
		tree = new BinaryTree(loader.lettersWithFrequencies, loader.file); //creates a binary tree based on frequency of each node
		System.out.println("It took "+(((System.currentTimeMillis() - loader.timeBefore)-tree.timeToDiscount)/1000.0)+" seconds to encode the file.\n\n");
	}
	

	

	public static void main(String[] args) {
		new Driver();
	}

}