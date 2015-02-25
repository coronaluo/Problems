package leetcode.dp;

public class DistinctSubsequence {
	public static void main(String[] args) {
		String S = "adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc";
		String T = "bcddceeeebecbc";
		System.out.println(new DistinctSubsequence().numDistinct("abababab", "ab"));
	}
	// count the number of distinct subseq in s, where the subseq equals to T
    public int numDistinct(String S, String T) {
        if (S == null || T == null) return 0;
        int slen = S.length(), tlen = T.length();
        if (tlen == 0) return 1;
        if (slen == 0) return 0;
        
        int[][] f = new int[slen][tlen];
        
        for (int i = 0; i < slen; i++) {
        	for (int j = 0; j < tlen; j++) {
        		if (i < j) {
        			f[i][j] = 0;
        		} else {
        			f[i][j] = (i < 1) ? 0 : f[i-1][j];
        			if (S.charAt(i) == T.charAt(j)) {
        				f[i][j] += (j < 1) ? 1 : ((i < 1) ? 0 : f[i-1][j-1]);
        			}
        		}
        	}
        }
        return f[slen-1][tlen-1];
    }
        
    private int helper(String s, String t, int sb, int tb) {
        int slen = s.length()-sb, tlen = t.length()-tb;
        
        if (slen < tlen) return 0;
        if (tlen == 0 && slen >= 0) return 1;
        	
        System.out.println("slen="+slen+"/tlen="+tlen);
        
        int rst = 0;
        for (int i = sb; i < sb+slen; i++) {
            if (s.charAt(i) == t.charAt(tb)) {
                rst += helper(s, t, i+1, tb+1);
            }
        }
        return rst;
    }
}
