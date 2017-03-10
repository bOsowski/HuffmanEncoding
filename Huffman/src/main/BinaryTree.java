package main;
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
	
	public void addNode(Letter letter) {

		// Create a new Node and initialize it

		Node newNode = new Node(letter);

		// If there is no root this becomes root

		if (root == null) {

			root = newNode;

		} else {

			// Set root as the Node we will start
			// with as we traverse the tree

			Node focusNode = root;

			// Future parent for our new Node

			Node parent;

			while (true) {

				// root is the top parent so we start
				// there

				parent = focusNode;

				// Check if the new node should go on
				// the left side of the parent node

				if (letter.compareTo(focusNode.data) < 0) {

					// Switch focus to the left child

					focusNode = focusNode.left;

					// If the left child has no children

					if (focusNode == null) {

						// then place the new node on the left of it

						parent.left = newNode;
						return; // All Done

					}

				} else { // If we get here put the node on the right

					focusNode = focusNode.right;

					// If the right child has no children

					if (focusNode == null) {

						// then place the new node on the right of it

						parent.right = newNode;
						return; // All Done

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
