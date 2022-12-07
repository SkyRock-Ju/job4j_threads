package multithreading;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class SimpleBlockingQueueTest {

    @Test
    public void blockingQueueTest() throws InterruptedException {
        final CopyOnWriteArrayList<String> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<String> queue = new SimpleBlockingQueue<>(15);
        Thread producer = new Thread(
                () -> {
                    try {
                        queue.offer("I");
                        queue.offer("am");
                        queue.offer("Julus");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        producer.start();

        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        ArrayList<String> arraylist = new ArrayList<>();
        arraylist.add("I");
        arraylist.add("am");
        arraylist.add("Julus");
        assertThat(buffer, is(arraylist));
    }
}