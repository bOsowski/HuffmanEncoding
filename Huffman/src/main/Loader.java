package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Loader {

	static protected HashMap<Character,Node> lettersWithFrequencies = new HashMap<Character,Node>();
	static String text = "";
	
	public Loader(String action){
		if(action.equals("encode")){
			getCharsAndFrequencies(new File(getFile("Text Files", "txt")));
		}
		else if(action.equals("decode")){
			getCharactersAndCode(new File(getFile("Compressed Files", "brt")));
		}
	}
	
	private void getCharactersAndCode(File file){
		HashMap<String, Character> lettersAndEncodings = new HashMap<String,Character>();
		String line = "";
		try {
			Scanner scanner = new Scanner(file);
			
			while(!line.contains(Driver.newLineSymbol+"")){
				//System.out.println("LINE = " + line);
				line = scanner.nextLine();
				if(line.length() > 1){
					String charInBin = "";
					for(int i = 0; i<8; i++){
						charInBin += line.charAt(i);
					}
					char character = Driver.binaryToChar(charInBin);
					String encoding = "";
					
					for(int i = 8; i<line.length(); i++){
						encoding += line.charAt(i);
					}
					//System.out.println("Character = "+character+ " encoding = "+encoding);
					lettersAndEncodings.put(encoding, character);	
				}
			}
			System.out.println("Encoding = ");
			while(scanner.hasNext()){
				line = scanner.nextLine();
				System.out.println(decodeLine(line, lettersAndEncodings));
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private String decodeLine(String Encodedline, HashMap<String,Character> map){
		String encoding = "";
		String decodedLine = "";
		for(int i = 0; i<Encodedline.length(); i++){
			encoding += Encodedline.charAt(i);
			if(map.containsKey(encoding)){
				decodedLine += map.get(encoding);
				encoding = "";
			}
		}
		return decodedLine;
		
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
	
	
	public void getCharsAndFrequencies(File file){
		String line;
		
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext()){
				line = scanner.nextLine();
				text = text + line;
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
					text = text + Driver.newLineSymbol;
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.print("File not found!");
		}
	}

}
