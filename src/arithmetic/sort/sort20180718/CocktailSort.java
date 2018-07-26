package arithmetic.sort.sort20180718;


import java.util.Arrays;

public class CocktailSort {

    // 鸡尾酒排序 ,“双向冒泡排序” 冒泡是一个单向的从小到大或者从大到小的交换排序，
    // 而鸡尾酒排序是双向的，从一端进行从小到大排序，从另一端进行从大到小排序。
    public static int[] sortMethod(int[] arr){


        boolean isSort = false;
        boolean isReverse  = false; // false 正向冒泡；true 反向冒泡

        int leftBorder = 0;
        int rightBorder = arr.length - 1;

        int lastSwapIndex = 0;

        while (leftBorder < rightBorder){

            isSort = true;
            if(!isReverse){ // 正向冒泡

                for (int i = leftBorder; i < rightBorder ; i++) {
                    if(arr[i] > arr[i + 1]){
                        int temp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = temp;

                        isSort = false;
                        lastSwapIndex = i + 1;
                    }
                }

                rightBorder = lastSwapIndex;
            }else { // 反向冒泡
                for (int i = rightBorder; i > leftBorder ; i--) {
                    if(arr[i] < arr[i - 1]){
                        int temp = arr[i];
                        arr[i] = arr[i - 1];
                        arr[i - 1] = temp;

                        isSort = false;
                        lastSwapIndex = i - 1;
                    }
                }

                leftBorder = lastSwapIndex;
            }
            if(isSort) break;

            // 控制下次是正向冒泡，还是反向冒泡
            if(isReverse == true){
                System.out.println("反向冒泡：" + Arrays.toString(arr));
                isReverse = false;
            }else {
                System.out.println("正向冒泡：" + Arrays.toString(arr));
                isReverse = true;
            }


        }
        return  arr;
    }


    public static void main(String[] args) {

        int[] arr =  { 8, 1, 4, 2, 9, 5, 3 };
//        int[] arr =  { 9, 2, 4, 6, 7, 8, 1 };


        arr = sortMethod(arr);

        System.out.println(Arrays.toString(arr));
    }
}
