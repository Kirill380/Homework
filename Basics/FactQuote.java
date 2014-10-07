import java.io.*;

public class FactQuote {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while(true)
		{
			System.out.print("FactQuoter> ");
			String line = in.readLine();
			if((line == null) || line.equals("quit") ) break;

			try {
				int x = Integer.parseInt(line);
				System.out.println(x + "! = " + Factorial.factorial(x));				
			}

			catch(Exception e) { System.out.println("Invalid input"); }
		}
	}
}