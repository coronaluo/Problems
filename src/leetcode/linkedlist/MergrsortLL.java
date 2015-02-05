package leetcode.linkedlist;

import leetcode.util.ListNode;

public class MergrsortLL {
	public static void main(String[] args) {
		ListNode head = new ListNode(2);
		head.next = new ListNode(1);
		ListNode tmp = head.next;
		tmp.next = new ListNode(5);
		tmp = tmp.next;
		tmp.next = new ListNode(4);
		tmp = tmp.next;
		tmp.next = new ListNode(3);

		ListNode sorted = mergesort(head);
		
		for (tmp = sorted; tmp != null; tmp = tmp.next) {
			System.out.println(tmp.val);
		}
	}
	
	public static ListNode mergesort(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode mid = FindMiddleElement.findMiddleElement(head);
		ListNode h1 = head;
		ListNode h2 = mid.next;
		mid.next = null;
		h1 = mergesort(h1);
		h2 = mergesort(h2);
		return merge(h1, h2);
	}
	
	public static ListNode merge(ListNode h1, ListNode h2) {
		if (h1 == null) return h2;
		if (h2 == null) return h1;
		
		ListNode h0 = new ListNode(-1);
		ListNode cur = h0;
		while(h1 != null && h2 != null) {
			if (h1.val < h2.val) {
				cur.next = h1;
				h1 = h1.next;
			} else {
				cur.next = h2;
				h2 = h2.next;
			}
			cur = cur.next;
		}
		
		cur.next = (h1 == null) ? h2 : h1;
		return h0.next;
	}
}
