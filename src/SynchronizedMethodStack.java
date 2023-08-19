public class SynchronizedMethodStack {
    private final int [] arr;
    private int top ;
    SynchronizedMethodStack(int capacity){
        arr = new int[capacity];
        top = -1;
    }

    synchronized boolean push(int num){
        // synchronized method internally uses class object reference as a lock
        // in the case of static synchronized method it internally uses className.class as a lock
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

    synchronized int pop(){
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

    boolean isFull(){
        return top >= arr.length-1;
    }

    boolean isEmpty(){
        return top <0;
    }

    public static void main(String[] args) {
        SynchronizedMethodStack s = new SynchronizedMethodStack(5);
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
