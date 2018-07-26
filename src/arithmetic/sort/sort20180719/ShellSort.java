package arithmetic.sort.sort20180719;

import java.util.Arrays;

public class ShellSort {

    // 希尔排序(Shell's Sort)是插入排序的一种又称“缩小增量排序”（Diminishing Increment Sort），
    // 是直接插入排序算法的一种更高效的改进版本。希尔排序是非稳定排序算法



    public static void main(String[] args) {

        int[] arr = new int[]{3,5,6,9,7,2,12,45,75,24,8,16,73,18,13,4011,8,164};

        sortMethod(arr);

        System.out.println(Arrays.toString(arr));
    }


    /**
     *
     *  初始时，假设有一个大小为 10 的无序序列。
     * （1）在第一趟排序中，我们不妨设 gap1 = N / 2 = 5，即相隔距离为 5 的元素组成一组，可以分为 5 组。
     * （2）接下来，按照直接插入排序的方法对每个组进行排序。
     *  在第二趟排序中，我们把上次的 gap 缩小一半，即 gap2 = gap1 / 2 = 2 (取整数)。这样每相隔距离为 2 的元素组成一组，可以分为 2 组。
     * （3）按照直接插入排序的方法对每个组进行排序。
     * （4）在第三趟排序中，再次把 gap 缩小一半，即gap3 = gap2 / 2 = 1。 这样相隔距离为 1 的元素组成一组，即只有一组。
     * （5）按照直接插入排序的方法对每个组进行排序。此时，排序已经结束。
     */
    public static void sortMethod(int[] arr){

        //
        int d = arr.length;

        for (int step = d/2; step > 0; step /= 2) {

            for (int i = 0; i < arr.length ; i++) {
                for (int j = i  ;  j < arr.length - step; j+=step) {
                    if(arr[j] > arr[j + step]){
                        int temp = arr[j];
                        arr[j] = arr[j + step];
                        arr[j + step] = temp;
                    }
                }
            }
        }
    }
}
