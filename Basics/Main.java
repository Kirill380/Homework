public class Main {
	public static void main(String[] args) {
		try	{
			int x  = Integer.parseInt(args[0]);
			System.out.println(x + "! = " + Factorial.factorial(x));
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("You must set argument");
			System.out.println("Template: java Main <number>");
		}
		catch(NumberFormatException e) {
			System.out.println("Setting argument must be integer");
		}

		catch(IllegalArgumentException e) {
			System.out.println("Incorrect argument: " + e.getMessage());
		}
	}
}