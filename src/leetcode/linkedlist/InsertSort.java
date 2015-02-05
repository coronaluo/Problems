package leetcode.linkedlist;

import leetcode.util.ListNode;

public class InsertSort {
	public static void main(String[] args) {
		ListNode head = new ListNode(2);
		head.next = new ListNode(1);
		ListNode tmp = head.next;
		tmp.next = new ListNode(5);
		tmp = tmp.next;
		tmp.next = new ListNode(4);
		tmp = tmp.next;
		tmp.next = new ListNode(3);

		for (tmp = head; tmp != null; tmp = tmp.next) {
			System.out.println(tmp.val);
		}
		
		ListNode sorted = insertionSortList(head);
		
		System.out.println("-----sorted-----");
		for (tmp = sorted; tmp != null; tmp = tmp.next) {
			System.out.println(tmp.val);
		}
	}
	
	public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode shead = null;
        while (head != null) {
            ListNode cur = head;
            head = head.next;
            if (shead == null || cur.val < shead.val) {
                cur.next = shead;
                shead = cur;
            } else {
                ListNode scur = shead;
                while (scur != null) {
                    if (scur.next == null || scur.next.val > cur.val) {
                        cur.next = scur.next;
                        scur.next = cur;
                        break;
                    }
                    scur = scur.next;
                }
            }
        }
        return shead;
    }
}
