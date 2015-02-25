package leetcode.linkedlist;
import leetcode.util.*;

public class Partition {
	public static void main(String[] args) {
//		ListNode head = new ListNode(2);
//		head.next = new ListNode(1);
//		ListNode tmp = head.next;
//		tmp.next = new ListNode(5);
//		tmp = tmp.next;
//		tmp.next = new ListNode(4);
//		tmp = tmp.next;
//		tmp.next = new ListNode(3);
//
//		ListNode.displayList(head);
//		ListNode.displayList(new Partition().partition(head, 3));
		
		System.out.println(Integer.parseInt("000123"));
	}
	
	public ListNode partition(ListNode head, int x) {
		int i = 1;
		
        ListNode head1 = null, tail1 = null, head2 = null, tail2 = null, cur = head;
        while (cur != null) {
        	ListNode nextNode = cur.next;
        	cur.next = null;
            if (cur.val < x) {
                if (head1 == null) {
                    head1 = cur;
                    tail1 = head1;
                } else {
                    tail1.next = cur;
                    tail1 = tail1.next;
                }
            } else {
                if (head2 == null) {
                    head2 = cur;
                    tail2 = head2;
                } else {
                    tail2.next = cur;
                    tail2 = tail2.next;
                }
            }
            cur = nextNode;
        }
        if (tail1 != null) tail1.next = head2;
        return (head1 == null) ? head2 : head1;
    }
}
