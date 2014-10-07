import java.io.*;

public class FileCopy {

	public static void main(String[] args) {
		if(args.length != 2)
			System.out.println("Pattern of command: java FileCopy <source file> <destination file>");
		else {
			try { copy(args[0], args[1]); }
			catch(IOException e) { System.err.println(e.getMessage()); }
		}
	}

	public static void copy(String from_name, String to_name) throws IOException {

		File from_file = new File(from_name);
		File to_file = new File(to_name);

		/*begin checking */

		if(!from_file.exists()) 
			abort("The file " + from_file.getName() + " doesn't exist.");
		
		if(!from_file.isFile())
			abort("Error");

		if(!from_file.isFile())
			abort("Error");


		if(to_file.isDirectory())
			to_file = new File(to_file, from_file.getName());

		if(to_file.exists()) {
			if(!to_file.canWrite())
				abort("The file can't be rewritten.");
			System.out.println("Do you want to rewrite the file" 
								+ to_file.getName() + " [Y/N]?");
			System.out.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String response = in.readLine();
			if(!response.equals("Y") && !response.equals("y"))
					abort("The file " + to_name + " has not been rewritten.");
		}
		else
			abort("The file " + to_file.getName() + " doesn't exist.");
		
		/* end checking */

		FileInputStream from = null; // Stream for reading from source file
		FileOutputStream to = null; // Stream for writing down in destination file
		
		try {

			from = new FileInputStream(from_file);
			to = new FileOutputStream(to_file);
			byte[] buffer = new byte[4096];
			int bytes_read;

			while((bytes_read = from.read(buffer)) != -1)
				to.write(buffer, 0, bytes_read);
		
		}

		finally {
			if(from != null)
					try{ from.close(); }
			catch(IOException e) { ; }

			if(to != null)
					try{ to.close(); }
			catch(IOException e) { ; }
		}
	}

	private static void abort(String msg) throws IOException {
		throw new IOException("FileCopy: " + msg);
	}

}

