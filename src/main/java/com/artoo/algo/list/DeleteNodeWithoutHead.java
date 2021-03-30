package com.artoo.algo.list;

/**
 * 能不能不给单链表的头节点，只给想要删除的节点，就能做到在链表上把这个点删掉？
 */
public class DeleteNodeWithoutHead {

    //抖机灵的做法：当前node被下个节点覆盖，然后指向下下个节点(但是有问题，无法删除最后一个节点，无法适应各种场景比如服务器下线)
    public static ListNode deleteNode(ListNode targetNode) {
        //抖机灵的做法：当前node被下个节点覆盖，然后指向下下个节点(但是有问题，无法删除最后一个节点，无法适应各种场景比如服务器下线)


        //所以可以这么走，但是要说出问题，1、内存地址没变 2、无法删除尾节点 3、

        return null;
    }
}
