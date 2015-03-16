package leetcode.linkedlist;

public class RemoveNumerics {
	public static void main(String []args) {
		
		new RemoveNumerics().runTest();
		
	}
	
	class ListNode {
		char val;
		ListNode next;
		
		public ListNode(char c) {
			val = c;
			next = null;
		}
	}
	
	public void runTest() {
		ListNode head = new ListNode('1');
		char[] a = new char[]{'a', 'b', '1', '0', '5', 'c', '4', 'g'};
		ListNode p = head;
		for (int i = 0; i < a.length; i++) {
			p.next = new ListNode(a[i]);
			p = p.next;
		}
		display(head);
		head = remove(head);
		display(head);
	}
	
	public void display(ListNode head) {
		ListNode p = head;
		while (p != null) {
			System.out.println(p.val);
			p = p.next;
		}
	}
	
	public ListNode remove(ListNode node) {
		ListNode pre = null, cur = node;
		while (cur != null) {
			if (cur.val >= '0' && cur.val <= '9') {
				if (pre == null) {
					cur = cur.next;
					node = cur;
				} else {
					pre.next = cur.next;
					cur = cur.next;
				}
			} else {
				pre = cur;
				cur = cur.next;
			}
		}

		display(node);
		return node;
	}
}
