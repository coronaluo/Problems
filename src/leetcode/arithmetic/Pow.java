package leetcode.arithmetic;

public class Pow {
	public static void main(String[] args) {
		 System.out.println(new Pow().pow(Double.MAX_VALUE, 3));
	}
	
	public double pow(double x, int n) {
		if (x == 1 || x == 0) return x;
		
        boolean negExp = (n < 0);
        double rtn = powWrapper(x, Math.abs(n));
        if (negExp && rtn == Double.MIN_VALUE) throw new RuntimeException("INFINITY"); 
        return (negExp ? 1/rtn : rtn);
    }
	
	// n > 0
	public double powWrapper(double x, int n) {
		if (n == 0) return 1;
		
        double r = powWrapper(x, n/2);
        if ((r > 0 && Double.MAX_VALUE/r < r) || (r < 0 && Double.MAX_VALUE/r > r)) throw new RuntimeException("overflow");
        if (n%2 == 0) return r*r;
        
        if((x > 0 && Double.MAX_VALUE/(r*r) < x) || (x < 0 && (-Double.MAX_VALUE)/(r*r) > x)) throw new RuntimeException("overflow");
        return r*r*x;
	}
}
