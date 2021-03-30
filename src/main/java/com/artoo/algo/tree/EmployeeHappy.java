package com.artoo.algo.tree;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * 派对的最大快乐值
 * <p>
 * 公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、
 * 没有环的多叉树。树的头节点是公司唯一的老板。除老板之外的每个员工都有唯一的直接上级。
 * 叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级。
 * <p>
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来-------------------------------------->(但是跨下级能来)
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大
 * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
 */
public class EmployeeHappy {

    public static Info process(Employee x) {
        if (x.subordinates.isEmpty()) {
            return new Info(x.happy, 0);
        }

        int yesMax = x.happy;
        int noMax = 0;
        for (Employee y : x.subordinates) {
            Info next = process(y);
            //来的话，就是当前的最大值+next不来的最大值，他来的，直接下级就不能来
            yesMax = yesMax + next.noMax;
            //不来的话，下级可来可不来，取最大值
            noMax = Math.max(next.yesMax, next.noMax);
        }

        return new Info(yesMax, noMax);
    }


    @AllArgsConstructor
    public static class Info {
        int yesMax;//来的情况下最大值
        int noMax; //不来的情况下最大快乐值
    }


    @AllArgsConstructor
    public static class Employee {
        public int happy; // 这名员工可以带来的快乐值
        List<Employee> subordinates; // 这名员工有哪些直接下级
    }

}
