public class DeadLock {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        /**
         * Thread t1 acquired lock o1 and thread t2 acquired lock o2 and both are waiting for each other to
         * relinquish the locks . Thread t1 is waiting for t2 to release lock o2 and Thread t2 is waiting for t1
         * to release lock o1.* */
        Thread t1 = new Thread(()->{
            synchronized (o1){
                try {Thread.sleep(1);}
                catch (InterruptedException e) {throw new RuntimeException(e);}

                synchronized (o2){
                    System.out.println("Lock is acquired");
                }
            }
        });
        Thread t2 = new Thread(()->{
            synchronized (o1){
                try {Thread.sleep(1);}
                catch (InterruptedException e) {throw new RuntimeException(e);}

                synchronized (o2){
                    System.out.println("Lock is acquired");
                }
            }
        });
        t1.start();
        t2.start();
        System.out.println("This is main thread");
    }
}
