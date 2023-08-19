public class Join {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for(int i=1; i<=5; ++i){

                try{Thread.sleep(1000);}
                catch (InterruptedException ex){}

                System.out.println(i);
            }
        });
        t.start();
        // making calling t.join() method, i am making main method wait for thread t, then main thread will start its execution
        try{t.join();}
        catch (InterruptedException ex){}
        System.out.println("This is main function");
    }
}
