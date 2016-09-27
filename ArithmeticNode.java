public class ArithmeticNode {
	private ArithmeticNode prevNode = null;
	private ArithmeticNode nextNode = null;
	private ArithmeticNode downNode = null;
	private ArithmeticNode upNode = null;
	
	private boolean hasNextNode;
	private boolean hasDownNode;
	
	private IArithmetic nodeArithmetic;
	
	public ArithmeticNode(ArithmeticNode prevNode) {
		this.prevNode = prevNode;
		this.hasNextNode = false;
		this.hasDownNode = false;
	}
	
	public ArithmeticNode getPrevNode() {
		return this.prevNode;
	}
	
	public ArithmeticNode getNextNode() {
		return this.nextNode;
	}
	
	public void setNextNode(ArithmeticNode nextNode) {
		this.nextNode = nextNode;
	}
	
	public ArithmeticNode getDownNode() {
		return this.downNode;
	}
	
	public void setDownNode(ArithmeticNode downNode) {
		this.downNode = downNode;
	}
	
	public ArithmeticNode getUpNode() {
		return this.upNode;
	}
	
	public void setUpNode(ArithmeticNode upNode) {
		this.upNode = upNode;
	}
	
	public boolean hasNext() {
		return this.hasNextNode;
	}
	
	public void setNext(boolean isNext) {
		this.hasNextNode = isNext;
	}
	
	public boolean hasDown() {
		return this.hasDownNode;
	}
	
	public void setDown(boolean isDown) {
		this.hasDownNode = isDown;
	}
	
	public IArithmetic getArithmetic() {
		return this.nodeArithmetic;
	}
	
	public void setNodeArithmetic(IArithmetic newArithmetic) {
		this.nodeArithmetic = newArithmetic;
	}
	
	public int operation(int first, int second) {
		if (this.hasDownNode == true && this.hasNextNode == false) {
			System.out.println("FIRST PART OF OPERATION");
			int answer = this.downNode.operation(first, second);
			return this.nodeArithmetic.operation(answer, 0);
		} else if (this.hasDownNode == true && this.hasNextNode == true) {
			System.out.println("SECOND PART OF OPERATION");
			int answer = this.downNode.operation(first, second);
			int answer2 = this.nextNode.operation(first, second);
			int finalAnswer = answer + answer2;
			return this.nodeArithmetic.operation(finalAnswer, 0);
		} else {
			System.out.println("THIRD PART OF OPERATION");
			return this.nodeArithmetic.operation(first, second);
		}
	}
}
