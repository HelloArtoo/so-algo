package com.artoo.algo.sort;

/**
 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、 2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16
 */
public class SmallerSum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);

    }

    private static int merge(int[] arr, int l, int mid, int r) {

        int[] tmp = new int[r - l + 1];
        int left = l, right = mid + 1, k = 0, sum = 0;
        while (left <= mid && right <= r) {
            if (arr[left] < arr[right]) {
                sum += (r - right + 1) * arr[left];
                tmp[k++] = arr[left++];
            } else {
                tmp[k++] = arr[right++];
            }
        }

        while (left <= mid) {
            tmp[k++] = arr[left++];
        }

        while (right <= r) {
            tmp[k++] = arr[right++];
        }

        for (int i = 0; i < tmp.length; i++) {
            arr[l + i] = tmp[i];
        }
        return sum;
    }


    public static void main(String[] args) {
        //利用归并排序的merge函数

        //分成归并的时候，左右两边分成两个左组，右组

        //只有当左组<右组的时候，记录大于左组的数量

        //l[n] = count(r) * l[n]

        //count(r)右边比l[n] 大的个数

    }
}
