package com.gobue.blink.algo.backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subsets {

    List<List<Integer>> rst = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> trace = new ArrayList<>();
        backtrace(nums, 0, trace);
        rst.add(new ArrayList<Integer>());
        return rst;
    }

    public void backtrace(int[] nums, int start, List<Integer> trace) {
        for (int i = start; i < nums.length; i++) {
            trace.add(nums[i]);
            rst.add(new ArrayList<>(trace));
            backtrace(nums, i + 1, trace);
            trace.remove(trace.size() - 1);
        }
        return;
    }


    // --------------------------------------------


    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> rst = f(nums, 0, nums.length - 1);
        rst.add(new ArrayList<Integer>());
        return rst;
    }

    public List<List<Integer>> f(int[] nums, int start, int end) {
        List<List<Integer>> rst = new ArrayList<>();
        List<Integer> lst = new ArrayList<>();
        if (start == end) {
            lst.add(nums[start]);
            rst.add(lst);
            return rst;
        }

        lst.add(nums[start]);
        rst.add(lst);
        List<List<Integer>> sublist = f(nums, start + 1, end);
        for (List<Integer> items : sublist) {
            rst.add(items);
            List<Integer> tmp = new ArrayList<>();
            tmp.add(nums[start]);
            tmp.addAll(items);
            rst.add(tmp);
        }
        return rst;
    }


    public static List<List<Integer>> subset3(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        process(nums, 0, new ArrayList<>(), rst);
        return rst;
    }

    public static void process(int[] nums, int start, List<Integer> path, List<List<Integer>> rst) {
        if(start == nums.length) {
            rst.add(new ArrayList<>(path));
            return;
        }

        process(nums, start + 1, path , rst);
        path.add(nums[start]);
        process(nums, start + 1, path , rst);
        path.remove(path.size() - 1);
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(subset3(nums));
    }

}
