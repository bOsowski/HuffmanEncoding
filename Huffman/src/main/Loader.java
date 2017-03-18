package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Loader {

	static protected HashMap<Character,Node> lettersWithFrequencies = new HashMap<Character,Node>();
	static protected String text = "";
	
	public Loader() {
		readTextFile(new File("textFile.txt"));
	}

	public void readTextFile(File file){
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
				/*if(scanner.hasNextLine()){	//bug, input keeps getting null for the new line characters.
					text = text + "\n";
					if(!lettersWithFrequencies.containsKey('n') && !lettersWithFrequencies.containsKey('/')){
						
						lettersWithFrequencies.put('\\',new Node(1,'\\',null, null));
						lettersWithFrequencies.put('n',new Node(1,'n',null, null));
						System.out.println("Scanner has next line and there are no letters containing n");
					}
					else{
						lettersWithFrequencies.get('\\').frequency++;
						lettersWithFrequencies.get('n').frequency++;
						System.out.println("Scanner has next line and there are letters containing n");
					}
				}*/
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.print("File not found!");
		}
	}

}
