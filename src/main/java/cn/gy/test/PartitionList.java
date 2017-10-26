package cn.gy.test;

/**
 *
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

 You should preserve the original relative order of the nodes in each of the two partitions.

 For example,
 Given 1->4->3->2->5->2 and x = 3,
 return 1->2->2->4->3->5.
 * @author yang.gao created on 2016/9/20 15:06
 * @version $Id$
 */
public class PartitionList {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public static ListNode partition(ListNode head, int x) {
        ListNode h = head;
        ListNode h1 = null,p1 = null;
        ListNode h2 = null,p2 = null;
        while (h != null){
            if(h.val < x){
                if(p1 == null){
                    p1 = h;
                    h1 = p1;
                }else{
                    p1.next = h;
                    p1 = h;
                }
            }else{
                if(p2 == null){
                    p2 = h;
                    h2 = p2;
                }else{
                    p2.next = h;
                    p2 = h;
                }
            }
            h = h.next;
        }
        if(h1 == null || h2 == null){
            return h1 == null ? h2 : h1;
        }else{
            p1.next = h2;
            p2.next = null;
            return h1;
        }
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(5,null);
        ListNode node1 = createNode(4,head);
//        ListNode node2 = createNode(3,node1);
//        ListNode node3 = createNode(2,node2);
//        ListNode node4 = createNode(5,node3);
//        ListNode node5 = createNode(2,node4);
        printList(partition(head,5));

    }

    public static void printList(ListNode head){
        System.out.println("print List");
        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static ListNode createNode(int val, ListNode head){
        ListNode node = new ListNode(val,null);
        head.next = node;
        return node;
    }


}
