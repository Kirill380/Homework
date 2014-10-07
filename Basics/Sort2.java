// улучшить быструю сортировку

import java.text.Collator;
import java.text.CollationKey;
import java.util.Locale;

public class Sort2 {

	public static interface Comparer {
		public int compare(Object a, Object b);
	}

	public static interface Comparable {
		public int compareTo(Object other);
	}

	// comparable_comp создан безымянным классом
	// for comparable objects or objects of classes implement Comparable 
	private static Comparer comparable_comp = new Comparer() {
		public int compare(Object a, Object b) {
			return ( ((Comparable)a).compareTo((Comparable)b) );
		}
	};

	/* begin string */

	/* For strings consist of ascii symbol */

	private static Comparer ascii_comp = new Comparer() {
		public int compare(Object a, Object b) {
			return ( ((String)a).compareTo((String)b) );
		}
	};

	public static void sortAscii(String[] a) {
		sort(a, null, 0, a.length - 1, true, ascii_comp);
	}

	public static void sortAscii(String[] a, int from, int to, boolean asc) {
		sort(a, null, from, to, asc, ascii_comp);
	}

	public static void sortAsciiIgnoreCase(String[] a) {
		sortAsciiIgnoreCase(a, 0, a.length - 1, true);
	}

	public static void sortAsciiIgnoreCase(String[] a, int from, int to, boolean asc) {
		String b[] = new String[a.length];
		for(int i = 0; i < a.length; i++) b[i] = a[i].toLowerCase();
		sort(b, a, from, to, asc, ascii_comp);
	}

	/* For strings consist of local symbol */

	public static void sort(String[] a) {
		sort(a, 0, a.length, true, false, null);
	}

	public static void sort(String[] a, int from, int to, boolean asc,
	 boolean ignor) {
		sort(a, from, to, asc, ignor, null);
	}

	public static void sort(String[] a, int from, int to, boolean asc,
	 boolean ignor, Locale loc) {

	 	if( (a == null) || (a.length < 2) ) return;
		
		Collator c;
		if(loc == null) c = Collator.getInstance();
		else c = Collator.getInstance(loc);
		if(ignor) c.setStrength(Collator.SECONDARY);

		CollationKey[] b = new CollationKey[a.length];
		for(int i = 0; i < a.length; i++) b[i] = c.getCollationKey(a[i]);
	
		Comparer comp = new Comparer() {
			public int compare(Object a, Object b) {
				return ( ((CollationKey)a).compareTo((CollationKey)b) );
			}
		};

		sort(b, a, from, to, asc, comp);
	}
	/* end string */

	/*begin comparable object*/
	public static void sort(Comparable[] a) {
		sort(a, null, 0, a.length - 1, true);
	}

	public static void sort(Comparable[] a, int from, int to, boolean asc) {
		sort(a, null, from, to, asc);
	}

	public static void sort(Comparable[] a, Object[] b, int from, int to, boolean asc) {
		sort(a, b, from, to, asc, comparable_comp);
	}
	/*end comparable object*/

	public static void sort(Object[] a, Comparer c) {
		sort(a, null, 0, a.length - 1, true, c);
	}

	public static void sort(Object[] a, int from, int to, boolean asc, 
		Comparer c) {
		sort(a, null, from, to, asc, c);
	}

	/*! The main method of quick sort !*/
	public static void sort(Object[] a, Object[] b, int from, int to, 
		boolean asc, Comparer c) {

		if( (a == null) || (a.length < 2) ) return;
		int i = from, j = to;
		Object center = a[(from + to) / 2];
		
		do {
			if(asc) {
				while((i < to) && (c.compare(center, a[i]) > 0)) i++;
				while((j > from) && (c.compare(center, a[j]) < 0)) j--;
			} 
			else {
				while((i < to) && (c.compare(center, a[i]) < 0)) i++;
				while((j > from) && (c.compare(center, a[j]) > 0)) j--;				
			}

			if(i < j) {
				Object tmp = a[i]; a[i] = a[j]; a[j] = tmp;
				if(b != null) { tmp = b[i]; b[i] = b[j]; b[j] = tmp; }
			}

			if(i <= j) { i++; j--; }

		} while(i <= j);

		if(from < j) sort(a, b, from, j, asc, c);

		if(i < to) sort(a, b, i, to, asc, c);
	}

	public static class Test {

		static class SortableComplexNumber extends ComplexNumber
		implements Sort2.Comparable {
			public SortableComplexNumber(double x, double y) { super(x, y); }
			public int compareTo(Object other) {
				return sign(this.magnitude() - ((ComplexNumber)other).magnitude());
			}
		} 

		public static void main(String[] args) {
			
			SortableComplexNumber[] a = new SortableComplexNumber[10000000];
			for(int i = 0; i < a.length; i++)
				a[i] = new SortableComplexNumber(Math.random()*10, Math.random()*10);

			System.out.println("Array: ");
		//	for(int i = 0; i < a.length; i++)
		//		System.out.println(a[i].magnitude());

			System.out.println("\nSorted array: ");

			long start = System.currentTimeMillis();
			Sort2.sort(a);
			long end = System.currentTimeMillis();

			long time = end - start;

		//	for(int i = 0; i < a.length; i++)
		//		System.out.println(a[i].magnitude());

			System.out.println("\n" + time);
		}

		public static int sign(double x) {
			if(x > 0) return 1;
			else if( x < 0) return -1;
			else return 0;
		}
	}

}