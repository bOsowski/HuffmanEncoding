package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Loader {
	HashMap<Character,Node> lettersWithFrequencies;
	long timeBefore;
	File file;
	
	public Loader(String action){
		if(action.equals("encode")){
			file = new File(getFile("Text Files", "txt"));
			getCharsAndFrequencies(file);
		}
		else if(action.equals("decode")){
			decodeFile(new File(getFile("Compressed Files", "brt")));
		}
	}
	
	
	/**
	 * Checks if the file is supported, then creates a Hashmap of letters and their encodings taken from the start of the file.
	 * After the newLineSymbol delimeter is encountered, it proceeds to decode the text.
	 * @param appropriate file to be decoded.
	 */
	private void decodeFile(File file){
		long timeBefore = System.currentTimeMillis();
		HashMap<String, Character> lettersAndEncodings = new HashMap<String,Character>();
		//String line = "";
		try {
			Scanner scanner = new Scanner(file);
			//checking if the file is supported.
			String fileCheck = "00001100101011011101000010011001";
			char[] bits = scanner.next().toCharArray();
			int currentBitIndex = 0;
			for(currentBitIndex = 0; currentBitIndex<32; currentBitIndex++){
				if(fileCheck.charAt(currentBitIndex) != bits[currentBitIndex]){
					System.out.println("The file specified is not supported.");
					scanner.close();
					return;
				}
			}
			System.out.println("Decoding the file..");
			while(currentBitIndex < bits.length){
					String charInBin = "";
					for(int j = currentBitIndex; currentBitIndex<j+8; currentBitIndex++){
						charInBin += bits[currentBitIndex];
					}
					char character = Driver.binaryToChar(charInBin);
			//		System.out.print("character = " + character+"   ");
					String intInBin = "";
					for(int j = currentBitIndex; currentBitIndex<j+5; currentBitIndex++){
						intInBin += bits[currentBitIndex];
					}
					int huffmanCharBitLength = Integer.parseInt(intInBin, 2);	
			//		System.out.print("huffmanCharBitLength = "+ huffmanCharBitLength+"\n");
					String encoding = "";		
					for(int j = currentBitIndex; currentBitIndex<j + huffmanCharBitLength; currentBitIndex++){
						encoding += bits[currentBitIndex];
					}

					//stop reading in the lettters and encodings and start reading the text if the encoding has been already read, eg. the end of the section.
					if(lettersAndEncodings.containsKey(encoding)){
						break;
					}
					else{
						lettersAndEncodings.put(encoding, character);	
					}
			}	
			//System.out.println("Decoding = ");
			System.out.println("Would you like the decoding to be 1)printed in the console or 2)written to a file?");
			
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			long timeBeforeInput = System.currentTimeMillis();
			int choice = sc.nextInt();
			long timeToDeduct = System.currentTimeMillis() - timeBeforeInput;
			
			if(choice == 1){
				System.out.println("Printing the encoding: ");
				printlnDecoding(bits, lettersAndEncodings, currentBitIndex);
			}
			else{
				try {
					System.out.println("Saving decoding to file, please wait..");
					writeDecodingToFile(bits, lettersAndEncodings, currentBitIndex);
					System.out.println("Decoding sucessfully saved. filename = DECOMPRESSED.txt");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			long timeAfter = System.currentTimeMillis();
			System.out.println("It took "+((timeAfter - timeBefore - timeToDeduct)/1000.0)+" seconds to decode the file.");
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * @param
	 * encoded line
	 * @param
	 * map of characters with their encoding as a key
	 * @return
	 * the decoded line
	 */
	private void printlnDecoding(char[] encodedInts, HashMap<String,Character> map, int textStartIndex){
		String encoding = "";
		String decodedLine = "";
		for(int i = textStartIndex; i<encodedInts.length; i++){
			encoding += encodedInts[i];
			if(map.containsKey(encoding)){
				if(map.get(encoding) == Driver.sectionDividerSymbol){
					System.out.println(decodedLine);
					decodedLine = "";
				}
				else{
					decodedLine += map.get(encoding);
				}
				encoding = "";
			}
		}
		System.out.println(decodedLine);	
	}
	
	private void writeDecodingToFile(char[] encodedInts, HashMap<String,Character> map, int textStartIndex) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter("DECODED.txt", "UTF-8");
		String encoding = "";
		String decodedLine = "";
		for(int i = textStartIndex; i<encodedInts.length; i++){
			encoding += encodedInts[i];
			if(map.containsKey(encoding)){
				if(map.get(encoding) == Driver.sectionDividerSymbol){
					writer.println(decodedLine);
					decodedLine = "";
				}
				else{
					decodedLine += map.get(encoding);
				}
				encoding = "";
			}
		}
		writer.println(decodedLine);	
		writer.close();
	}
	
	/*public File pickAFile(String defaultFilePath, String fileType){
		JFileChooser fc = new JFileChooser();
		System.out.println("1");
		fc.setCurrentDirectory(new File(defaultFilePath));
		System.out.println("2");
		int returnVal = fc.showOpenDialog(null);	//BUG HERE WHEN USING SCANNER.NEXT..
		System.out.println("3");
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile(); 
			if(file.getName().charAt(file.getName().length()-1) == fileType.charAt(0) && file.getName().charAt(file.getName().length()-2) == fileType.charAt(1) && file.getName().charAt(file.getName().length()-3) == fileType.charAt(2)){
				return file;
			}
			else{
				System.out.println("Wrong file extension! Pick a ."+ fileType+" file!");
				return pickAFile(defaultFilePath, fileType);	
			}
		}
		System.out.println("No file chosen.");
		System.exit(0);
		return null;
	}*/

	/**@author Oleksandr Kononov
	 * @return a chosed text file path.
	 */
	private String getFile(String fileType, String fileExtension){
		String filePath = "";
		JFrame jf = new JFrame(); // added
        jf.setAlwaysOnTop( true ); // added
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter(fileType,fileExtension);
        fileChooser.setFileFilter(filter);
        fileChooser.showOpenDialog( jf );  // changed
        filePath = fileChooser.getSelectedFile().getPath();
        jf.dispose(); // added
        
        return filePath;
	}
	
	/**
	 * Gets the frequencies of each unique character in the given file and
	 * puts them in a hashmap.
	 * Creates a text with a line symbol in place of a new line character.
	 * @param file to be encoded.
	 */
	public void getCharsAndFrequencies(File file){
		timeBefore = System.currentTimeMillis();
		lettersWithFrequencies = new HashMap<Character,Node>();
		String line;
		
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext()){
				line = scanner.nextLine();
				for(int i =0; i<line.length(); i++){
							if(lettersWithFrequencies.containsKey(line.charAt(i))){//If letters contains the letter
								lettersWithFrequencies.get(line.charAt(i)).frequency++;//add the weight to the proper letter in letters
							}
							else{//if the letter in word isn't in letters, add it to letters.
								lettersWithFrequencies.put(line.charAt(i), new Node(1,line.charAt(i),null, null));
							}
				}
				
				//adding new lines to the text
				if(scanner.hasNextLine()){
					System.out.println("new line..");
					if(lettersWithFrequencies.containsKey(Driver.sectionDividerSymbol)){
						lettersWithFrequencies.get(Driver.sectionDividerSymbol).frequency++;
					}
					else{
						lettersWithFrequencies.put(Driver.sectionDividerSymbol, new Node(1,Driver.sectionDividerSymbol,null, null));
					}
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.print("File not found!");
		}
	}

}
