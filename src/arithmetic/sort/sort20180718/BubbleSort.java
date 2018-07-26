package arithmetic.sort.sort20180718;

import java.util.Arrays;

public class BubbleSort {


    public static void main(String[] args) {

        int []arr = {2,3,5,12,2,4,6,8};

//        sortMethod(arr);

//        sortMethod2(arr);

        sortMethod3(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static int[] sortMethod(int[] array){

//      最基本双循环冒泡实现
        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array.length - i -1; j++) {
                if(array[j] > array[j+1]){
                   int temp = array[j];
                   array[j]  = array[j+1];
                   array[j+1] = temp;
                }
            }
        }

        return array;
    }

    // 改进一下，每次后面的数组其实都排列好了，还是会去依次比较。
    // 如果后面没有进行交换，说明后面都已排列好。
    // 冒泡排序，外层循坏一次就确定一个数，但是有时，外层循坏几次后，后面确定数的个数超过了外层循坏次数
    // 所以，每轮循坏的时候，确定一个最后一次交换的下标，下标后面的数都是确定的。
    public static int[] sortMethod2(int[] array){

        int orderBorder = array.length - 1; // 有序边界 初始值

        int lastSwapIndex  = 0; // 每轮最后一次交换的下标
        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < orderBorder; j++) {

                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j]  = array[j+1];
                    array[j+1] = temp;

                    lastSwapIndex = j+1;
                }
            }
            orderBorder = lastSwapIndex;
        }

        return array;
    }


    // 冒泡排序，如果开始就是有序的，或者进行几次循坏后，变成有序的，但是还是继续循坏。
    // 所以该版本加了一个isSort boolean变量，判断该数组是否已经是有序的
    public static int[] sortMethod3(int[] array){

        int orderBorder = array.length - 1; // 有序边界 初始值

        int lastSwapIndex  = 0; // 每轮最后一次交换的下标

        boolean isSort = false;
        for (int i = 0; i < array.length; i++) {

            isSort = true; // 每轮开始的时候，默认为有序的
            for (int j = 0; j < orderBorder; j++) {

                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j]  = array[j+1];
                    array[j+1] = temp;

                    lastSwapIndex = j+1;

                    // 如果发生了交换，说明该数组，还不是有序的
                    isSort = false;
                }
            }
            orderBorder = lastSwapIndex;

            if (isSort){ // 有序的 就跳出循环
                break;
            }
        }
        return array;
    }
}
