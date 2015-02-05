package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import leetcode.bigInteger.BigInteger;

public class Solution {
	public static char NON_ALPHBET = '-';
	public final static int EMPTY_NODE_VALUE = Integer.MAX_VALUE;
	 public static class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	 }
	
	 public static void main(String[] args) {
	        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
//	        
//	        Scanner sc = new Scanner(System.in);
//	        int len = sc.nextInt();
//	        String[] output = new String[len];
//	        int i = 0;
//	        while (i < len) {
//	            output[i++] = getDecentNum(sc.nextInt());
//	        }
//	        
//	        // output
//	        i = 0;
//	        while(i < len) System.out.println(output[i++]);
//		 Scanner sc = new Scanner(System.in);
//	        int size = sc.nextInt();
//	        long[] rst = new long[size];
//	        int i = 0;
//	        long uint_max = 0xffffffffl;
//	        while (i < size) {
//	            long uint = sc.nextLong();
//	            if (uint > uint_max) throw new RuntimeException();
//	            rst[i++] = uint ^ 0xffffffffl;
//	        }
//	        
//	        i = 0;
//	        while (i < size) {
//	            System.out.println(rst[i++]);
//	        }
	    }
	    
	    public static String getDecentNum(int d) {
	        if (d%3 == 0) return createNum(d,0);
	        if ((d-5 >= 0) && (d-5)%3 == 0) return createNum(d-5, 5);
	        if ((d-10 >= 0) && (d-10)%3 == 0) return createNum(d-10, 10);
	        else return "-1";
	    }
	    
	    public static String createNum(int n5, int n3) {
	        StringBuilder sb = new StringBuilder();
	        while ((n5--) > 0) sb.append("5");
	        while ((n3--) > 0) sb.append("3");
	        return sb.toString();
	    }
	    
	    
//	public static void main(String []args) {
//		Solution s = new Solution();
		// System.out.println(s.isPalindrome("0k.;r0.k;"));
//		char c ='0';
//		System.out.println((c >= '0' && c <= '9'));
//		System.out.println("##tree1##");
//		List<List<Integer>> list = s.generate(2); 
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).toString());
//		}
//		System.out.println("##tree2##");
//		List<List<Integer>> list2 = s.generate(3); 
//		for (int i = 0; i < list2.size(); i++) {
//			System.out.println(list2.get(i).toString());
//		}
//		System.out.println("##jointTree##");
//		List<List<Integer>> list3 = s.joinTrees(list, list2); 
//		for (int i = 0; i < list3.size(); i++) {
//			System.out.println(list3.get(i).toString());
//		}
		
//		System.out.println("##levelOrderTraverse##");
		// 3,9,20,#,#,15,7
//		TreeNode root = new TreeNode(3);
//		root.left = new TreeNode(9);
//		root.right = new TreeNode(20);
//		root.right.left = new TreeNode(15);
//		root.right.right = new TreeNode(7);
//		root.left.left = null;
//		root.left.right = null;
		
//		TreeNode root = new TreeNode(1);
//		root.left = new TreeNode(2);
//		root.left.left = new TreeNode(1);
//		root.right = new TreeNode(2);
//		root.right.left = new TreeNode(1);
//		List<String> tree = s.preOrderTraverse(root, true);
//		for (int i = 0; i < tree.size(); i++) {
//			System.out.println(tree.get(i));
//		}
//		tree = s.preOrderTraverse(root, false);
//		for (int i = 0; i < tree.size(); i++) {
//			System.out.println(tree.get(i));
//		}
//		System.out.println("results: "+s.isSymmetric(root));
		
