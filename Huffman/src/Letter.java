
public class Letter {
	
	int weight;
	char character;
	
	
	public Letter(char character) {
		this.character = character;
		weight = 1;
	}
	
	public void addWeight(){
		weight++;
	}

	public int getWeight() {
		return weight;
	}

	public char getCharacter() {
		return character;
	}

}
