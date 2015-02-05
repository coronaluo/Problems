package test;

public class Solution3 {
	public static void main(String []args) {
		System.out.println((5 & (1<<2)));
		System.out.println(((13>>2) & 1));
	}
	
	public boolean exist(char[][] board, String word) {
        if (board == null || word == null || board[0] == null) return false;
        int dicHeight = board.length, dicWidth = board[0].length;
//        if (word.length() > (dicHeight*dicWidth)) return false;
        if (word == "") return ((dicHeight != 0) && (dicWidth == 0));
        
        boolean[][] visited = new boolean[dicHeight][dicWidth];
        for (int i = 0; i < dicHeight; i++) {
            for (int j = 0; j < dicWidth; j++) {
                if (dfs(board, visited, i, j, word, 0)) return true;
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, boolean[][] visited, int sx, int sy, String word, int cur) {
    	int dicHeight = board.length, dicWidth = board[0].length;
    	
        if (word.charAt(cur) != board[sx][sy] || cur >= dicHeight*dicWidth) return false;
        if (cur == word.length()-1) return (word.charAt(cur) == board[sx][sy]);
        
        boolean found = false;
        visited[sx][sy] = true;
        
        for (int i = -1; i <= 1; i++) {
            if (sx+i < 0 || sx+i >= dicHeight) continue;
            for (int j = -1; j <= 1; j++) {
                if ((sy+j < 0) || (sy+j >= dicWidth) || !((i==0)^(j==0)) || visited[sx+i][sy+j]) continue;
                if (dfs(board, visited, sx+i, sy+j, word, cur+1)) {
                    found = true;
                    break;
                }
            }
        }
        if (!found) visited[sx][sy] = false;
        return found;
    }
}
