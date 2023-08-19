public class ThreadExtend extends Thread{
    ThreadExtend(String s){
        super(s);
    }
    @Override
    public void run(){
        for(int i =1; i<=5; i++) {
            System.out.println("Inside " + ThreadExtend.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        ThreadExtend thread1 = new ThreadExtend("Thread1");
        ThreadExtend thread2 = new ThreadExtend("Thread2");
        /**
         * thread1.run();
         * thread2.run();
         * calling direct run() method will not execute in the other thread.
         * It will execute like function call
         **/
        // If all the user has been executed then it depends on the JVM whether it will allow
        // the daemon thread to execute further.
        thread2.setDaemon(true);
        System.out.println("Starting main thread");
        thread1.start();
        thread2.start();
        System.out.println("Ending main thread");
        // Don't assume anything because of order of the result.
        // It is very much dependent of the JVM . JVM decides in which order it should be executed.
    }
}

