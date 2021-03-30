package com.gobue.blink.algo.array;

import java.util.ArrayList;
import java.util.List;


/**
 * 摩尔投票：m个人数会超过 1/(m+1)的人数
 */
public class MajorityElement {

    /**
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * <p>
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
     * <p>
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/majority-element
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //摩尔投票法，求众数
    public int majorityElement(int[] nums) {
        int maj = nums[0], idx = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == maj) {
                idx++;
            } else if (idx == 0) {
                maj = nums[i];
                idx = 1;
            } else {
                idx--;
            }
        }
        return maj;
    }

    /**
     * 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
     * <p>
     * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [3,2,3]
     * 输出: [3]
     * 示例 2:
     * <p>
     * 输入: [1,1,1,3,3,2,2,2]
     * 输出: [1,2]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/majority-element-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    //摩尔投票法升级版本
    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        int maj1 = nums[0], maj2 = nums[0];
        int idx1 = 0, idx2 = 0;
        //选举
        for (int num : nums) {
            if (maj1 == num) {
                idx1++;
                continue;
            }
            if (maj2 == num) {
                idx2++;
                continue;
            }

            if (idx1 == 0) {
                maj1 = num;
                idx1++;
                continue;
            }

            if (idx2 == 0) {
                maj2 = num;
                idx2++;
                continue;
            }

            idx1--;
            idx2--;
        }

        //计数
        idx1 = 0;
        idx2 = 0;
        for (int num : nums) {
            if (num == maj1) {
                idx1++;
            } else if (num == maj2) {
                idx2++;
            }
        }

        if (idx1 > (nums.length / 3)) {
            rst.add(maj1);
        }
        if (idx2 > (nums.length / 3)) {
            rst.add(maj2);
        }
        return rst;
    }

}
