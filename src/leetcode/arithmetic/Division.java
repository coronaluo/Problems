package leetcode.arithmetic;

public class Division {
	
	public static void main(String[] args) {
		 System.out.println(new Division().divide(-1, 1));
	}
	
	public int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        long sign = ((dividend < 0) ^ (divisor < 0)) ? -1: 1; 
        long rtn = sign * divideWrapper(Math.abs((long)dividend), Math.abs((long)divisor))[0];
        System.out.println("rtn = "+rtn);
        if (rtn > Integer.MAX_VALUE || rtn < Integer.MIN_VALUE) return Integer.MAX_VALUE;
        return (int)(rtn);
    }
	
	public long[] divideWrapper(long dividend, long divisor) {
		System.out.println("dividend = "+dividend);
        if (dividend == 0) return new long[]{0, 0};
		
		long[] x = divideWrapper((dividend >> 1), divisor);
        long q = x[0], r = x[1];
        
        q = (q << 1);
        r = (r << 1);
        
        // odd
        if (dividend != ((dividend >> 1) << 1)) r++;
        if (r >= divisor) {
        	q = q+1;
        	r -= divisor;
        }
        return new long[]{q, r};
	}
}
