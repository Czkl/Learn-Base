package thread.thread20180716;

public class PrintABC_2 {

    public static void main(String[] args) throws InterruptedException {


        PrintABC_2 testABC = new PrintABC_2();


    }


    class ThreadABC implements Runnable {

        private String name ;
        private Object current;
        private Object next;
        private int count  = 10;

        public ThreadABC(String name, Object current, Object next) {
            this.name = name;
            this.current = current;
            this.next = next;
        }

        @Override
        public void run() {
            try {
                while ( count >= 0){
                    synchronized (current){
                        synchronized(next){
                            System.out.println(name);

                            next.notify();
                        }
                        current.wait();
                    }
                    count --;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
