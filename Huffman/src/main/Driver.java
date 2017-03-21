package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Driver {
	final static char newLineSymbol = '~';
	Loader loader;
	BinaryTree tree;
	
	public Driver() {
		//System.out.println(charToBinary('J'));
		//System.out.println(binaryToChar("01001010"));
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
				System.out.println("power = "+ Math.pow(2, power)+"  power = "+ power);
			}
		}
		int integer = (char) number;
		return (char) integer;
	}
	
	/**
	 * 
	 * @param an ASCII character
	 * @return 8 digit binary code representing the ASCII character.
	 */
	public static String charToBinary(char character){
		String binary = Integer.toBinaryString(character);	
		//this part adds extra dummy bytes so the number of bytes is always 8
		for(int i = binary.length(); i<8; i++){
			binary = "0"+binary;
		}
		return binary;
	}
 
	
	public void menu(){
		try{
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("1) Compress a text file.");
			System.out.println("2) Decompress a binary file."); 
			System.out.println("0) Exit."); 
			System.out.print("Choice>> ");

			int choice = scanner.nextInt();
			scanner.close();
			switch(choice){
			case 1:
				encode();
				break;
			case 2:
				System.out.println("Not yet implemented.");
				//decode();
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
			// TODO Auto-generated method stub
			
	}

	private void encode(){
		loader = new Loader("encode");	//loads from the file and processes the text into a Hashmap of nodes
		tree = new BinaryTree(); //creates a binary tree based on frequency of each node
		System.out.println("Text: " + Loader.text);
		System.out.println("Encoding: " + tree.encoding );
		writeEncodingToFile("compressed", "UTF-8", tree.encoding, "The encoding has been saved.");
	}
	
	/**
	 * 
	 * @param fileName
	 * Name of the file to be written.
	 * @param fileType
	 * Type of the file to be written.
	 * @param content
	 * The encoding.
	 * @param message
	 * Message to display in case of a failure.
	 */
	private void writeEncodingToFile(String fileName, String fileType, String content, String message){
		try{
		    PrintWriter writer = new PrintWriter(fileName, fileType);
		    
		    for (Map.Entry<Character, String> entry : BinaryTree.letters.entrySet())
		    {
		        writer.println(entry.getValue() + charToBinary(entry.getKey()));	//prints the 8 bit character in binary and then the encoding for it.
		    }
		    writer.println("-");
		    writer.print(content);
		    writer.close();
			System.out.println(message);
		} catch (IOException e) {
		  System.err.println("Unable to write to file.");
		}
	}
	

	public static void main(String[] args) {
		new Driver();
	}

}