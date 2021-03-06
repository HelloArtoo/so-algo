package com.artoo.algo.list;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * <p>
 * 你应当保留两个分区中每个节点的初始相对位置。
 * <p>
 * 示例:
 * <p>
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 */
public class PartitionByX {

    public static ListNode partition(ListNode head, int x) {
        ListNode pre = new ListNode(0), post = new ListNode(0);
        ListNode preNode = pre, postNode = post;
        while (head != null) {
            if (head.val < x) {
                preNode.next = head;
                preNode = preNode.next;
            } else {
                postNode.next = head;
                postNode = postNode.next;
            }
            head = head.next;
        }
        postNode.next = null;
        preNode.next = post.next;
        return pre.next;
    }

    public static void main(String[] args) {
        ListNode node = ListNode.build(new int[]{1, 4, 3, 2, 5, 2});
        System.out.println(partition(node, 3));
    }
}
