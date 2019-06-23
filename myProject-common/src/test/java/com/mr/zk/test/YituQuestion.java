package com.mr.zk.test;

/**
 * @ClassName YituQuestion
 * @Author stack
 * @Version 1.0
 * @since 2019/6/21 17:02
 */
public class YituQuestion {

    /**
     * 题目描述：
     * 找出一个数组中第一个重复出现2次的元素，没有则返回空。
     * <p>
     * 例如：
     * [1,2,3,4,5,2,3,4,2]，重复2次的元素为3和4，3比4先出现，结果为3
     * <p>
     * 可以选择java或python实现，函数定义如下：
     * python：
     * def get_first_double_repetitive_item(items):
     * # 实现代码
     * return None
     * <p>
     * java:
     * public int getFirstDoubleRepetitiveItem(int[] items) {
     * // 实现代码
     * return null;
     * }
     */

    public static void main(String[] args) {
        int[] items = new int[]{1, 2, 3, 4, 5, 2, 3, 4, 2};

        int firstDoubleRepetitiveItem = getFirstDoubleRepetitiveItem(items);
        System.err.println(firstDoubleRepetitiveItem);

    }

    public static Integer getFirstDoubleRepetitiveItem(int[] items) {
        int i;
        for (i = 0; i < items.length - 1; i++) {
            int flag = 1;
            for (int j = 0; j < items.length; j++) {
                if (i == j) {
                    continue;
                }
                if (items[i] == items[j]) {
                    flag++;
                }
            }
            if (flag == 2) {
                break;
            }
        }
        System.out.println("第几个数（从0开始）i:" + i);
        if (i == items.length - 1) {
            return null;
        } else {
            return items[i];
        }
    }


}
