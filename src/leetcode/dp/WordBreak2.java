package leetcode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak2 {

	public static long prime1 = 15487469L;
	public long prime2 = 15487457L;
	public long prime3 = 15487067L;
	public static int sigma = 256;
	public static long sigmaBuffer[];
	public static boolean breakable[];
	
	public static void main(String[] args) {
		String str = "abcd";
		Set<String> dict = new HashSet<String>();
		dict.add("a");
		dict.add("b");
		dict.add("abc");
		dict.add("cd");
		dict.add("d");
		dict.add("ab");
		dict.add("bc");
		List<String> sol = wordBreak2(str, dict);
		for (boolean b : breakable) {
			System.out.println(b);
		}
		
		System.out.println("start");
		for (String string : sol) {
			System.out.println(string);
		}
		System.out.println("end");
	}
	
	public static boolean wordBreak(String s, Set<String> dict) {
		if (s == null || dict.size() == 0) return false;
		if (s.length() == 0) {
			if (dict.contains("")) return true;
			else return false;
		}
		
		sigmaBuffer = calSigmaBuffer(s, sigma, dict);
		long prefixTBuffer[] = calPrefixTBuffer(s, prime1);
		HashSet<Long> ndict = rebuildDict(s, dict);
		
		breakable = new boolean[s.length()];
		return wordBreakWrapper(s, ndict, prefixTBuffer);
	}
	
	private static boolean wordBreakWrapper(String s, HashSet<Long> dict, long[] prefixTBuffer) {
		for (int i = 0; i < s.length(); i++) {
			int j = i;
			for ( ; j > 0; j--) {
				long key = calSubstrKey(s, j, i+1, prefixTBuffer, prime1);
				if (breakable[j-1] && dict.contains(key)) {
					breakable[i] = true;
					break;
				}
			}
			if (j == 0) {
				long key = calSubstrKey(s, 0, i+1, prefixTBuffer, prime1);
				if (dict.contains(key)) {
					breakable[i] = true;
				}
			}
		}
		return breakable[s.length()-1];
	}
	
	
	// get all solutions
	public static List<String> wordBreak2(String s, Set<String> dict) {
		if (s == null || dict.size() == 0) return new ArrayList<String>();
		if (s.length() == 0) {
			if (dict.contains("")) {
				List<String> rtn = new ArrayList<String>();
				rtn.add("");
				return rtn;
			} else {
				return new ArrayList<String>();
			}
		}
		
		sigmaBuffer = calSigmaBuffer(s, sigma, dict);
		long prefixTBuffer[] = calPrefixTBuffer(s, prime1);
		HashSet<Long> ndict = rebuildDict(s, dict);
		
		breakable = new boolean[s.length()];
		return wordBreakWrapper2(s, ndict, prefixTBuffer);
	}
	
	private static List<String> wordBreakWrapper2(String s, HashSet<Long> dict, long[] prefixTBuffer) {
		for (int i = 0; i < s.length(); i++) {
			int j = i;
			for ( ; j > 0; j--) {
				long key = calSubstrKey(s, j, i+1, prefixTBuffer, prime1);
				if (breakable[j-1] && dict.contains(key)) {
					breakable[i] = true;
					break;
				}
			}
			if (j == 0) {
				long key = calSubstrKey(s, 0, i+1, prefixTBuffer, prime1);
				if (dict.contains(key)) {
					breakable[i] = true;
				}
			}
		}
		if (breakable[s.length()-1]) {
			return getAllSolutions(s, dict, breakable, prefixTBuffer);
		} else {
			return new ArrayList<String>();
		}
		
	}
	
	private static List<String> getAllSolutions(String s, HashSet<Long> dict, boolean breakable[], long[] prefixTBuffer) {
		List<String> rtn = new ArrayList<String>();
		if (s.length() <= 1) {
			rtn.add(s);
			return rtn;
		}
		
		int i = s.length() - 2;
		for (; i >= 0 ; i--) {
			if (breakable[i]) {
				String ss = s.substring(i+1, s.length());
				long key = calSubstrKey2(breakable.length, i+1, s.length(), prefixTBuffer, prime1);
				if (dict.contains(key)) {
					List<String> previousSolutions = getAllSolutions(s.substring(0,i+1), dict, breakable, prefixTBuffer);
					if (previousSolutions != null && previousSolutions.size() != 0) {
						for (String strs : previousSolutions) {
							String nstrs = strs + " " + ss;
							rtn.add(nstrs);
						}
					}
				}
			}
		}
		long key2 = calSubstrKey2(breakable.length, i+1, s.length(), prefixTBuffer, prime1);
		if (dict.contains(key2)) {
			rtn.add(s);
		}
		return rtn;
	}
	// left inclusive, right exclusive
	// ATTENTION
	// Key != T
	// Key = T * sigma ^ length(str)
	private static long calSubstrKey(String s, int startIndex, int endIndex, long[] prefixTBuffer, long prime) {
		if (startIndex == 0) return modProduct(prefixTBuffer[endIndex-1], sigmaBuffer[s.length()-1], prime);
		// x / 256 ^ i * 256 ^ n
		// = x * 256 ^ (n - i)
		long t1 = (prefixTBuffer[endIndex-1] + prime - prefixTBuffer[startIndex-1]) % prime; 
		long t2 = sigmaBuffer[(s.length()-1) - startIndex];
		return modProduct(t1, t2, prime);
	}
	
	private static long calSubstrKey2(int slen, int startIndex, int endIndex, long[] prefixTBuffer, long prime) {
		if (startIndex == 0) return modProduct(prefixTBuffer[endIndex-1], sigmaBuffer[slen-1], prime);
		// x / 256 ^ i * 256 ^ n
		// = x * 256 ^ (n - i)
		long t1 = (prefixTBuffer[endIndex-1] + prime - prefixTBuffer[startIndex-1]) % prime; 
		long t2 = sigmaBuffer[(slen-1) - startIndex];
		return modProduct(t1, t2, prime);
	}
	
	
	private static HashSet<Long> rebuildDict(String s, Set<String> dict) {
		HashSet<Long> ndict = new HashSet<Long>();
		for (String str : dict) {
			ndict.add(calKey(str, prime1, sigmaBuffer[s.length()-1]));
		}
		return ndict;
	}
	
	private static long[] calSigmaBuffer(String s, int sigma, Set<String> dict) {
		int maxSize = s.length();
		for (String str : dict) {
			if (str.length() > maxSize)
				maxSize = str.length();
		}
		long sigmaBuffer[] = new long[maxSize];
		sigmaBuffer[0] = 1;
		for (int i = 1 ; i < maxSize; i++) {
			sigmaBuffer[i] = modProduct(sigmaBuffer[i-1], sigma, prime1);
		}
		return sigmaBuffer;
	}
			
	private static long[] calPrefixTBuffer(String s, long prime) {
		long prefixTBuffer[] = new long[s.length()];
		for (int i = 0; i < s.length(); i++) {
			int n = (int) s.charAt(i);
			long preT = 0;
			if (i > 0) preT = prefixTBuffer[i-1];
			long t1 = modProduct(n, sigmaBuffer[i], prime);
			prefixTBuffer[i] = (t1 + preT) % prime;
		}
		return prefixTBuffer;
	}
	
	// ATTENTION
	// Key != T
	// Key = T * sigma ^ length(str)
	private static long calKey(String s, long prime, long factor) {
		long sum = 0;
		for (int i = 0; i < s.length(); i++) {
			int n = (int) s.charAt(i);
			long t1 = modProduct(n, sigmaBuffer[i], prime);
			long t2 = modProduct(t1, factor, prime);
			sum = (t2 + sum) % prime;
		}
		return sum;
	}
	
	// (a*b) mod m = ((a mod m) * (b mod m)) mod m
	private static long modProduct (long a, long b, long m) {
		return (a % m) * (b % m) % m;
	}
	
	
}
