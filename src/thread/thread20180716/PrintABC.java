package thread.thread20180716;

public class PrintABC {

    /**
     *
     * 依次打印ABC10次
     */

    private volatile int S = 1;
    private Integer lock = 0;

    public static void main(String[] args) {


        PrintABC testABC = new PrintABC();


        ThreadA a = testABC.new ThreadA("threadA") ;
        ThreadB b = testABC.new ThreadB("threadB") ;
        ThreadC c = testABC.new ThreadC("threadC") ;

        a.start();
        b.start();
        c.start();


    }

    class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                try {
                    synchronized (lock){

                        while(S != 1){
                            lock.wait();
                        }
                        System.out.println("A");
                        S = 2;
                        lock.notifyAll(); // 这里必须唤醒所有的阻塞线程
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ThreadB extends Thread{


        public ThreadB(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    synchronized (lock){

                        while(S != 2){
                            lock.wait();
                        }
                        System.out.println("B");
                        S = 3;
                        lock.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ThreadC extends Thread {
        public ThreadC(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    synchronized (lock){

                        while(S != 3){
                            lock.wait();
                        }
                        System.out.println("C");
                        S = 1;
                        lock.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
