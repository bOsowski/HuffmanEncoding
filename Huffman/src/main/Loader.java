package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Loader {

	private ArrayList<Letter> letters = new ArrayList<Letter>();
	
	public Loader() {
		readTextFile(new File("textFile.txt"));
	}
	
	@SuppressWarnings("unchecked")
	public void readTextFile(File file){
		String line;
		
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext()){
				line = scanner.nextLine();	
				for(int i =0; i<line.length(); i++){
					boolean contains = false;
						for(Letter letter: letters){
							if(letter.getCharacter() == line.charAt(i)){//If letters contains the letter
								letter.addWeight();//add the weight to the proper letter in letters
								contains = true;
								break;
							}
						}
					if(!contains){//if the letter in word isn't in letters, add it to letters.
						letters.add(new Letter(line.charAt(i)));
					}
				}
			}
			scanner.close();
			Collections.sort(letters);
		} catch (FileNotFoundException e) {
			System.err.print("File not found!");
		}
	}

	public ArrayList<Letter> getLetters() {
		return letters;
	}

	public void setLetters(ArrayList<Letter> letter) {
		this.letters = letter;
	}
}
