package thread.thread20180725;

import java.util.concurrent.atomic.AtomicInteger;

public class CAS {
/*
    CAS是英文单词Compare And Swap的缩写，翻译过来就是比较并替换。

    CAS机制当中使用了3个基本操作数：内存地址V，旧的预期值A，要修改的新值B。

    更新一个变量的时候，只有当变量的预期值A和内存地址V当中的实际值相同时，才会将内存地址V对应的值修改为B。


    CAS的缺点：

    1.CPU开销较大
    在并发量比较高的情况下，如果许多线程反复尝试更新某一个变量，却又一直更新不成功，循环往复，会给CPU带来很大的压力。

    2.不能保证代码块的原子性
    CAS机制所保证的只是一个变量的原子性操作，而不能保证整个代码块的原子性。比如需要保证3个变量共同进行原子性的更新，就不得不使用Synchronized了。

    3.ABA问题
    这是CAS机制最大的问题所在。
    详细看remark .md文档
    */

    public static AtomicInteger count = new AtomicInteger();


    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                        for (int j = 0; j < 100000; j++) {
                            count.incrementAndGet();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }).run();
        }

        Thread.sleep(2000);
        System.out.println(count);
    }
}
