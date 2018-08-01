package other;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CircularRotation {
   /* 如果字符串s中的字符循环转移任意位置之后得到一个新的字符串t,
    那么s就被称为t的回环变位（circular rotation)，
    例如ACTGACG就是TGACGAC的一个回环变位，反之亦然判定这个条件在基因组序列的研究中是很重要的，
    编写一个程序检查给定的字符串s和t是否互为回环变位
    提示：答案只需要一行用到index(),length()和字符串连接的代码*/

   public static boolean isCircularRotation(String s, String t){

       return (s.length() == t.length()) && ((s + s).indexOf(t) >= 0) ;
   }

   // 来个校招题
    /*如果一个单词通过循环右移获得的单词，我们称这些单词都为一种循环单词。 例如：picture 和 turepic
    就是属于同一种循环单词。 现在给出n个单词，需要统计这个n个单词中有多少种循环单词。 输入描述: 输入包括n+1行：
    第一行为单词个数n(1 ≤ n ≤ 50) 接下来的n行，每行一个单词word[i]，长度length(1 ≤ length ≤
            50)。由小写字母构成

    输出描述: 输出循环单词的种数

    输入例子: 5 picture turepic icturep word ordw

    输出例子： 2*/

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); //不吃回车

        String[] words = new String[n];

        int count = 0;
        while (count < n ) {
            words[count++] = in.next();
        }
        ArrayList<String> list = new ArrayList<>(); // 用来存储循环单词

        boolean flag = false;
        for (int i = 0; i < words.length; i++) {
            flag = true; // 默认认为是一个循环单词
            for (int j = 0; j < list.size(); j++) {
                if (isCircularRotation(list.get(j), words[i])) {
                    //list里已经存在
                    flag = false;
                }
            }
            if (flag) {
                list.add(words[i]);
            }
        }

        System.out.println("-----------");
        System.out.println(n);
        System.out.println(Arrays.toString(words));
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.size());
    }
}
