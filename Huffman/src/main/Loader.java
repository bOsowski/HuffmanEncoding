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
			
		}
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
