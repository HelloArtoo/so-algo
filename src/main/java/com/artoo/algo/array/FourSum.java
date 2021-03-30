package com.artoo.algo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result=new ArrayList<>();
        if(nums==null||nums.length<4){
            return result;
        }

        Arrays.sort(nums);
        List<List<Integer>> rst = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int min = nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
            if (min > target) {
                continue;
            }
            int max = nums[i] + nums[len - 1] + nums[len - 2] + nums[len - 3];
            if (max < target) {
                continue;
            }

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int l = j + 1, r = nums.length - 1;
                int nestMin = nums[i] + nums[j] + nums[l] + nums[l + 1];
                if (nestMin > target) {
                    continue;
                }
                int nestMax = nums[i] + nums[j] + nums[r] + nums[r - 1];
                if (nestMax < target) {
                    continue;
                }

                while (l < r) {
                    int total = nums[i] + nums[j] + nums[l] + nums[r];
                    if (total > target) {
                        r--;
                    } else if (total < target) {
                        l++;
                    } else {
                        rst.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        l++;
                        r--;
                    }
                }
            }
        }
        return rst;
    }


    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{-1, -5, -5, -3, 2, 5, 0, 4}, -7));

    }
}
