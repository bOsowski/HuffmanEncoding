package main;

public class Node implements Comparable<Node>{
	    Node left, right;
	    char character;
	    int frequency;
	    

	    public Node(int frequency, char character, Node left, Node right) {
	        this.frequency = frequency;
	        this.character = character;
	        this.left = left;
	        this.right = right;
	    }
	    
	    public Node(int frequency, Node left, Node right) {
	        this.frequency = frequency;
	        this.left = left;
	        this.right = right;
	    }


		
		@Override
		public String toString() {
			return "Node [left=" + left + ", right=" + right + ", character=" + character + ", frequency=" + frequency
					+ "]";
		}



		@Override
		public int compareTo(Node other) {
			if(this.frequency > other.frequency){
				return 1;
			}
			else if(other.frequency > this.frequency){
				return -1;
			}
			return 0;
		}


	}