//		System.out.println(s.countAndSay(5));
// ["..5.....6","....14...",".........",".....92..","5....2...",".......3.","...54....","3.....42.","...27.6.."]
//		char flags[][] = new char[][]{
//				{'.','.','5','.','.','.','.','.','6'},
//				{'.','.','.','.','1','4','.','.','.'},
//				{'.','.','.','.','.','.','.','.','.'},
//				{'.','.','.','.','.','9','2','.','.'},
//				{'5','.','.','.','.','2','.','.','.'},
//				{'6','.','.','.','.','.','.','.','.'},
//				{'7','.','.','.','.','.','.','.','.'},
//				{'8','.','.','.','.','.','.','.','.'},
//				{'9','.','.','.','.','.','.','.','.'},
//				}; 
//		System.out.println(s.isValidSudoku(flags));
		
//		System.out.println(s.strStr("ababbbbaaabbbaaa", "bbbb"));
//		System.out.println(s.atoi("12147483650"));
//	}
	
//	public int maxProduct(int[] A) {
//        if (A == null || A.length == 0) return 0;
//        
//        int max = Integer.MIN_VALUE, curProd = 1, lastProd = Integer.MIN_VALUE, lastNeg = 1;
//        boolean oddNeg = false, validCurProd = false;
//        
//        for (int i = 0; i < A.length; i++) {
//            if (A[i] < 0 && !oddNeg) {
//                oddNeg = true;
//                lastProd = curProd*A[i];
//                lastNeg = A[i];
//                validCurProd = false;
//                curProd = 1;
//                
//            } else if (A[i] < 0 && oddNeg) {
//                oddNeg = false;
//                curProd *= lastProd * A[i];
//                validCurProd = true;
//                
//            } else if (A[i] == 0) {
//            	curProd = validCurProd ? (oddNeg ? Math.max(curProd, lastProd/lastNeg) : curProd) : lastProd/lastNeg;
//                max = Math.max(0, Math.max(curProd, max));
//                oddNeg = false;
//                validCurProd = false;
//                curProd = 1;
//                
//            } else {
//        		curProd *= A[i];
//        		validCurProd = true;
//            }
//        }
//        
//        curProd = validCurProd ? (oddNeg ? Math.max(curProd, lastProd/lastNeg) : curProd) : lastProd/lastNeg;
//        
//        return Math.max(curProd, max);
//    }
	
	public int findPeakElement(int[] num) {
        if (num == null || num.length == 0) return -1;
        
        int left = 0, right = num.length-1, midIdx = 0;
        while (left <= right && left >=0 && right < num.length) {
            midIdx = (left+right)/2;
            
            if ((midIdx < 1 || num[midIdx] > num[midIdx-1]) && (midIdx >= num.length-1 || num[midIdx] > num[midIdx+1])) {
                return midIdx;
            }
            if (midIdx >= num.length-1 || num[midIdx] < num[midIdx+1]) left = midIdx+1; // increasing
            else right = midIdx-1; //decreasing
        }
        return midIdx;
    }
	
	public int reverse(int x) {
        int rtn = 0;
        while (x != 0) {
            int r = x%10;
            rtn += r;
            x = x/10;
            if (x != 0) {
            	System.out.println(rtn);
            	if (isOverflow(rtn,10)) {
            		return 0;
            	}
                rtn *= 10;
            }
        }
        
        return rtn;
    }
    
    private boolean isOverflow(int a, int b) {
        return ((a > 0 && Integer.MAX_VALUE / b < a) || (a < 0 && Integer.MIN_VALUE / b > a));
    }
	
    private boolean isValidInput(char c) {
        return (isNumeric(c) || isSymbol(c) || isWhiteSpace(c));
    }
    
    private boolean isNumeric(char c) {
        return (c >= '0' && c <= '9');
    }
    
    private boolean isSymbol(char c) {
        return (c == '+' || c == '-');
    }
    
    private boolean isWhiteSpace(char c) {
        return (Character.isWhitespace(c));
    }
    
	public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        
        int r = x, len = 0;
        while (r != 0) {
            r = r/10;
            len++;
        }
        
        int lowerIdx = 0, higherIdx = len-1;
        int higherDigit = -1, lowerDigit = -1;
        do {
            higherDigit = x/(int)Math.pow(10,higherIdx--)%10;
            lowerDigit = x/(int)Math.pow(10,lowerIdx++)%10;
            System.out.println("higherDigit="+higherDigit);
            System.out.println("lowerDigit="+lowerDigit);
        } while (lowerIdx < higherIdx && lowerDigit == higherDigit);
        
        return (lowerIdx >= higherIdx && lowerDigit == higherDigit);
        
    }
	
	// boyer moorey
    private HashMap<Character,Integer> getBMTable(String pattern) {
        if (pattern == null) return null;
        HashMap<Character,Integer> rtn = new HashMap<Character,Integer>();
        int len = pattern.length();
        for (int i = 0; i < len; i++) {
            Character cur = pattern.charAt(i);
            int value = len - 1 - i;
            if (i == (len-1)) {
            	if (!rtn.containsKey(cur)) value = len;
            	else break;
            }
            rtn.put(cur, value);
        }
        return rtn;
    }
    
    private void displayHashTable(HashMap table) {
    	// Get a set of the entries
        Set set = table.entrySet();
        // Get an iterator
        Iterator i = set.iterator();
        // Display elements
        while(i.hasNext()) {
           Map.Entry me = (Map.Entry)i.next();
           System.out.print(me.getKey() + ": ");
           System.out.println(me.getValue());
        }
        System.out.println();
    }
	public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() > haystack.length()) return -1;
        if (needle == "") {
            return (haystack == "") ? 0 : -1;
        }
        
