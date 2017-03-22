package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BinaryTree {
	private PriorityQueue<Node> queue = new PriorityQueue<Node>();
	HashMap<Character, String> letters = new HashMap<Character, String>();
	Node root;
	public long timeToDiscount;
	
	public BinaryTree(HashMap<Character,Node> lettersWithFrequencies, File file) {
		createTree(lettersWithFrequencies);
		createEncodings(root, "");
		try {
			encodeText(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void createTree(HashMap<Character,Node> lettersWithFrequencies){
		queue.addAll(lettersWithFrequencies.values());
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
	
	@SuppressWarnings("resource")
	public void encodeText(File file) throws FileNotFoundException, UnsupportedEncodingException{
		Scanner scanner =  new Scanner(System.in);
		System.out.println("What name should be given to the encoded file?>>");
		long timeBefore = System.currentTimeMillis();
		String filename = scanner.next();
		timeToDiscount = System.currentTimeMillis() - timeBefore;
		
	    PrintWriter writer = new PrintWriter(filename+".brt", "UTF-8");
	    writer.println("00001100101011011101000010011001");//identifier
	    for (Map.Entry<Character, String> entry : letters.entrySet())
	    {
	    	String charInBinary = charToBinary(entry.getKey());
	        writer.println(charInBinary + entry.getValue());	//prints the 8 bit character in binary and then the encoding for it.
	    }
	    writer.println(Driver.sectionDividerSymbol);	//separate the encoding from character tree
		scanner = new Scanner(file);
		System.out.println("length = "+file.length());
		long fileLengthInBits = file.length()*8;
		System.out.println("length in bits = "+fileLengthInBits);
		
		while(scanner.hasNext()){
			String line = scanner.nextLine();
			for(char character: line.toCharArray()){
				writer.print(letters.get(character));
			}
			writer.println();
		}
	    writer.close();
	    scanner.close();
	    
	    //the below displays information about the compression and original file.
	    File encodedFile = new File(filename+".brt");
	    long encodedFileLength = encodedFile.length();
	    double percentageSaved = (100.0-(((double)encodedFileLength/(double)fileLengthInBits)*100.0));
	    System.out.println("The size of the encoding in bits = "+encodedFileLength);
	    System.out.println("Percentage saved = "+percentageSaved);
		System.out.println("The encoding has been saved.");
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

}
