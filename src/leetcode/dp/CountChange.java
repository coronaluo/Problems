package leetcode.dp;

import java.util.ArrayList;
import java.util.List;

public class CountChange {
	public static void main(String[] args) {
		int[] coins = new int[]{13, 8, 1};
		int money = 24;
		List<List<Integer>> rst = new CountChange().countChange(coins, money);
		for (List<Integer> list : rst) {
			for (int i : list) {
				System.out.print(i+", ");
			}
			System.out.println();
		}
		
		System.out.println(new CountChange().optimalCountChange(coins, money));
	}
	
	public List<Integer> optimalCountChange(int[] coins, int money) {
		// f(i) = min U_k{ f(i-k) } where k belongs to coins
		List<List<Integer>> dp = new ArrayList<List<Integer>>();
		for (int i = 0; i <= money; i++) {
			if (i == 0) dp.add(new ArrayList<Integer>());
			dp.add(null);
		}
		
		for (int curmoney = 1; curmoney <= money; curmoney++) {
			List<Integer> optimal = null;
			int optimalCoin = -1;
			for (int coin : coins) {
				if (coin <= curmoney) {
					List<Integer> s = dp.get(curmoney - coin);
					if (s == null) continue;
					if (optimal == null || optimal.size() > s.size()) {
						optimal = s;
						optimalCoin = coin;
					}
				}
			}
			if (optimal == null) continue;
			dp.set(curmoney, new ArrayList<Integer>(optimal));
			dp.get(curmoney).add(optimalCoin);
		}
		
		return dp.get(money);
	}
	
	public List<List<Integer>> countChange(int[] coins, int money) {
		List<List<Integer>> rst = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		helper(coins, money, list, rst);
		return rst;
	}

	public void helper(int[] coins, int money, List<Integer> list, List<List<Integer>> rst) {
		if (money == 0) {
			rst.add(new ArrayList<Integer>(list));
			return ;
		}

		for (int i = 0; i < coins.length; i++) {
			if (money - coins[i] >= 0) {
				list.add(coins[i]);
				helper(coins, money - coins[i], list, rst);
				list.remove(list.size()-1);
			}
		}
	}
}
