package com.gobue.blink.algo.tree;

/**
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
 * 例如:N=1时，打印: down N=2时，打印: down down up
 */
public class PageFolding {


    //规律就是，上面会有一条凹折，下面会有一条凸折痕
    //二叉树的中序遍历
    //其实就是一个树的中序遍历

    /**
     * @param i    第一层开始
     * @param n    折几下？
     * @param flag true为凹，false为凸
     */
    public static void printPageFolding(int i, int n, boolean flag) {
        if (i > n) {
            return;
        }

        //上面凹
        printPageFolding(i + 1, n, true);
        System.out.print(flag ? "凹 " : "凸 ");
        //下面凸
        printPageFolding(i + 1, n, false);

    }

    public static void main(String[] args) {
        //第一层是凹的
        printPageFolding(1, 3, true);
    }

}
