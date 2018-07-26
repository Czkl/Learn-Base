package thread.thread20180716;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Producer_Customer {

    // 消费者-生产者

    private volatile Integer size  = 0;
    private int  maxCapacity = 20; // 最大资源数量

    // 缓冲池 buffer pool
    private Queue<Resource> resourceBuff = new ArrayBlockingQueue<Resource>(maxCapacity);


    public static void main(String[] args) {

        Producer_Customer producer_customer  = new Producer_Customer();



        new Thread(producer_customer.new Producer(),"threadp").start();
        new Thread(producer_customer.new Customer(),"threadc").start();
    }

    public void consume(){
        try {

            synchronized (resourceBuff){
                while(size < 1){
                    resourceBuff.wait();
                }
                resourceBuff.poll(); // 取出消费

                size--;
                resourceBuff.notify();

                System.out.println("消费一个还剩下："+size);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void prodeuce(){

        try {

            synchronized (resourceBuff){
                while(size >= maxCapacity){
                    resourceBuff.wait(); //
                }
                resourceBuff.add(new Resource()); // 生产放进

                size ++;
                resourceBuff.notify();
                System.out.println("生产放进一个还剩下 ：" + size);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    // 资源类
    class Resource {

        Resource(){}

    }

    // 生产者
    class Producer implements Runnable{


        @Override
        public void run() {

            for (int i = 0; i <10 ; i++) {
                prodeuce();

            }

        }
    }


    // 消费者
    class Customer implements Runnable{


        @Override
        public void run() {
            for (int i = 0; i <10 ; i++) {
                consume();
            }
        }
    }

}
