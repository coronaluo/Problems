package leetcode.dp;

import java.util.HashSet;
import java.util.Set;

public class WordBreak {
	public static void main(String[] args) {
		String str = "a";
		Set<String> dict = new HashSet<String>();
		dict.add("a");
		System.out.println(wordBreak(str, dict));
	}
	public static boolean breakable[];
	
	
	public static boolean wordBreak(String s, Set<String> dict) {
		if (s == null || dict.size() == 0) return false;
		if (s.length() == 0 && dict.contains("")) return true;
		
		breakable = new boolean[s.length()];
        return wordBreakWrapper(s, dict);
    }
	
	private static boolean wordBreakWrapper(String s, Set<String> dict) {
		for (int i = 0; i < s.length(); i++) {
			int j;
			for (j = i; j > 0; j--) {
				if (breakable[j-1] && dict.contains(s.substring(j, i+1))) {
					breakable[i] = true;
					break;
				}
			}
			if (j == 0 && dict.contains(s.substring(j, i+1))) breakable[i] = true; 
		}
		return breakable[s.length()-1];
	}
	
//	public static int[][] cache;
//	private static boolean wordBreakWrapper(String s, Set<String> dict, int startIndex) {
//		if (dict.contains(s)) {
//            return true;
//        }
//        
//        if (s.length() == 1) {
//            return dict.contains(s);
//        }
//        
//        for (int i = 1; i < s.length(); i++) {
//            String s1 = s.substring(0,i);
//            String s2 = s.substring(i,s.length());
//            
//            if (cache[startIndex][startIndex + i] == 0) {
//            	cache[startIndex][startIndex + i] = dict.contains(s1) ? 1 : -1;
//            }
//            
//            if (cache[startIndex + i][startIndex + s.length()-1] == 0) {
//            	cache[startIndex + i][startIndex + s.length()-1] = wordBreakWrapper(s2, dict, startIndex + i) ? 1 :-1;
//            }
//            
//            if (cache[startIndex][startIndex + i] == 1 && cache[startIndex + i][startIndex + s.length()-1] == 1) {
//            	return true;
//            }
//            
//        }
//        return false;
//	}
}
