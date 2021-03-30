package com.gobue.blink.algo.list;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
 * 【要求】
 * 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
 * \
 */
public class FindLoopNode {

    public static ListNode getIntersectNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        //相交节点
        ListNode loop1 = getLoopNode(head1);
        ListNode loop2 = getLoopNode(head2);

        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }

        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }

        //第三种情况是一个有loop一个没有loop，这种情况定不相交
        return null;
    }

    /**
     * 1、快慢指针，一个快的、一个慢，肯定会相遇
     * 2、然后其中一个到头指针，两个同时走，下一次一定会在第一次入环的节点相遇（重点）
     * (这是一个数学证明：小学奥数的追击问题)
     *
     * @param head
     * @return
     */
    //找到链表第一个入环节点，如果无环，则返回null
    private static ListNode getLoopNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        ListNode cur1 = head.next; //slow
        ListNode cur2 = head.next.next; //fast
        //1、快慢指针，一个快的、一个慢，肯定会相遇
        while (cur1 != cur2) {
            //如果快指针提前走到结束，则无环
            if (cur2.next == null || cur2.next.next == null) {
                return null;
            }
            cur1 = cur1.next;
            cur2 = cur2.next.next;
        }
        //2、然后其中一个到头指针，两个同时走，下一次一定会在第一次入环的节点相遇（重点）
        cur1 = head;
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }

    /**
     * 如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
     * <p>
     * 注意如果两个链表相交了，后面一定是公共部分。 Y  字形
     * <p>
     * 方式1：用哈希表，将一个链表节点全存进去，然后遍历第二个链表，判断在set中是否存在，存在就是相交节点
     * 方式2：长链表先走n个节点后开始同步走，一定在相交的地方相遇（长短对齐的方式）
     * 1、寻找差值 x
     * 2、长的链表先走 x步
     * 3、两个链表同时走，相等的时候就是相交的节点
     */
    public static ListNode noLoop(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        //1、寻找差值 x
        ListNode cur1 = head1, cur2 = head2;
        int n = 0;
        //走到最后一个节点
        while (cur1.next != null) {
            cur1 = cur1.next;
            n++;
        }

        //走到最后一个节点
        while (cur2.next != null) {
            cur2 = cur2.next;
            n--; //可为正，可为负
        }

        if (cur1 != cur2) {//没有公共部分则不相交
            return null;
        }

        //2、长的链表先走 x步
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n > 0) {
            n--;
            cur1 = cur1.next;
        }

        //3、两个链表同时走，相等的时候就是相交的节点
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }

    /**
     * 两个有环链表，返回相交的第一个节点，否则返回null
     *
     * <p>
     * （一个链表有环，一个链表无环是不会相交的，只有两个链表都有环的情况
     * <p>
     * 如果两个有环链表相交，他们一定是共用环的）
     * <p>
     * 三种情况1、各自成环不相交，2、相交同一个节点，3、相交不同的两个节点
     *
     * @param head1
     * @param head2
     * @param loop1 head1的入环节点
     * @param loop2 head2的入环节点
     * @return
     */
    public static ListNode bothLoop(ListNode head1, ListNode loop1, ListNode loop2, ListNode head2) {
        ListNode cur1 = null, cur2 = null;

        //如果入环是同一个节点，找到相交的节点，参考单链表相交
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }

            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }

            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n > 0) {
                n--;
                cur1 = cur1.next;
            }

            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }

            return cur1;
        } else { //入环节点不是一个的话，就是两个大辫子的形状（都是第一个相交的节点，loop1或loop2）
            cur1 = loop1.next;
            while (cur1 != loop1) { //绕一圈，碰到loop2
                if (cur1 == loop2) {
                    return cur1;
                }
            }

            //走了一圈都没碰上，则两个人没缘分
            return null;
        }
    }


    public static void main(String[] args) {

    }
}
