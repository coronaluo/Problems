package leetcode.linkedlist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import leetcode.util.ListNode;

public class MergeKSortedList {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(3);
		ListNode tmp = head.next;
		tmp.next = new ListNode(5);
		tmp = tmp.next;
		tmp.next = new ListNode(7);
		tmp = tmp.next;
		tmp.next = new ListNode(9);
		
		ListNode head2 = new ListNode(2);
		head2.next = new ListNode(3);
		tmp = head2.next;
		tmp.next = new ListNode(6);
		tmp = tmp.next;
		tmp.next = new ListNode(8);
		tmp = tmp.next;
		tmp.next = new ListNode(12);

		ListNode head3 = new ListNode(4);
		head3.next = new ListNode(10);
//		tmp = head3.next;
//		tmp.next = new ListNode(13);
		
		System.out.println("-----sorted list 1-----");
		for (tmp = head; tmp != null; tmp = tmp.next) {
			System.out.println(tmp.val);
		}
		
//		System.out.println("-----sorted list 2-----");
//		for (tmp = head2; tmp != null; tmp = tmp.next) {
//			System.out.println(tmp.val);
//		}
//		
//		System.out.println("-----sorted list 3-----");
//		for (tmp = head3; tmp != null; tmp = tmp.next) {
//			System.out.println(tmp.val);
//		}
//		
//		List<ListNode> lists = new ArrayList<ListNode>();
//		lists.add(head);
//		lists.add(head2);
//		lists.add(head3);
//		
//		
//		ListNode sorted = new MergeKSortedList().mergeKLists(lists);
		
		ListNode sorted = new MergeKSortedList().reverseKGroup(head, 6);
		
		System.out.println("-----sorted-----");
		for (tmp = sorted; tmp != null; tmp = tmp.next) {
			System.out.println(tmp.val);
		}
		
		System.out.println(2/-3);
		
	}
	
	public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;
        ListNode rtn = head, p = head, rtnTail = null;
         
        int counter = 0;
        while (p != null) {
            ListNode p2 = p, p2pre = p;
            while (counter < k && p2!= null) {
                counter++;
                p2pre = p2;
                p2 = p2.next;
            }
            if (p2 == null && counter < k) return rtn;
            
            p2pre.next = null;
            if (rtnTail == null) {
                rtn = reverseList(p,p2);
            } else {
                rtnTail.next = reverseList(p,p2);
            }
            rtnTail = p;
            p = p2;
            counter = 0;
        }
        
        return rtn;
        
    }
    
    private ListNode reverseList(ListNode head, ListNode newtail) {
        ListNode reversed = newtail, p = head;
        while (p != null) {
            ListNode t = p.next;
            p.next = reversed;
            reversed = p;
            p = t;
        }
        return reversed;
    }
	
	public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode p1 = head, p2 = head.next, rtn = null;
        do {
        	if (rtn == null) {
	            p1.next = p2.next;
	            p2.next = p1;
	            rtn = p2;
	            p1 = p2.next;
	            p2 = p1.next;
        	} else if (p2.next != null) {
        		ListNode tmp = p2.next.next;
        		p1.next = p2.next;
        		p2.next = tmp;
        		p1.next.next = p2;
        		p1 = p2;
        	}
        	p2 = p1.next;
        } while (p2 != null && p2.next != null);
       
        return rtn;
    }
	
	public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) return null;
        
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.size(), new ListNodeComparator());
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }
        
        ListNode rtn = null, p = null;
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            if (rtn == null) {
                rtn = cur;
                p = rtn;
            } else {
                p.next = cur;
                p = p.next;
            }
            if (cur.next != null) pq.add(cur.next);
        }
        return rtn;
    }
    
    public class ListNodeComparator implements Comparator<ListNode> {
        // @override
        public int compare(ListNode l1, ListNode l2) {
           if (l1.val > l2.val) return 1;
           if (l1.val == l2.val) return 0;
           return -1;
        }
    }

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2, rtn = null, p = null;
        
        while (p1 != null && p2 != null) {
            ListNode node = p1;
            if (p2.val < p1.val) {
                node = p2;
                p2 = p2.next;
            } else {
                p1 = p1.next;
            }
            
            if (rtn == null) {
                rtn = node;
                p = rtn;
            } else {
                p.next = node;
                p = p.next;
            }
        }
        
        if (rtn == null) return (p1 != null) ? p1 : p2;
        
        if (p1 != null) p.next = p1;
        if (p2 != null) p.next = p2;
        
        return rtn;
    }
}
