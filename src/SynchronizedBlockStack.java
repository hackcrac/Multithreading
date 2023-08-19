public class SynchronizedBlockStack {
    private final int [] arr;
    private int top ;

    private final Object lock;
    SynchronizedBlockStack(int capacity){
        arr = new int[capacity];
        top = -1;
        lock = new Object();
    }

    boolean push(int num){
        synchronized (lock){
            if(isFull()) {
                System.out.println("Stack is full");
                return false;
            }
            ++top;
            // using thread sleep here to create wrong read condition for other thread .
            // but we are using synchronized block. Current thread acquiring the lock will not release
            // the lock until it executes the whole synchronized block. Therefore, wrong read condition will be avoided.
            try{Thread.sleep(1000);} catch (Exception e){}
            arr[top] = num;
            return true;
        }
    }

    int pop(){
        synchronized (lock){
            if(isEmpty()){
                System.out.println("Stack is empty");
                return Integer.MIN_VALUE;
            }
            int value = arr[top];
            arr[top]=Integer.MIN_VALUE;
            --top;
            try{Thread.sleep(1000);} catch (Exception e){}
            return value;
        }
    }

    boolean isFull(){
        return top >= arr.length-1;
    }

    boolean isEmpty(){
        return top <0;
    }

    public static void main(String[] args) {
        SynchronizedBlockStack s = new SynchronizedBlockStack(5);
        Thread t1 = new Thread(()->{
            int counter = 0;
            while(++counter <10){
                System.out.println("pushed :"+s.push(100));
            }
        },"Thread1");
        Thread t2 = new Thread(()->{
            int counter = 0;
            while(++counter<10){
                System.out.println("popped :"+s.pop());
            }
        },"Thread2");
        t1.start();
        t2.start();
    }
}
