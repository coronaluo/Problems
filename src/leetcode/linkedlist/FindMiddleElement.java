package leetcode.linkedlist;
import leetcode.util.*;

public class FindMiddleElement {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		ListNode tmp = head.next;
		tmp.next = new ListNode(3);
		tmp = tmp.next;
		tmp.next = new ListNode(4);
		tmp = tmp.next;
		tmp.next = new ListNode(5);

		ListNode mid = findMiddleElement2(head);
		System.out.println("mid = " + mid.val);
		
		for (tmp = head; tmp != null; tmp = tmp.next) {
			System.out.println(tmp.val);
		}
	}
	
	// a better solution
	public static ListNode findMiddleElement2(ListNode head) {
		if (head == null) return null;
		
		ListNode slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		return slow;
	}
	
	// bad example (mins)
	public static ListNode findMiddleElement(ListNode head) {
		ListNode prehead = new ListNode(-1);
		prehead.next = head;
		ListNode mid = prehead, tmp = prehead;
		int len = 0;
		while (tmp.next != null) {
			len++;
			if (len % 2 == 0) mid = mid.next;
			tmp = tmp.next;
		}
		if (len%2 == 1) mid = mid.next;
		return mid;
	}
}
