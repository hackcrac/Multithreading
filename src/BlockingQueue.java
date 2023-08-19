import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {
    private final Queue<Integer> queue;
    private final int capacity;

    public BlockingQueue(int capacity){
        queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public boolean add(int element) throws InterruptedException {
        synchronized (queue){
            if(queue.size() == capacity) {
                queue.wait();
            }
            queue.add(element);
            queue.notifyAll();
            return true;
        }
    }

    public int remove() throws InterruptedException {
        synchronized (queue){
            while(queue.isEmpty()) {
                queue.wait();
            }
            int removedElement = queue.poll();
            queue.notifyAll();
            return removedElement;
        }
    }
    public static void main(String[] args) {
        BlockingQueue q = new BlockingQueue(10);
        Thread thread1 = new Thread(()->{
           int counter = 0;
           while(++counter <= 10){
               try {
                   System.out.println("Produced :"+ q.add(counter));
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        });

        Thread thread2 = new Thread(()->{
            int counter = 0;
            while(++counter <= 10){
                try {
                    System.out.println("Consumed :"+ q.remove());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread2.start();
        thread1.start();
    }
}
