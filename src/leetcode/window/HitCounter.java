package leetcode.window;

import java.util.Calendar;

class HitCounter {
	class ListNode {
		long hits;
		long second; // timestamp with accuracy to second
		ListNode next;

		public ListNode() {
			hits = 0;
			second = Calendar.getInstance().get(Calendar.SECOND);
			next = null;
		}
	}

	ListNode head = new ListNode(), tail = head;
	long totalHits; // total hits in the past 5 min

	public void hits() {
		long cur = Calendar.getInstance().get(Calendar.SECOND); //round down
		if (cur > tail.second) {
			tail.next = new ListNode();
			tail = tail.next;
		}
		tail.hits++;
		totalHits++;
		shiftListLeft(cur);
	}

	public long getHits() {
		long cur = Calendar.getInstance().get(Calendar.SECOND); //round down
		shiftListLeft(cur);
		return totalHits;

	}

	private void shiftListLeft(long timeInSec) {
		while (head.next != null && head.second < timeInSec - 300) {
			totalHits -= head.hits;
			head = head.next;
		}
		if (head.next == null) tail = head;
	}


}