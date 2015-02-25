package leetcode.util;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
		next = null;
	}
	
	public static void displayList(ListNode head) {
		System.out.println("displaying list...");
		ListNode p = head;
		while (p != null) {
			System.out.println(p.val);
			p = p.next;
		}
	}
}