//        //rk
//        int len = needle.length(), i = 0;
//        while ((i + len) <= haystack.length()) {
//        	String cur = haystack.substring(i, i+len);
//            if (cur.equals(needle)) return i;
//            i++;
//        }
        HashMap<Character,Integer> table = getBMTable(needle);
        displayHashTable(table);
        
        // "ababbbbaaabbbaaa", "bbbb"
        int i = needle.length()-1;
        while (i < haystack.length()) {
            int j = i, pidx = needle.length()-1;
            while (pidx >= 0 && j >=0 && haystack.charAt(j) == needle.charAt(pidx)) {
                j--;
                pidx--;
            }
            if (pidx < 0) return (j+1);
            int shift = needle.length();
            Integer value = table.get((Character)haystack.charAt(i));
            shift = (value != null) ? value : shift;
            i += shift;
        }
        return -1;
        //kmp
    }
	
	public boolean isValidSudoku(char[][] board) {
        int dim = board[0].length;
        if (dim != 9) {System.out.println(1);return false;}
        
        // no repeated element in one row
        for (int r = 0; r < dim; r++) {
            boolean flags[] = new boolean[dim]; 
            for (int c = 0; c < dim; c++) {
                if (board[r][c] == '.') continue;
                char e = board[r][c];
                int idx = (int)(e-'1');
                if (idx < 0 || idx > 8 || flags[idx]) {System.out.println("2:e="+e+"/r="+r+"/c="+c);return false;}
                flags[idx] = true;
                
                // no repeated element in a 3*3 subbox
                if (r%3 == 1 && c%3 ==1) {
                	for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if ((i == 0 && j==0) || board[r+i][c+j] == '.') continue;
                            if (board[r+i][c+j] == e) {System.out.println(3);return false;}
                        }
                    }
                }
            }
        }
        
        // no repeated element in one column
        for (int c = 0; c < dim; c++) {
            boolean flags[] = new boolean[dim]; 
            for (int r = 0; r < dim; r++) {
            	if (board[r][c] == '.') continue;
                char e = board[r][c];
                int idx = (int)(e-'1');
                if (idx < 0 || idx > 8 || flags[idx]) {System.out.println("4:e="+e+"/r="+r+"/c="+c);return false;}
                flags[idx] = true;
            }
        }
        
        return true;
    }
	
	public String countAndSay(int n) {
        if (n <= 0) return null;
        
        int outeridx = 1;
        String pre = "1";
        while (outeridx < n) {
            int count = 0, inneridx = 0;
            char curchar = pre.charAt(inneridx);
            String curstr = "";
            
            while (inneridx < pre.length()) {
                if (pre.charAt(inneridx) == curchar) {
                    count++;
                    inneridx++;
                    
                } else if (count > 0) {
                    curstr += (char)(count + '0');
                    curstr += curchar;
                    count = 0;
                    curchar = pre.charAt(inneridx);
                }
            }
            
            if (count > 0) {
            	curstr += (char)(count + '0');
                curstr += curchar;
            }
            pre = curstr;
            outeridx++;
        }
        return pre;
    }
	
	public String addBinary(String a, String b) {
       if (a == null && b == null) return null;
        else if (a == null) return b;
        else if (b == null) return a;
        
        int size = Math.max(a.length(), b.length());
        char rtn[] = new char[size];
        int idx = size - 1, carry = 0, idxa = a.length()-1, idxb = b.length()-1;
        while (idx >= 0) {
            int sum = 0;
            if (idxa >= 0 && idxb >=0) {
                sum = carry + (int)(a.charAt(idxa--)-'0') + (int)(b.charAt(idxb--)-'0');
                
            } else if (idxa >= 0) {
                sum = carry + (int)(a.charAt(idxa--)-'0');
                
            } else if (idxb >= 0) {
                sum = carry + (int)(b.charAt(idxb--)-'0');
            }
            
            carry = sum / 2;
            sum = sum % 2;
            rtn[idx--] = (char)('0'+sum);
        }
        
        String newrtn = "";
        if (carry != 0) {
            newrtn = String.valueOf(carry);
        } 
        for (int i = 0; i < rtn.length; i++) {
            newrtn += rtn[i];
        }
        return newrtn;
    }
	
 public int climbStairs(int n) {
        if (n <= 0) return 0;
        int pre = 2, prepre = 1, i = 3, sum = 0;
        while (i <= n) {
            sum = pre + prepre;
            prepre = pre;
            pre = sum;
            i++;
        }
        return sum;
    }
	 
	public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        if (root.left==null && root.right == null) return true;
        else if (root.left == null || root.right == null) return false;
        else {
            List<String> ltr = preOrderTraverse(root, true);
            List<String> rtl = preOrderTraverse(root, false);
            for (int i = 0; i < ltr.size(); i++) {
                if (!ltr.get(i).equals(rtl.get(i))) return false;
            }
            return true;
        }
    }
	
