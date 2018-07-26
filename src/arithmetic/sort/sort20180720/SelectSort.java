package arithmetic.sort.sort20180720;

import arithmetic.sort.ArrayUtils;

import java.util.Arrays;

public class SelectSort {

    // 选择排序


    public static void main(String[] args) {

        int[] arr = new int[]{2,4,46,1,2,5,7,3,12,123};

        sortMethod2(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void sortMethod(int[] arr){

        for (int i = 0; i < arr.length /2; i++) {

            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }


    public static void sortMethod2(int[] arr){


        // 每次找出最大和最小
        int minIndex = 0;
        int maxIndex = 0;

        for (int i = 0; i < arr.length /2 ; i++) {
            minIndex = i;
            maxIndex = i;


            for (int j = i + 1; j < arr.length - i  ; j++) {
                if(arr[maxIndex] < arr[j]){ // 该数比最大值还大。肯定比最小值大
                    maxIndex = j;
                }
                if(arr[minIndex] > arr[j]){
                    minIndex = j;
                }

            }

            if(maxIndex == i){ // 如果最大值是这轮初始值，这应该想交换最大值，然后再交换最小值
                ArrayUtils.swap(arr,arr.length - i -1 ,maxIndex);
                ArrayUtils.swap(arr,i,minIndex);
            }
            else{
                ArrayUtils.swap(arr,i,minIndex);
                ArrayUtils.swap(arr,arr.length - i -1 ,maxIndex);
            }

        }
    }
}
