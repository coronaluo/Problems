package leetcode.dfs;

import java.util.HashMap;

public class StringPatternMatch {
	// pattern match
	// pattern: a b b a
	// string redbluebluered; output: true
	// string redblueredblue: output: false
	// pattern character doesnt represent empty string

	public static void main(String[] args) {
		System.out.println(new StringPatternMatch().isMatch("abba", "redblueredblue"));
	}
	boolean isMatch(String pattern, String str) {
		if (pattern == null || str == null) return false;
		if (str.length() == 0) return (pattern.length() == 0);
		if (pattern.length() == 0) return true;

		HashMap<Character, String> hm = new HashMap<Character, String>();
		return helper(pattern, str, 0, 0, hm);
	}

	boolean helper(String p, String s, int pi, int si, HashMap<Character, String> hm) {
		if (pi == p.length() && si == s.length()) return true;
		if (pi == p.length() || si == s.length()) return false;

		char curPattern = p.charAt(pi);
		String hashed = hm.get(curPattern);
		if (hashed != null) {
			if (si+hashed.length() > s.length() || !s.substring(si, si+hashed.length()).equals(hashed)) return false;
			return helper(p, s, pi+1, si+hashed.length(), hm);
		}

		boolean matched = false;
		for (int index = si+1; index <= s.length(); index++) {
			hm.put(curPattern, s.substring(si, index));
			matched = helper(p, s, pi, si, hm);
			hm.remove(curPattern);
			if (matched) return true;
		}

		return false;
	}


}
