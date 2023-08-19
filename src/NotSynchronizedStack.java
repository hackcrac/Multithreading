public class NotSynchronizedStack {
    private final int [] arr;
    private int top;
    NotSynchronizedStack(int capacity){
        arr = new int[capacity];
        top = -1;
    }

    boolean push(int num){
        if(isFull()) {
            return false;
        }
        ++top;
        System.out.println("{push1} index: "+top);
        // wrong read occur here if push() increase index to 0, then sleeps.
        // then after sleep thread2 starts executing it decrements the value to -1 then it will sleeps.
        // after control again goes push() it tries access index -1 which will create arrayIndexOutOfBoundException.
        try{Thread.sleep(1000);} catch (Exception e){}
        arr[top] = num;
        return true;
    }

    int pop(){
        if(isEmpty()){
            return Integer.MIN_VALUE;
        }
        int value = arr[top];
        arr[top]=Integer.MIN_VALUE;
        System.out.println("{pop} index: "+top);
        // If they are not synchronized ,it will create arrayIndexOutBoundException
        --top;
        try{Thread.sleep(1000);} catch (Exception e){}
        return value;
    }

    boolean isFull(){
        return top >= arr.length-1;
    }

    boolean isEmpty(){
        return top < 0;
    }

    public static void main(String[] args) {
        //If they are not synchronized it will create runtime error.
        NotSynchronizedStack s = new NotSynchronizedStack(5);
        Thread t1 = new Thread(()->{
            int counter = 0;
            while(++counter <=10){
                System.out.println("pushed :"+ s.push(100));
            }
            },"Thread1");
        Thread t2 = new Thread(()->{
            int counter = 0;
            while(++counter <=10){
                System.out.println("popped :"+s.pop());
            }
            },"Thread1");
        t1.start();
        t2.start();
    }
}
