import ParserExceptions.*;

public class ParserInterface {
	
	private IArithmetic addition = (int first, int second) -> first + second;
	private IArithmetic subtraction = (int first, int second) -> first - second;
	private IArithmetic multiplication = (int first, int second) -> first * second;
	private IArithmetic division = (int first, int second) -> first / second;
	private IArithmetic squareRoot = (int first, int second) -> 
		(int)Math.sqrt((double)(first));
	private IArithmetic squared = (int first, int second) -> first * first;
	private IArithmetic cubed = (int first, int second) -> first * first * first;
	
	private enum ArgNumType {
		NONE, ONE_ARG, TWO_ARG
	}
	
	public ParserInterface() {
		
	}
	
	public ArithmeticNode getArithmetic(String initialString) {
		initialString = initialString.toLowerCase();
		
		String[] tokenizedStringArray = initialString.split(" ");
		
		ArithmeticNode firstNode = null;
		
		try {
			firstNode = checkStringArray(tokenizedStringArray);
		} catch (InvalidArgumentException | IncorrectVariablesException e) {
			e.printStackTrace();
		}
		
		return firstNode;		
	}
	
	private ArithmeticNode checkStringArray(String[] stringArray) 
			throws InvalidArgumentException, IncorrectVariablesException {
		ArithmeticNode firstNode = null;
		ArithmeticNode prevNode = null, currNode = null;
		int arrayLength = stringArray.length;
		int arrayPosition = 0, currentVarPos = 0;
		ArgNumType currentArgs = ArgNumType.NONE;
		String currentArg;
		boolean isNewNode = true;
		
		System.out.println("There are " + arrayLength + " arguments in this string");
		
		while (arrayPosition != arrayLength) {
			currentArg = stringArray[arrayPosition];
			
			System.out.println("Current Array Position: " + arrayPosition);
			System.out.println("Current String: " + currentArg);
			
			if (arrayPosition == 0) {
				IArithmetic newFunction;
				
				try {
					newFunction = returnValidFunction(currentArg);
				} catch(InvalidArgumentException e) {
					throw new InvalidArgumentException(
							"Invalid Starting Argument");
				}
				
				System.out.println("Creating first node");
				
				firstNode = new ArithmeticNode(null);
				firstNode.setNodeArithmetic(newFunction);
				
				currNode = firstNode;
				
				currentArgs = this.returnNumOfArgs(currentArg);
				currentVarPos = 0;
			} else {
				if (currentArg.equals("(")) {
					prevNode.setDown(true);
					isNewNode = false;
				} else if (currentArg.equals(")")) {
					currNode = prevNode.getUpNode();
					isNewNode = false;
				} else if (currentArg.equals("x") ||
						currentArg.equals("y")) {
					currentVarPos++;
					
					if (currentVarPos > currentArgs.ordinal()) {
						throw new IncorrectVariablesException(
								"Incorrect number of x/y arguments");
					}
					isNewNode = false;
				} else {
					IArithmetic newFunction;
					ArithmeticNode newNode;
					
					try {
						newFunction = returnValidFunction(currentArg);
					} catch(InvalidArgumentException e) {
						throw new InvalidArgumentException(
								"Invalid Argument");
					}
					
					newNode = new ArithmeticNode(prevNode);
					newNode.setNodeArithmetic(newFunction);
					
					if (prevNode.hasDown()) {
						prevNode.setDownNode(newNode);
						newNode.setUpNode(prevNode);
					} 
					
					currNode = newNode;
					
					currentArgs = this.returnNumOfArgs(currentArg);
					currentVarPos = 0;
					isNewNode = true;
				}
			}
			
			if (isNewNode == true) {
				prevNode = currNode;				
				isNewNode = false;
			}
			
			arrayPosition++;
		}
		
		return firstNode;
	}
	
	private ArgNumType returnNumOfArgs(String currentArg) {
		switch (currentArg) {
		case "sin" :
		case "cos" :
		case "tan" :
		case "root" :
		case "sqr" :
		case "cub" : return ArgNumType.ONE_ARG;
		case "plus" :
		case "minus" :
		case "times" :
		case "div" : return ArgNumType.TWO_ARG;
		default : return ArgNumType.NONE;
		}
	}
	
	private IArithmetic returnValidFunction(String funcType) 
			throws InvalidArgumentException {
		switch (funcType) {
		case "plus" : return this.addition;
		case "minus" : return this.subtraction;
		case "times" : return this.multiplication;
		case "div" : return this.division;
		case "root" : return this.squareRoot;
		case "sqr" : return this.squared;
		case "cub" : return this.cubed;
		default : throw new InvalidArgumentException(
				"Invalid Argument");		
		}
	}
}
