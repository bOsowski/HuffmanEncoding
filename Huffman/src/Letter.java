
public class Letter implements Comparable{
	
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

	@Override
	public int compareTo(Object other) {
		if(this.weight > ((Letter) other).getWeight()){
			return 1;
		}
		else if(this.weight < ((Letter) other).getWeight()){
			return -1;
		}
		else if(this.character > ((Letter) other).getCharacter()){
			return 1;
		}
		else if(this.character < ((Letter) other).getCharacter()){
			return -1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return weight+"";
	}
	
	

}
