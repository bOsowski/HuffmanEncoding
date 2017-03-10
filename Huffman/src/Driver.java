
public class Driver {

	public Driver() {
		Loader loader = new Loader();
		BinaryTree tree = new BinaryTree();
		for(int i =0; i<loader.getLines().size(); i++){
			System.out.println("Letter"+i+" = "+loader.getLines().get(i).getCharacter()+"   weight = "+ loader.getLines().get(i).getWeight());
			tree.addNode(loader.getLines().get(i));
		}
		tree.printPreOrder(tree.root," ");
		
		
	}

	public static void main(String[] args) {
		new Driver();
	}

}