package leetcode.array;

import java.util.Stack;

public class StockSpan {
	public static void main(String[] args) {
		int[] rst = new StockSpan().stockSpan(new int[]{100, 80, 60, 70, 60, 75, 85});
		for (int i : rst) System.out.println(i);
	}
	
	public int[] stockSpan(int[] stock) {
		if (stock == null || stock.length == 0) return new int[]{};
		int len = stock.length;
		int[] rst = new int[len];
		rst[0] = 1;
		
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		for (int i = 1; i < len; i++) {
			int cur = stock[i];
			int preIdx = -1;
			while (!stack.isEmpty() && cur > stock[stack.peek()]) {
				preIdx = stack.pop();
			}
			if (preIdx == -1) rst[i] = 1;
			else if (stack.isEmpty()) rst[i] = i+1;
			else rst[i] = i-preIdx+rst[preIdx];
			
			stack.push(i);
		}
		return rst;
	}
}
