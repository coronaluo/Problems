package leetcode.bigInteger;

import java.util.ArrayList;
import java.util.List;

public class BigInteger {
	public final static int MAX = 1000000000;
	public final static int MAX_DECIMAL_DIGITS = 9;

	private List<Integer> data =  new ArrayList<Integer>();

	public static void main(String []args) {
		try {
			BigInteger b1 = new BigInteger("123456789123456789");
			BigInteger b2 = new BigInteger("123456789123456789");
			System.out.println(b2.plus(b1).toString());
			System.out.println(b2.minus(b1).toString());
//			System.out.println(b1.compareTo(b2));
			System.out.println(b2.times(b1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println((char)('E'-('A'-'a')));
	}

	public BigInteger(String bigint) throws OutOfBoundsException {		
		parseStrInteger(bigint);
	}

	public BigInteger(int elems[]) throws OutOfBoundsException {
		for (int i = 0; i < elems.length; i++) {
			if (elems[i] >= MAX) throw new OutOfBoundsException(String.valueOf(elems[i]));
			data.add(elems[i]);
		}
	}

	private void parseStrInteger(String bigint) throws OutOfBoundsException {
		int numOfElems = bigint.length() / MAX_DECIMAL_DIGITS + 1;
		int lenOfFirstElem = bigint.length() % MAX_DECIMAL_DIGITS;
		if (lenOfFirstElem != 0) {
			data.add(Integer.parseInt(bigint.substring(0, lenOfFirstElem)));
		}
		
		for (int i = 1; i < numOfElems; i++) {
			int leftIdx = lenOfFirstElem + (i-1) * MAX_DECIMAL_DIGITS;
			int rightIdx = lenOfFirstElem + i * MAX_DECIMAL_DIGITS;
			int e = Integer.parseInt(bigint.substring(leftIdx, rightIdx));
			if (e >= MAX) throw new OutOfBoundsException(String.valueOf(e));
			data.add(e);
		}
	}
	
	public String toString() {
		String output = "";
		for (int e : data) {
			output += e;
		}
		return output;
	}

	public int getLength() {
		return data.size();
	}

	public int getElemAt(int idx) throws IndexOutOfBoundsException {
		if (idx < 0 || idx >= getLength()) {
			throw new IndexOutOfBoundsException(String.valueOf(idx));
		}
		return data.get(idx);		
	}
	
	/**
	 * 1: this > b
	 * 0: this == b
	 * -1: this < b
	 */
	public int compareTo(BigInteger b) throws IndexOutOfBoundsException {
		if (this.getLength() != b.getLength()) {
			return (this.getLength() - b.getLength());
			
		} else {
			int i = 0, size = this.getLength();
			while (i < size && this.getElemAt(i) == b.getElemAt(i)) {
				i++;
			}
			return (i == size) ? 0 : (this.getElemAt(i) - b.getElemAt(i));
		} 
	}

	public BigInteger shiftLeft(int shift) throws IndexOutOfBoundsException, NegativeDataException, OutOfBoundsException {
		if (shift < 0) throw new NegativeDataException("can not support negative shift");

		BigInteger rtn = new BigInteger(new int[shift+this.getLength()]);
		rtn.copyFrom(this, 0, this.getLength(), 0);
		return rtn;
	}

	public BigInteger shiftRight(int shift) throws IndexOutOfBoundsException, NegativeDataException, OutOfBoundsException {
		if (shift < 0) throw new NegativeDataException("can not support negative shift");

		BigInteger rtn = new BigInteger(new int[this.getLength() - shift]);
		rtn.copyFrom(this, 0, rtn.getLength(), 0);
		return rtn;
	}

	public void copyFrom(BigInteger src, int srcBegin, int len, int desBegin) throws IndexOutOfBoundsException, OutOfBoundsException {
		if (srcBegin < 0 || srcBegin >= src.getLength() || desBegin < 0 || desBegin >= getLength()
				|| len <= 0 || len > (this.getLength()-desBegin) || len > src.getLength()) {
			throw new IndexOutOfBoundsException();
		}
		
		for (int i = 0; i < len; i++) {
			data.set(i+desBegin, src.getElemAt(srcBegin + i));
		}
	}
	
	public BigInteger times(BigInteger factor) throws IndexOutOfBoundsException, NegativeDataException, OutOfBoundsException {
		int factorLen = factor.getLength();
		if (factorLen == 1) return this.timesOneElem(factor.getElemAt(0));
		if (this.getLength() == 1) return factor.timesOneElem(this.getElemAt(0));

		BigInteger higher = this.times(factor.shiftRight(1));
		BigInteger lower = this.timesOneElem(factor.getElemAt(factorLen-1));
		return higher.shiftLeft(1).plus(lower);
	}
	
	public BigInteger plus(BigInteger addition) throws IndexOutOfBoundsException, OutOfBoundsException {
		int results[] = new int[Math.max(addition.getLength(), getLength())];
		int idx1 = getLength() - 1;
		int idx2 = addition.getLength() - 1;
		int resultsIdx = results.length - 1;
		
		int carry = 0;
		while (idx1 >= 0 && idx2 >= 0) {
			int sum = carry + this.getElemAt(idx1--) + addition.getElemAt(idx2--);
			carry = sum / MAX;
			sum = sum % MAX;
			results[resultsIdx--] = sum;			
		}
		
		while (idx1 >= 0) {
			int sum = carry + this.getElemAt(idx1--);
			carry = sum / MAX;
			sum = sum % MAX;
			results[resultsIdx--] = sum;	
		}
		while (idx2 >= 0) {
			int sum = carry + addition.getElemAt(idx2--);
			carry = sum / MAX;
			sum = sum % MAX;
			results[resultsIdx--] = sum;	
		}
		
		if (carry != 0) {
			int newResults[];
			newResults = new int[results.length+1];
			newResults[0] = carry;
			System.arraycopy(results, 0, newResults, 1, results.length);
			return new BigInteger(newResults);
			
		} else {
			return new BigInteger(results);
		}
	}

	public BigInteger minus(BigInteger minuend) throws IndexOutOfBoundsException, OutOfBoundsException, NegativeDataException {
		if (this.compareTo(minuend) < 0) throw new NegativeDataException();
		int temp[] = new int[minuend.getLength()];
		for (int i = 0; i < minuend.getLength(); i++) {
			temp[i] = ~(minuend.getElemAt(i));
		}
		return this.plus(new BigInteger(temp)).plus(new BigInteger("1"));
	}
	
	private BigInteger timesOneElem(int e) throws IndexOutOfBoundsException, OutOfBoundsException {
		if (e < 0 || e > MAX) throw new OutOfBoundsException(String.valueOf(e));
		int rtn[] = new int[this.getLength()+1];
		int carry = 0, rtnIdx = rtn.length - 1;
		for (int i = this.getLength()-1; i >= 0; i--) {
			long product = (long)this.getElemAt(i) * e + carry;
			carry = (int)(product / MAX);
			product = (int)(product % MAX);
			rtn[rtnIdx--] = (int)product;
		}
		if (carry != 0) rtn[rtnIdx] = carry;
		return new BigInteger(rtn);
	}
	
	private void removeLeadingZeros() {
		if (data.size() > 1 && data.get(0) == 0) {
			data.remove(0);
		}
	}
	
	private class OutOfBoundsException extends Exception {
		public OutOfBoundsException() {};
		public OutOfBoundsException(String msg) {
			super(msg + "is too big or small");
		}
	}

	private class IndexOutOfBoundsException extends Exception {
		public IndexOutOfBoundsException() {};
		public IndexOutOfBoundsException(String msg) {
			super("idx " + msg + " is out of bounds");
		}
	}
	
	/**
	 * for now no support for negative operations
	 * @author coronaluo
	 *
	 */
	private class NegativeDataException extends Exception {
		public NegativeDataException() {
			super("do not support negative integers");
		};
		public NegativeDataException(String msg) {
			super(msg);
		}
	}
}
