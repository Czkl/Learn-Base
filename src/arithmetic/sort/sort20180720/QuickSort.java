package arithmetic.sort.sort20180720;

import arithmetic.sort.ArrayUtils;

import java.util.Arrays;

public class QuickSort {

    // 快速排序

    public static void main(String[] args) {

        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 1, 8,23,25,7,345,546,23,346,4,4,2,643,234,124};

        sortMethod(arr);

        System.out.println(Arrays.toString(arr));

    }


    public static void sortMethod (int[] arr){

        int left = 0;
        int right = arr.length - 1;
        quickSort(arr,left,right);

    }

    public static void quickSort(int[] arr , int left,int right){


        if(left > right){
            return;
        }

        int startLeft = left;
        int startRight = right;
        int markIndex = left;

        while (left < right){
            // 右指针
            while(left < right && arr[right] >= arr[markIndex] ){
                right--;
            }
            // 左指针
            while(left < right && arr[left] <= arr[markIndex] ){
                left++;
            }
            if (left < right) {
                ArrayUtils.swap(arr,left,right);
            }
        }
        if (left == right){
            ArrayUtils.swap(arr,markIndex,left);
        }

        quickSort(arr,startLeft,left-1);
        quickSort(arr,left+1,startRight);
    }
}
