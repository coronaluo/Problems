package algorithm.bitwise;

public class Bitwise {
	
	public static void main(String[] args) {
		Bitwise bitwise = new Bitwise();
		System.out.println(bitwise.toggleBit(0, 32));
		System.out.println(Integer.MIN_VALUE);
	}
	  // if number < 0, sign bit ?
	  // unsigned int ?
	  // assume offset is positive, from low to high
	  private int setBit(int number, int offset) {
	      if (!isLegalInput(offset)) throw new RuntimeException();
	      if (offset == 0) return number;
	      return ((1 << (offset-1)) | number);
	  }

	  private int clearBit(int number, int offset) {
	    if (!isLegalInput(offset)) throw new RuntimeException();
	    if (offset == 0) return number;
	    return (~(1 << (offset-1))) & number;
	  }
	  
	  private int toggleBit(int number, int offset) {
		  if (!isLegalInput(offset)) throw new RuntimeException();
		  if (offset == 0) return number;
		  
		  return (1 << (offset-1)) ^ number;
	  }
	  
	  private int checkBit(int number, int offset) {
	    if (!isLegalInput(offset)) throw new RuntimeException();
	    if (offset == 0) return number;
	    return (number >> (offset-1)) & 1;
	  }
		
	  private boolean isLegalInput(int offset) {
	    return (offset <= 32 && offset >=0);
	  }
	}
