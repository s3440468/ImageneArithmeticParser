public class TestDriver {
	public static void main(String[] args) {
		String testString1 = "sqr ( root ( sqr ( plus x y ) ) )";
		ArithmeticNode firstNode;
		
		System.out.println("TEST POINT 1");
		System.out.println("Current string: " + testString1);
				
		
		int x = 1, y = 1;
		int total = 0;
		
		ParserInterface PS = new ParserInterface();
		
		firstNode = PS.getArithmetic(testString1);
		
		System.out.println("TEST POINT 2");
		
		total = firstNode.operation(x, y);
		
		System.out.println("The end result is " + total);
	}
	
}
