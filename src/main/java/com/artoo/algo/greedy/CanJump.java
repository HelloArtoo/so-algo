package com.artoo.algo.greedy;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 * <p>
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CanJump {

    public boolean canJump(int[] nums) {
        int len = nums.length;
        if (len < 1) {
            return false;
        }

        //能走的最远距离 > len - 1则肯定能到达
        int rightMax = 0;
        //贪心算法
        for (int i = 0; i < len; i++) {
            //必须要能达到i才有资格走进判断
            if (i <= rightMax) {
                rightMax = Math.max(rightMax, i + nums[i]);
                if (rightMax >= len - 1) {
                    return true;
                }
            }
        }
        return false;
    }

}
