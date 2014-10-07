public class ComplexNumber { 
	private double x, y;

	public ComplexNumber(double real, double imaginary) {
		x = real;
		y = imaginary; //-?
	}


	public double real() { return x; }

	public double imaginary() { return y; }

	public double magnitude() { return Math.sqrt(x*x + y*y); }

	public double arg() { 
		if(x != 0)	{
			if(x > 0)
				return Math.atan(y/x);
			// x < 0
			else if(y >= 0) 
				return Math.PI + Math.atan(y/x);
			else 
				return -Math.PI + Math.atan(y/x);
		}
		else if(y != 0)
			return (y > 0) ? Math.PI/2 : -Math.PI/2;
		else
			throw new IllegalArgumentException("Argument for zero is not define");	
	}

	public String toString() { return x + " + " + y + "*i" ; }

	public static ComplexNumber add(ComplexNumber a, ComplexNumber b) {
		return new ComplexNumber(a.x + b.x, a.y + b.y);
	}

	public ComplexNumber add(ComplexNumber a) {
		return new ComplexNumber(this.x + a.x, this.y + a.y);
	}

	public static ComplexNumber multiply(ComplexNumber a, ComplexNumber b) {
		return new ComplexNumber(a.x*b.x - a.y*b.y, a.x*b.y + a.y*b.x);
	}

	public ComplexNumber multiply(ComplexNumber a) {
		return new ComplexNumber(x*a.x - y*a.y, x*a.y + y*a.x);
	}
}