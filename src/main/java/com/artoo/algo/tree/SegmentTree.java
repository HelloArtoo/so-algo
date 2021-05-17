package com.artoo.algo.tree;

/**
 * 线段树
 * <p>
 * 快速的区间修改、增加、查询
 */
public class SegmentTree {

    private int MAX_N;
    private int[] arr; //arr[]为原序列的信息从0开始，但在arr里是从1开始的
    private int[] sum; // sum[]模拟线段树维护区间和
    private int[] lazy; //lazy[]为累加懒惰标记
    private boolean[] update; //update[]为更新慵懒标记
    private int[] change; //change[]为更新的值

    public SegmentTree(int[] origin) {
        MAX_N = origin.length + 1;
        arr = new int[MAX_N];
        // arr[0] 不用  从1开始使用
        for (int i = 1; i < MAX_N; i++) {
            arr[i] = origin[i - 1];
        }

        // 用来支持脑补概念中，某一个范围的累加和信息
        sum = new int[MAX_N << 2];
        // 用来支持脑补概念中，某一个范围沒有往下傳遞的纍加任務
        lazy = new int[MAX_N << 2];
        // 用来支持脑补概念中，某一个范围更新任务，更新成了什么
        update = new boolean[MAX_N << 2];
        // 用来支持脑补概念中，某一个范围有没有更新操作的任务
        change = new int[MAX_N << 2];
    }

    /**
     * 在初始化阶段，先把sum数组，填好
     * 在arr[l~r]范围上，去build，1~N，
     * rt :  这个范围在sum中的下标
     *
     * @param l
     * @param r
     * @param rt
     */
    public void build(int l, int r, int rt) {
        if (l == r) {
            sum[rt] = arr[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(l, mid, rt << 1);
        build(mid + 1, r, rt << 1 | 1);
        pushUp(rt);
    }

    /**
     * 添加
     *
     * @param L  任务开始
     * @param R  任务结束
     * @param C  需要添加的值
     * @param l  表达范围开始
     * @param r  表达范围结束
     * @param rt 去哪找l，r范围上的信息
     */
    public void add(int L, int R, int C, int l, int r, int rt) {
        //范围囊括了
        if (L <= l && R >= r) {
            //节点数量 * C
            sum[rt] = C * (r - l + 1);
            lazy[rt] = C;
            return;
        }

        //------下面是没有包括的情况，所以要拆分任务到左右节点

        int mid = (l + r) >> 1;
        //每次都先pushDown，看看自己是否有懒任务需要下传
        pushDown(rt, mid - l + 1, r - mid);
        if (mid >= L) {
            add(L, R, C, l, mid, rt << 1);
        }

        if (mid < R) {
            add(L, R, C, mid + 1, r, rt << 1 | 1);
        }

        pushUp(rt);
    }

    /**
     * 修改
     *
     * @param L  任务开始
     * @param R  任务结束
     * @param C  需要添加的值
     * @param l  表达范围开始
     * @param r  表达范围结束
     * @param rt 去哪找l，r范围上的信息
     */
    public void update(int L, int R, int C, int l, int r, int rt) {
        //范围囊括了
        if (L <= l && R >= r) {
            //节点数量 * C
            sum[rt] = C * (r - l + 1);
            lazy[rt] = 0;
            update[rt] = true;
            change[rt] = C;
            return;
        }

        //------下面是没有包括的情况，所以要拆分任务到左右节点

        int mid = (l + r) >> 1;
        //每次都先pushDown，看看自己是否有懒任务需要下传
        pushDown(rt, mid - l + 1, r - mid);
        if (mid >= L) {
            update(L, R, C, l, mid, rt << 1);
        }

        if (mid < R) {
            update(L, R, C, mid + 1, r, rt << 1 | 1);
        }

        pushUp(rt);
    }

    /**
     * query
     *
     * @param L
     * @param R
     * @param l
     * @param r
     * @param rt
     */
    public long query(int L, int R, int l, int r, int rt) {
        if (L <= l && r <= R) {
            return sum[rt];
        }

        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        long ans = 0;
        if (mid >= L) {
            ans += query(L, R, l, mid, rt << 1);
        }

        if (mid < R) {
            ans += query(L, R, mid + 1, r, rt << 1 | 1);
        }
        return ans;
    }

    /**
     * @param rt
     * @param ln 左子树元素个数
     * @param rn 右子树元素个数
     */
    private void pushDown(int rt, int ln, int rn) {

        //左右update = true
        //左右change = change[rt]
        //左右sum = change[rt] * 节点数量
        //左右lazy = 0
        if (update[rt]) {
            update[rt << 1] = true;
            update[rt << 1 | 1] = true;

            change[rt << 1] = change[rt];
            change[rt << 1 | 1] = change[rt];

            sum[rt << 1] = change[rt] * ln;
            sum[rt << 1 | 1] = change[rt] * rn;

            lazy[rt << 1] = 0;
            lazy[rt << 1 | 1] = 0;

            //恢复
            update[rt] = false;
        }


        // 左右lazy+=lazy[rt]
        // 左右sum += lazy[rt] * 节点数量
        if (lazy[rt] != 0) {
            lazy[rt << 1] += lazy[rt];
            lazy[rt << 1 | 1] += lazy[rt];
            sum[rt << 1] += lazy[rt] * ln;
            sum[rt << 1 | 1] += lazy[rt] * rn;
            lazy[rt] = 0;
        }

    }

    //合并左右节点的值
    private void pushUp(int rt) {
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

}
