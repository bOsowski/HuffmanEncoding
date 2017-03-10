
public class Driver {

	public Driver() {
		Loader loader = new Loader();
		for(int i =0; i<loader.getLines().size(); i++){
			System.out.println("Letter"+i+" = "+loader.getLines().get(i).getCharacter()+"   weight = "+ loader.getLines().get(i).getWeight());
		}
	}

	public static void main(String[] args) {
		new Driver();
	}

}