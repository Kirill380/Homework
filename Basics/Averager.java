public class Averager {
	private int n = 0;
	private double sum = 0.0,  sumOfSquares = 0.0;

	public void addDatum(double x) {
		n++;
		sum += x;
		sumOfSquares += x*x;
	}

	public double getAverage() { return sum/n; }

	public double getStandardDeviation() {
		return Math.sqrt((sumOfSquares - sum*sum/n)/n); // sigm = sqrt(M(X*X) - M(X)*M(X))
	}

	public double getNum() { return n; }
	public double getSum() { return sum; }
	public double sumOfSquares() { return sumOfSquares; }

	public static class Test {
		public static void main(String[] args) {
			Averager a = new Averager();
			for(int i = 1; i <= 1000; i++) a.addDatum(i);
			System.out.println("Average value: " + a.getAverage());
			System.out.println("StandardDeviation: " + a.getStandardDeviation());
			System.out.println("N: " + a.getNum());
			System.out.println("Sum: " + a.getSum());
			System.out.println("Sum of Squares: " + a.sumOfSquares());
		}
	}
}
