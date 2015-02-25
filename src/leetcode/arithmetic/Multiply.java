package leetcode.arithmetic;

public class Multiply {
	public static void main(String[] args) {
		System.out.println(new Multiply().multiplication(-134, 1111));
	}
	
	public int multiplication(int a, int b) {
		if (a == 0 || b == 0)
			return 0;
		int rst = 0;
		int sign = (a*b < 0) ? -1 : 1;
		// TODO
		// 
		while (b > 0) {
			int tmp = ((b & 1) == 1) ? a : 0;
			if ((tmp > 0 && Integer.MAX_VALUE - tmp < rst)
					|| (tmp < 0 && Integer.MIN_VALUE - tmp > rst))
				throw new RuntimeException("overflow/underflow");
			rst += tmp;

			if ((a > 0 && (Integer.MAX_VALUE >> 1) < a)
					|| (a < 0 && (Integer.MIN_VALUE >> 1) > a))
				throw new RuntimeException("overflow/underflow");

			a = a << 1;
			b = b >> 1;
		}

		return rst;
	}
}
