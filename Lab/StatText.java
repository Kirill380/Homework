// UTF-8 or UTF-16?
import java.io.*;
import java.util.*;

public class StatText {

	private HashMap<String, Integer> listWord = new HashMap<>();
	private HashMap<Character, Integer> listSymbol = new HashMap<>();
//!!
	public void statistic(String filename) {

		String s;
		File f;
		// We use stream FileReader because we need character stream
		FileReader in = null;
		BufferedReader br = null;

		listSymbol.clear();
		listWord.clear();
		try {
			f = new File(filename);
			in = new FileReader(f);
			br = new BufferedReader(in);

			while((s = br.readLine()) != null) {

				char[] alf = s.toCharArray();
				for(char c : alf) {
					if(listSymbol.containsKey(c))
						listSymbol.put(c, listSymbol.get(c) + 1);
					else
						listSymbol.put(c, 1);
				}

				//optimize regex!
				String[] arr = s.split("!+| |\\?+|\\.+|,|:|\\]|\\[|\\)|\\/|\\)|\\(|;|\"|--+|");

				for(String w : arr) 
					if(w != null) {
						if(listWord.containsKey(w))
							listWord.put(w, listWord.get(w) + 1);
						else
							listWord.put(w, 1);
				}
			}

		}

		catch(IOException e) {
			System.out.println(e.getMessage());
		}

		finally {

			try { if(in != null) in.close(); } 
			catch(IOException e) { }

			try { if(br != null) br.close(); }
			catch(IOException e) { }
		}
	}

	public HashMap<String, Integer> getListWord() {
		return listWord;
	}

	public HashMap<Character, Integer> getListSymbol() {
		return listSymbol;
	}

	public long numWord() {
		long num = 0;
		for(Map.Entry<String, Integer> e : listWord.entrySet())
			num += e.getValue();
		return num;
	}


	public long numSymbol() {
		long num = 0;
		for(Map.Entry<Character, Integer> e : listSymbol.entrySet())
			num += e.getValue();
		return num;
	}

	public static class Test {
		public static void main(String[] args) {
			StatText st = new StatText();
			st.statistic(args[0]);

			for(Map.Entry<String, Integer> e : st.getListWord().entrySet())
           	System.out.println(e.getKey() + " - " + e.getValue());
            System.out.println("");
            for(Map.Entry<Character, Integer> e : st.getListSymbol().entrySet())
            	System.out.println(e.getKey() + " - " + e.getValue());
            System.out.println("");

            System.out.println("Symbols: " + st.numSymbol());
            System.out.println("Words: " + st.numWord());

             System.out.println("Unique Symbols: " + st.getListSymbol().size());
            System.out.println("Unique Words: " + st.getListWord().size());

		}
	}
}