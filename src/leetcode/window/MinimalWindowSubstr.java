package leetcode.window;

import java.util.HashMap;

public class MinimalWindowSubstr {
	public static void main(String [] args) {
		System.out.println(new MinimalWindowSubstr().minWindow("bba", "ab"));
	}
	
	public String minWindow(String S, String T) {
        if (S == null || T == null || S.length() < T.length()) return "";

        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for (int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            if (!hm.containsKey(c)) hm.put(c, 0);
            int curCount = hm.get(c);
            hm.put(c, curCount+1);
        }
        
        int left = 0, right = 0, minWindowSize = Integer.MAX_VALUE, minWindowLeft = 0;
        int counter = T.length();
        while (right < S.length()) {
            char c = S.charAt(right);
            if (hm.containsKey(c)) {
                int t = hm.get(c);
                if (t > 0) counter--;
                hm.put(c, t-1);
                
                while (left < right) {
                    char leftchar = S.charAt(left);
                    if (hm.containsKey(leftchar)) {
                        int leftcharCounter = hm.get(leftchar);
                        if (leftcharCounter + 1 > 0) break;
                        hm.put(leftchar, leftcharCounter+1);
                    }
                    left++;
                }
                
                if (counter == 0) {
                    int curWindowSize = right - left + 1;
                    if (curWindowSize < minWindowSize) {
                        minWindowSize = curWindowSize;
                        minWindowLeft = left;
                    }
                }
            }
            right++;
        }
        if (minWindowSize == Integer.MAX_VALUE) return "";
        return S.substring(minWindowLeft, minWindowLeft + minWindowSize);
    }
}
