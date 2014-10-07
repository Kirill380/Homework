import java.io.*;

public class Delete {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("Pattern of command: java Delete <fiel or direction>");
			System.exit(0);
		}

		try{ delete(args[0]); }
		catch(IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void delete(String filename) {
		File f = new File(filename);
		if(!f.exists()) fail("Delete: The file or direction doesn't exist.");
		if(!f.canWrite()) fail("Delete: One are secured from write.");

		if(f.isDirectory()) {
			String[] files = f.list();
			if(files.length > 0)
				fail("Delete: directory is not empty.");
		}

		boolean success = f.delete();
		if(!success) fail("Delete: Deleting is not success.");
	}

	private static void fail(String msg) throws IllegalArgumentException {
		throw new IllegalArgumentException(msg);
	}

}