package leetcode.array;

import java.util.LinkedList;
import java.util.Queue;

public class Read4 {
	// API: int read4(char[] buf)
	// http://www.codeshare.io/k8n7k
	public int Read(char[]buf, int n) {
		int len = 0;
		while (len < n) {
			int readLen = loadCache();
			for (int i = 0; i < readLen; i++) {
				buf[len++] = cache.remove();
				if (len == n) return len;
			}
			if (readLen < 4) break; // end of file
		}
		return len;
	}
	
	private final Queue<Character> cache = new LinkedList<Character>();
	private int loadCache() {
		if (cache.size() == 0) {
			char[] tmp = new char[4];
			int readLen = read4(tmp);
			for (int i = 0; i < readLen; i++) {
				cache.add(tmp[i]);
			}
		}
		return cache.size();
	}
	
	public int read4(char[] buf) {
		return -1;
	}
}
