package cn.gy.algro;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * <p>
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 * Created by gaoyang on 2018/1/19.
 */
public class Lee_234 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode mid = head;
        ListNode start = head;
        while (start != null) {
            if (start.next != null) {
                mid = mid.next;
                start = start.next.next;
            } else {
                mid = mid.next;
                start = null;
            }
        }
        if (mid == null) {
            return true;
        }
        //逆序
        ListNode next = mid.next;
        ListNode tmp;
        mid.next = null;
        while (next != null) {
            tmp = next.next;
            next.next = mid;
            mid = next;
            next = tmp;
        }
        //比较
        while (mid != null) {
            if (head.val != mid.val) {
                return false;
            }
            mid = mid.next;
            head = head.next;
        }
        return true;
    }

    public static ListNode buildList() {
        ListNode head = new ListNode(1);
//        ListNode node1 = new ListNode(2);
//        head.next = node1;
//        ListNode node2 = new ListNode(3);
//        node1.next = node2;
//        ListNode node3 = new ListNode(2);
//        node2.next = node3;
//        ListNode node4 = new ListNode(1);
//        node3.next = node4;
        head.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = buildList();
        System.out.println(new Lee_234().isPalindrome(head));
    }

}
