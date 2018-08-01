package thread.thread20180718;

public class VolatileTest {

    public static volatile int count = 0;

    public static void increase(){
        count++;
    }

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {

                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        while(Thread.activeCount() > 2){
            System.out.println(Thread.activeCount());
            System.out.println(Thread.currentThread().getName());

            Thread.yield();
        }
        System.out.println(count);
    }

}
