public class RunnableThread implements Runnable{
    @Override
    public void run() {
        for(int i=1;i<=5; ++i){
            System.out.println("Inside "+Thread.currentThread().getName()+" "+i);
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new RunnableThread(), "MyThread1");
        ThreadExtend thread2 = new ThreadExtend("MyThread2");
        // Creating thread by passing lambda function
        Thread thread3 = new Thread(()->{
            for(int i=1;i<=5; ++i){
                System.out.println("Inside "+Thread.currentThread().getName()+" "+i);
            }
        },"MyThread3");
        System.out.println("Starting the main thread");
        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println("Ending the main thread");
    }
}
