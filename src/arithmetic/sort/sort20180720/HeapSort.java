package arithmetic.sort.sort20180720;

import arithmetic.sort.ArrayUtils;

import java.util.Arrays;

public class HeapSort {

    // 堆排序

    // 堆结构实际上是一颗 完全二叉树 ，使用数组来实现
    // 但是它还满足父结点大于（或小于）子结点特性。
    // 父结点大于子结点称为最大堆（或大顶堆，array[i]>=array[2i+1] && array[i]>=array[2i+2]，i从0开始），
    // 父结点小于子结点称为最小堆（或小顶堆，array[i]<=array[2i+1] && array[i]<=array[2i+2] ，i从0开始）

    // 每个节点都大（小）于它的两个子节点，当每个节点都大于等于它的两个子节点时，就称为大顶堆，也叫堆有序；
    // 当每个节点都小于等于它的两个子节点时，就称为小顶堆。
    public static void main(String[] args) {

        int[] arr = {20,50,20,40,70,10,80,30,60};

        sortMethod(arr);

        System.out.println(Arrays.toString(arr));
    }
    
    public static void sortMethod(int[] arr){

       heapInit(arr);

       for (int i = 0; i < arr.length; i++) {

           //将堆顶元素与尾节点交换后，长度减1，尾元素最小
           ArrayUtils.swap(arr,0,arr.length - i - 1);


           heapAdjust(arr,0,arr.length - i - 1);
       }
    }

    /**
     * 无序数组初始化为最小堆
     * @param arr
     */
    public static void heapInit(int[] arr){

        for (int i = arr.length/2 - 1; i >= 0; i--) {
            heapAdjust(arr,i,arr.length);
        }
    }

    // heap调整
    public static void heapAdjust(int[] arr,int i,int length){
        int left =0;
        int minIndex = 0;

        while ((left = i * 2+1) < length) {// 该节点是否有孩子节点
            minIndex = left;

            if(left + 1 < length){// 是否有右节点
                if(arr[left] > arr[left + 1]){
                    minIndex = left + 1;
                }
            }

            if(arr[i] > arr[minIndex]){ // 最小的孩子节点与父节点比较，小的成为新的父节点
                ArrayUtils.swap(arr,i,minIndex);
            }
            else{
                break;
            }
            i = minIndex; // 父节点交换后需要判断其在子节点是否需要交换

        }
    }
}
