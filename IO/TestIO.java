import java.io.*;

class Head {
	public static void main(String[] args) {
		int i = 0;
		String s;
		BufferedReader br = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			
			while((s = br.readLine()) != null || i != 10)
				System.out.println(s);
		}

		catch(IOExeception e) {
			System.out.println(e.getMessage());
		}

		finally {
			br.close();
		}
	}

}