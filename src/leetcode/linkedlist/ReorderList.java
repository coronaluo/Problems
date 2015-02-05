package leetcode.linkedlist;

import leetcode.util.ListNode;

public class ReorderList {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		ListNode tmp = head.next;
		tmp.next = new ListNode(3);
		tmp = tmp.next;
		tmp.next = new ListNode(4);
		tmp = tmp.next;
		tmp.next = new ListNode(5);

		reorderList(head);
		for (tmp = head; tmp != null; tmp = tmp.next) {
			System.out.println(tmp.val);
		}
	}
	
	public static ListNode reverseList(ListNode head) {
		ListNode rhead = null;
		while (head != null) {
			ListNode tmp = head.next;
			head.next = rhead;
			rhead = head;
			head = tmp;
		}
		return rhead;
	}
	
	public static void reorderList(ListNode head) {
        if (head == null || head.next == null) return ;
        
        ListNode mid = findMid(head);
        ListNode tmp = mid.next;
        mid.next = null;
        ListNode h2 = reverseList(tmp);
        
        while (head != null && h2 != null) {
            ListNode tmp2 = h2.next;
            h2.next = head.next;
            head.next = h2;
            head = head.next.next;
            h2 = tmp2;
        }
    }
    
    private static ListNode findMid(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pslow = head, pfast = head;
        while (pfast.next != null && pfast.next.next != null) {
            pslow = pslow.next;
            pfast = pfast.next.next;
        }
        return pslow;
    }
    
}
