package leetcode.bigInteger;

import leetcode.array.FindFirstMissingPositive;

public class BigIntegerStr {
	public static void main (String[] args) {
		String num1 = "0";
		String num2 = "123";
		System.out.println(new BigIntegerStr().multiply(num1, num2));
	}
	
	public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) throw new RuntimeException("illegal input");
         
        int len1 = num1.length(), len2 = num2.length();
        if (len1 == 0 || len2 == 0) throw new RuntimeException("illegal input");
        
        if (len2 == 1) return timesOneDigit(num1, num2);
        if (len1 == 1) return timesOneDigit(num2, num1);
        
        String high = multiply(num1, num2.substring(0,len2-1));
        String low = multiply(num1, num2.substring(len2-1, len2));
        
        // left shift
        high += "0";
        return add(high, low);
    }
    
    private String timesOneDigit(String a, String factor) {
        if (factor.length() != 1) throw new RuntimeException("factor: "+factor+" has to be one digit number");
        
        int pa = a.length()-1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (pa >= 0) {
            int p = ctoi(a.charAt(pa--)) * ctoi(factor.charAt(0)) + carry;
            carry = p / 10;
            p = p % 10;
            sb.insert(0, String.valueOf(p));
        }
        
        if (carry != 0) {
            sb.insert(0, String.valueOf(carry));
        }
        
        return format(sb);
    }
    
    private String add(String a, String b) {
        if (a == null || b == null) return (a == null) ? b : a;
        
        int pa = a.length()-1, pb = b.length()-1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (pa >= 0 || pb >= 0) {
            int va = (pa >= 0) ? ctoi(a.charAt(pa--)) : 0;
            int vb = (pb >= 0) ? ctoi(b.charAt(pb--)) : 0;
            int sum = va + vb + carry;
            carry = sum/10;
            sum = sum%10;
            sb.insert(0, String.valueOf(sum));
        }
        
        if (carry != 0) {
            sb.insert(0, String.valueOf(carry));
        }
       
        return format(sb);
    }
    
    private String format(StringBuilder sb) {
    	int i = 0;
        while (i < sb.length() && sb.charAt(i) == '0') {
        	i++;
        }
        
        if (i == sb.length()) return "0";
        return sb.toString().substring(i);
    }
    
    private int ctoi(char c) {
        if (c < '0' && c >'9') throw new RuntimeException();
        return (int)(c-'0');
    }
    
}
