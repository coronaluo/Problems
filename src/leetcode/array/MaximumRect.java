package leetcode.array;

public class MaximumRect {
	public static void main(String[] args) {
		char[][] m = new char[][]{
				{'1','0','0','1'},
				{'1','1','1','1'},
				{'1','0','1','1'},
				{'1','1','1','1'}
				};
		System.out.println(new MaximumRect().maximalRectangle(m));
	}
	
	public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        // related: largest rect in histogram
        int m = matrix.length, n = matrix[0].length;
        int[][] left = getLeft(matrix);
        int[][] right = getRight(matrix);
        int[][] height = new int[m][n];
        
        int max = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == '0') {
                    height[r][c] = 0;
                } else {
                    height[r][c] = ((r-1 < 0) ? 0 : height[r-1][c]) + 1;
                    max = Math.max((left[r][c] + right[r][c] - 1)*height[r][c], max);
                }	
            }
        }
        return max;
    }
    
    private int[][] getLeft(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] left = new int[m][n];
        for (int r = 0; r < m; r++) {
            int[] f = new int[n]; // f(j) len of the longest conseq 1s right before j
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == '0') {
                    f[c] = 0;
                    left[r][c] = n+1;
                } else {
                    f[c] = (c - 1 < 0) ? 1 : f[c-1]+1;
                    int upper = (r - 1 < 0) ? n+1 : left[r-1][c];
                    left[r][c] = Math.min(upper, f[c]);
                }
            }
        }
        return left;
    }

    private int[][] getRight(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] right = new int[m][n];
        for (int r = 0; r < m; r++) {
            int[] f = new int[n]; // f(j) len of the longest conseq 1s right after j
            for (int c = n-1; c >= 0; c--) {
                if (matrix[r][c] == '0') {
                    f[c] = 0;
                    right[r][c] = n+1;
                } else {
                    f[c] = (c + 1 == n) ? 1 : f[c+1]+1;
                    int upper = (r - 1 < 0) ? n+1 : right[r-1][c];
                    right[r][c] = Math.min(upper, f[c]);
                }
            }
        }
        return right;
    }
    
}
