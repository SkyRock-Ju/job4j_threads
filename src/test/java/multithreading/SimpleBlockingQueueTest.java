package multithreading;

import org.junit.jupiter.api.Test;

import java.util.Random;

class SimpleBlockingQueueTest {

    @Test
    public void thread_sequence_test() throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>();
        Thread first = new Thread(() -> sbq.offer(randomInt()));
        Thread second = new Thread(sbq::poll);
        first.start();
        second.start();
        for (int i = 0; i < 5; i++){

            first.join();
        }
        for (int i = 0; i < 10; i++){

            second.join();
        }
        for (int i = 0; i < 5; i++){

            first.join();
        }
    }

    private int randomInt() {
        return new Random().nextInt(Integer.MAX_VALUE);
    }
}