package leetcode.array;

public class RotateImage {
	public static void main(String[] args) {
		int[][] m = new int[][]{{1,2,3,4},
								{5,6,7,8},
								{9,10,11,12},
								{13,14,15,16}
								};
		
		for (int i = 0; i <  m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				System.out.print(m[i][j]+", ");		
			}
			System.out.println();
		}
		
	}
	public void rotate(int[][] matrix) {
        if (matrix == null) return ;
        
        int n = matrix.length;
        if (n == 0 || n != matrix[0].length) return;
        
        // left <-> right
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n/2; c++) {
                int t = matrix[r][c];
                matrix[r][c] = matrix[r][n-1-c];
                matrix[r][n-1-c] = t;
            }
        }
        
        // upper left <-> lower right
        for (int sum = n-2; sum >= 0; sum--) {
            for (int r = 0; r < n-1; r++) {
                int c = sum - r;
                if (c < 0) continue;
                int t = matrix[r][c];
                matrix[r][c] = matrix[r+(n-1-sum)][c+(n-1-sum)];
                matrix[r+(n-1-sum)][c+(n-1-sum)] = t;
            }
        }
    }
}
