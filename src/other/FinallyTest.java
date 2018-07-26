package other;

public class FinallyTest {

    // 关于finally 是否一定会执行


    public static void main(String[] args) throws Exception {

        try {

            System.out.println("测试Finally一定会执行吗？");

//          事实上finally块在一种情况下不会被执行：JVM被退出。虚拟机都被推出了，什么都不会执行了。
//            System.exit(0);
//            return;
            int d = 2/0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {

            System.out.println("这里不被执行");
        }
    }
}
