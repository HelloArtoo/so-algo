package com.gobue.blink.algo.list;

import java.util.Stack;

//给定一个单链表的头节点head，请判断该链表是否为回文结构。
//1）哈希表方法特别简单（笔试用）
//2）改原链表的方法就需要注意边界了（面试用）
public class Palindrome {

    public boolean isPalindrome(ListNode head) {
        Stack<Integer> s = new Stack<Integer>();
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            s.add(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }

        if(fast != null){
            slow = slow.next;
        }

        while(slow != null){
            int val = s.pop();
            if(slow.val != val){
                return false;
            }
            slow = slow.next;
        }

        return true;
    }

    //TODO 不用额外空间的方式

    public static void main(String[] args) {

    }
}
