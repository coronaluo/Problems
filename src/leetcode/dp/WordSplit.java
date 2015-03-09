package leetcode.dp;

import java.util.ArrayList;
import java.util.List;

public class WordSplit {
	public static void main(String[] args) {
		List<String> dict = new ArrayList<String>();
		dict.add("hash");
		dict.add("tag");
		dict.add("sucks");
		System.out.println(new WordSplit().isWordSplitable("suckshashtag",dict));
	}

//  输入：word字典，一个
//	string。输出：string是否可以由字典里面的word拼接而成
	public boolean isWordSplitable(String word, List<String> dict) {
		// f(i): whether word.substr(0, i+1)  is splitable
		// f(i) = f(k) where k <= i && dict.contains(word.substring(k, i))
		if (word == null) return (dict == null);
		int len = word.length();
		if (len == 0) return dict.contains(word);
		
		boolean[] f = new boolean[len];
		for (int i = 0; i < len; i++) {
			if (dict.contains(word.substring(0, i+1))) {
				f[i] = true;
			} else {
				for (int k = i-1; k >= 0; k--) {
					if (f[k] && dict.contains(word.substring(k+1, i+1))) {
						f[i] = true;
						break;
					} 
				}
			}
		}
		return f[len-1]; 
	}
	
	
}
