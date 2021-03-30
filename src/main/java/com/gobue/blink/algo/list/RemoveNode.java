package com.gobue.blink.algo.list;

public class RemoveNode {

    public static ListNode removeNode(ListNode head, int num) {
        if (head == null) {
            return null;
        }

        //处理头节点
        while (head != null) {
            if (head.val != num) {
                break;
            }
            head = head.next;
        }

        if (head == null) {
            return head;
        }

        ListNode cur = head;
        while (cur != null) {
            if (cur.val == num) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return head;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode.print(removeNode(node1, 1));

    }
}