//	public List<String> levelTraverse(TreeNode root, boolean LeftToRight) {
//        List<String> rtn = new ArrayList<String>();
//        if (root == null) return rtn;
//        Queue<TreeNode> queue = new LinkedList<TreeNode>();
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            TreeNode cur = queue.remove();
//            if (cur.val == EMPTY_NODE_VALUE) {
//            	rtn.add("#");
//            	continue;
//            	
//            } else {
//            	rtn.add(String.valueOf(cur.val));
//            }
//            
//            if (LeftToRight) {
//                queue.add((cur.left != null) ? cur.left : new TreeNode(EMPTY_NODE_VALUE));
//                queue.add((cur.right != null) ? cur.right : new TreeNode(EMPTY_NODE_VALUE));
//            } else {
//                queue.add((cur.right != null) ? cur.right : new TreeNode(EMPTY_NODE_VALUE));
//                queue.add((cur.left != null) ? cur.left : new TreeNode(EMPTY_NODE_VALUE));
//            }
//        }
//        
//        return rtn;
//    }
	
	public List<String> preOrderTraverse(TreeNode root, boolean ltr) {
        List<String> rtn = new ArrayList<String>();
        if (root == null) {
        	rtn.add("#");
        	return rtn;
        }
        
        rtn.add(String.valueOf(root.val));
        
        if (root.left == null && root.right == null) {
            return rtn;
            
        } else {
            if (ltr) {
                rtn.addAll(preOrderTraverse(root.left, ltr));
                rtn.addAll(preOrderTraverse(root.right, ltr));
            } else {
                rtn.addAll(preOrderTraverse(root.right, ltr));
                rtn.addAll(preOrderTraverse(root.left, ltr));
            }
        }
        return rtn;
    }
	
	public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> rtn = new ArrayList<List<Integer>>();
        if (root == null) return rtn;
        
        // placed as the last element in list
        List<Integer> rootLevel = Arrays.asList(new Integer[]{root.val});
        
        if (root.left == null && root.right == null) {
            rtn.add(rootLevel);
            
        } else if (root.left == null) {
            rtn.add(rootLevel);
            rtn.addAll(levelOrder(root.right));
            
        } else if (root.right == null) {
            rtn.add(rootLevel);
            rtn.addAll(levelOrder(root.left));
            
        } else {
            List<List<Integer>> left = levelOrder(root.left);
            List<List<Integer>> right = levelOrder(root.right);
            rtn.add(rootLevel);
            rtn.addAll(joinTrees(left, right));
        }
        
        return rtn;
        
    }
	
	public List<List<Integer>> joinTrees(List<List<Integer>> lista, List<List<Integer>> listb) {
        List<List<Integer>> rtn = new ArrayList<List<Integer>>();
        if (lista.isEmpty() && listb.isEmpty()) return rtn;
        else if (lista.isEmpty()) return listb;
        else if (listb.isEmpty()) return lista;
        else {
            int jointListSize = (lista.size() >= listb.size()) ? lista.size() : listb.size();
            int idxa = 0, idxb = 0;
            for (int i = 0; i < jointListSize; i++) {
                List<Integer> curJoint = new ArrayList<Integer>();
                if (idxa < lista.size()) {
                    curJoint.addAll(lista.get(idxa++));
                } 
                if (idxb < listb.size()) {
                    curJoint.addAll(listb.get(idxb++));
                }
                rtn.add(curJoint);
            }
            return rtn;
        }
    }
	
	// pascal triangle
	public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> rtn = new ArrayList<List<Integer>>();
        if (numRows <= 0) return rtn;

        if (numRows == 1) {
            rtn.add(Arrays.asList(new Integer[]{1}));
            
        } else {
            List<List<Integer>> lastPT = generate(numRows-1);
            List<Integer> lastRow = lastPT.get(numRows-2);
            List<Integer> curRow = new ArrayList<Integer>();
            for (int i = 0; i < numRows; i++) {
            	int v = 1;
            	if (i > 0 && i < (numRows-1)) {
            		int left = lastRow.get(i-1);
            		int right = lastRow.get(i);
            		if (Integer.MAX_VALUE - left < right) throw new RuntimeException("Overflow");
            		v =  left + right; 
            	}
                curRow.add(v);
            }
            rtn.addAll(lastPT);
            rtn.add(curRow);
        }
        return rtn;
    }
	
	// alphanumeric palindrome
	public boolean isPalindrome(String s) {
        if (s == null) return false;
        if (s.length() == 0 || s.length() == 1) return true;
        
        int forward = 0, backward = s.length()-1;
        char f, b;
        do {
            while (forward < backward && isAlphanumeric(s.charAt(forward)) == NON_ALPHBET) {
                forward++;
            }
            f = isAlphanumeric(s.charAt(forward));
            
            System.out.println("f="+f);
            		
            while (backward > forward && isAlphanumeric(s.charAt(backward)) == NON_ALPHBET) {
                backward--;
            }
            b = isAlphanumeric(s.charAt(backward));
            System.out.println("b="+b);
            forward++;
            backward--;
        } while (f == b && forward < backward);
        return (f == b);
    }
    
    // return null if c is not an alphabet
    public char isAlphanumeric(char c) {
        if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')) return c;
        else if (c >= 'A' && c <= 'Z') return (char)(c-('A'-'a'));
        else return NON_ALPHBET;
    }
}
