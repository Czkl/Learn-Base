package arithmetic.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ArrayUtils {

    // 该类包含一些对数组的操作



    public static  ArrayList<Integer> basicArray2Collotion(int[] arr){

        ArrayList<Integer> a = new ArrayList<Integer>();
        if(arr.length != 0){
            for (int i = 0; i < arr.length; i++) {
                a.add(arr[i]);
            }
        }
        return a;
    }


    public static void swap(int[] arr, int index , int swapIndex){
        int temp = arr[index];
        arr[index] = arr[swapIndex];
        arr[swapIndex] = temp;
    }
}
