package arithmetic.sort.sort20180721;

import java.util.Arrays;

public class RadixSort {

    // 基数排序


    public static void main(String[] args) {

        int[] arr = {8, 1, 4, 2, 9, 5, 3};

        int d = 1;
        sortMethod(arr,d);

        System.out.println(Arrays.toString(arr));
    }


    /**
     *
     *
     * @param arr 原数组
     * @param d   数组最大的位数
     */
    public static void sortMethod(int[] arr ,int d ){

        int[][] bucket = new int[10][10];
        int digit = 1;// 位数 1 表示个位数；10 表示十位数； 100 表示百位数；....
        int[] num = new int[10];

        while( d > 0 ){

            int k = 0;
            for (int i = 0; i < arr.length; i++) {

                int value = (arr[i] / digit) % 10;

                bucket[value][num[value]] = arr[i];
                num[value] ++;
            }
            for (int i = 0; i < 10; i++) {

                if(num[i] != 0){

                    for (int j = 0; j  < num[i]; j++) {

                        arr[k++] = bucket[i][j];
                    }
                }
                num[i] = 0; // 重置 该0~9 其中 i 的个数
            }
            d--;
            digit = digit * 10;
        }
    }
}
