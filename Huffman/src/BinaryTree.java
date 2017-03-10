public class BinaryTree {

	Node<Letter> root;
	
	public BinaryTree() {

	}
	
	void printPreOrder(Node root, String indent)

    {

        if(root == null)

            return;

        System.out.println(""+indent+root.data);

        if(root.left != null){

            printPreOrder(root.left,indent+"   ");

        }

        if(root.right != null){

            printPreOrder(root.right,indent+"   ");

        }

    }
	
	public void addNode(Letter letter){
		Node<Letter> newNode = new Node(letter);
		
		if(root == null){
			root = newNode;
		}
		else{
			Node focusNode = root;
			Node parent;
			while(true){
				parent = focusNode;
				if(letter.compareTo(focusNode.data) < 0){
					focusNode = focusNode.left;
					if(focusNode == null){
						parent.left = newNode;
						return;
					}
				}
				else{
					focusNode = focusNode.right;
					if(focusNode == null){
						parent.right = newNode;
						return;
					}
				}
			}
		}
	}	
	
	public void printTree(){
		if(root != null){
			System.out.println(root);
		}
	}
	
	

	class Node<T extends Comparable<?>> {
	    Node<T> left, right;
	    T data;

	    public Node(T data) {
	        this.data = data;
	    }

		@Override
		public String toString() {
			//return "Node [left=" + left + ", right=" + right + ", data=" + data + "]";
			return "\ndata = "+data+" left = "+left+" right = "+right;
		}
	    

	}
}
