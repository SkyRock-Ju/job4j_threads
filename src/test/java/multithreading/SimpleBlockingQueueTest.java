package multithreading;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
                        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/testText.txt"));
                        reader.lines().forEach(
                                buffer::add
                        );
                    } catch (IOException e) {
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
        arraylist.add("Hey ladies drop it down just want to see you touch the ground");
        assertThat(buffer, is(arraylist));
    }
}