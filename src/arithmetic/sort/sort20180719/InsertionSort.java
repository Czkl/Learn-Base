package arithmetic.sort.sort20180719;

import arithmetic.sort.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertionSort {

    // 直接插入排序

    public static void main(String[] args) {

        int[] arr = new int[]{8, 1, 4, 2, 9, 5, 3};

        ArrayList<Integer> arrayList =  ArrayUtils.basicArray2Collotion(arr);

        sortMethod3(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void sortMethod3(int[] arr){
        for (int i = 1 ; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j+1]; j--) {
                swap(arr,j,j+1);
            }
        }
    }

    // 交换
    public static void swap(int[]arr ,int i  ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     *
     * 初版
     * @param arr
     */
    public static void sortMethod2(int[] arr){

        if(arr.length == 0){
            return;
        }

        int insertIndex = 0;
        boolean isInsert = false;

        for (int i = 1; i < arr.length ; i++) {

            int exchangeValue = arr[i];
            insertIndex = i;

            for (int j = i - 1 ; j >= 0; j--) {
                if(exchangeValue < arr[j]){
                    arr[j+1] = arr[j];
                    insertIndex = j;
                }
            }
            arr[insertIndex] = exchangeValue;
        }

    }

    /**
     *
     * 第二版
     *
     * @param arr
     */
    public static void sortMethod(int[] arr){

        if(arr.length == 0){
            return;
        }

        int insertIndex = 0;
        boolean isInsert = false;

        for (int i = 1; i < arr.length ; i++) {
            isInsert = false;
            int exchangeValue = arr[i];
            for (int j = i - 1 ; j >= 0; j--) {
                if(exchangeValue < arr[j]){
                    insertIndex = j;
                    isInsert = true;
                }
            }

            // 插入
            if(isInsert){
                for (int j = i ; j > insertIndex; j--) {
                    arr[j] = arr[j-1];
                }
                arr[insertIndex] = exchangeValue;
            }

        }

    }



}
