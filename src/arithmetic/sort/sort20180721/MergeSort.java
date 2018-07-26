package arithmetic.sort.sort20180721;

import java.util.Arrays;

public class MergeSort {

    // 归并排序 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
    // 二个有序数列合并。这个非常简单，只要从比较二个数列的第一个数，谁小就先取谁，取了后就在对应数列中删除这个数。
    // 然后再进行比较，如果有数列为空，那直接将另一个数列的数据依次取出即可。
    public static void main(String[] args) {

        int[] arr = {8, 1, 4, 2, 9, 5, 3};

        sortMethod(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void sortMethod(int[] arr){


        int[] temp = new int[arr.length];

        mergeSort(arr,0,arr.length-1,temp);
    }

    public static void mergeSort(int[]arr ,int left,int right,int[] temp){


        if(left >= right){
            return;
        }
        int mid = (left+right)/2;
        mergeSort(arr,left,mid,temp);
        mergeSort(arr,mid+1,right,temp);
        mergeArray(arr,left,mid,right,temp);
    }


    public static void mergeArray(int[] arr,int left, int mid , int right,int[] temp){

        int i = left; // 左边数组  指针
        int j = mid + 1; // 右边数组 指针
        int k = 0;     //  临时数组指针

        while(i <= mid && j<=right){

            if(arr[i] < arr[j]){
                temp[k++] = arr[i++];
            }
            else{
                temp[k++] = arr[j++];
            }
        }

        // 将左边的数组剩下的依次加入到临时数组中
        while(i <= mid){
            temp[k++] = arr[i++];
        }

        // 右边的
        while (j <= right){
            temp[k++] = arr[j++];
        }

        k = 0;

        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[k++];
        }
    }
}
