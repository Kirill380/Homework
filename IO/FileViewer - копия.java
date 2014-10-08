import java.io.*;

class Hello {
	public static void main(String[] args) {
                PrintWriter pw = null;
		try {
			pw = new PrintWriter(new OutputStreamWriter(System.out, "Cp866"), true);
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		pw.println("Русский текст");
		System.out.println("Русский текст");
	}

}