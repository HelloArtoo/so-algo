package com.artoo.algo.list;

public class MiddleNode {


    public static void main(String[] args) {

    }


    //1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
    public static ListNode find1(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        ListNode s = head.next;
        ListNode f = head.next.next;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }

        return s;
    }

    //2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
    public static ListNode find2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode s = head.next;
        ListNode f = head.next;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }

        return s;
    }

    //3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
    public static ListNode find3(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null; //attention
        }

        ListNode s = head;
        ListNode f = head.next.next;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }

        return s;
    }

    //4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    public static ListNode find4(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        if (head.next.next == null) {
            return head;//attention
        }

        ListNode s = head;
        ListNode f = head.next;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }

        return s;
    }

}
