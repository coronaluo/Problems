package leetcode.array;

import java.util.LinkedList;
import java.util.Queue;

public class SurroundedRegion {
	public static void main(String[] args) {
		String[] a = {"XOOOOOOOOOOOOOOOOOOO","OXOOOOXOOOOOOOOOOOXX","OOOOOOOOXOOOOOOOOOOX","OOXOOOOOOOOOOOOOOOXO","OOOOOXOOOOXOOOOOXOOX","XOOOXOOOOOXOXOXOXOXO","OOOOXOOXOOOOOXOOXOOO","XOOOXXXOXOOOOXXOXOOO","OOOOOXXXXOOOOXOOXOOO","XOOOOXOOOOOOXXOOXOOX","OOOOOOOOOOXOOXOOOXOX","OOOOXOXOOXXOOOOOXOOO","XXOOOOOXOOOOOOOOOOOO","OXOXOOOXOXOOOXOXOXOO","OOXOOOOOOOXOOOOOXOXO","XXOOOOOOOOXOXXOOOXOO","OOXOOOOOOOXOOXOXOXOO","OOOXOOOOOXXXOOXOOOXO","OOOOOOOOOOOOOOOOOOOO","XOOOOXOOOXXOOXOXOXOO"};
		char[][] board = new char[a.length][a[0].length()];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length(); j++) {
				board[i][j] = a[i].charAt(j);
			}
		}
//		char[][] board = new char[][] {
//				{'O','O','O','O'},
//				{'O','O','O','O'},
//				{'O','O','O','O'},
//				{'O','O','O','O'}
//				};
				
		new SurroundedRegion().solve(board);
		for (int i = 0; i < board.length; i++) {
			System.out.println();
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j]+" ");
			}
		}	
		
	}
	public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return ;
        int m = board.length, n = board[0].length;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 || r == m-1 || c == 0 || c == n-1) {
                    if (board[r][c] == 'O') bfs(board, r, c);
                }
            }
        }
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == 'O') board[r][c] = 'X';
                else if (board[r][c] == 'T') board[r][c] = 'O';
            }
        }
    }
    
    private void bfs(char[][] board, int r, int c) {
        int m = board.length, n = board[0].length;
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.add(new Pair(r, c));
        
        while (!queue.isEmpty()) {
            Pair cur = queue.remove();
            board[cur.x][cur.y] = 'T';
            // top
            if (cur.x-1 >= 0 && board[cur.x-1][cur.y] == 'O') {
                queue.add(new Pair(cur.x-1, cur.y));
            }
            // bottom
            if (cur.x+1 < m && board[cur.x+1][cur.y] == 'O') {
                queue.add(new Pair(cur.x+1, cur.y));
            }
            // left
            if (cur.y-1 >= 0 && board[cur.x][cur.y-1] == 'O') {
                queue.add(new Pair(cur.x, cur.y-1));
            }
            // right
            if (cur.y+1 < n && board[cur.x][cur.y+1] == 'O') {
                queue.add(new Pair(cur.x, cur.y+1));
            }
        }
    }
    
    private class Pair {
        int x, y;
        public Pair(int a, int b) {
            x = a; 
            y = b;
        }
    }
    
//	public void solve(char[][] board) {
//        if (board == null || board.length == 0 || board[0].length == 0) return ;
//        int m = board.length, n = board[0].length;
//
//        for (int r = 0; r < m; r++) {
//            for (int c = 0; c < n; c++) {
//                if (r == 0 || r == m-1 || c == 0 || c == n-1) {
//                    dfs(board, r, c);
//                }
//            }
//        }
//        
//        for (int r = 0; r < m; r++) {
//            for (int c = 0; c < n; c++) {
//                if (board[r][c] == 'O') board[r][c] = 'X';
//                else if (board[r][c] == 'T') board[r][c] = 'O';
//            }
//        }
//    }
//    
//    private void dfs(char[][] board, int r, int c) {
//    	int m = board.length, n = board[0].length;
//        if (r < 0 || r > m-1 || c < 0 || c > n-1 || board[r][c] != 'O') return ;
//        // if (r >= 0 && r < m && c >= 0 && c < n) return ;
//        board[r][c] = 'T';
//        dfs(board, r+1, c);
//        dfs(board, r-1, c);
//        dfs(board, r, c+1);
//        dfs(board, r, c-1);
//    }
}
