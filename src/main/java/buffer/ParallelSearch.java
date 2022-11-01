package buffer;

import multithreading.SimpleBlockingQueue;

public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);

        final Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Thread.currentThread().interrupt();
                    }
                }
        );

        final Thread consumer = new Thread(
                () -> {
                    while (producer.isAlive()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Thread.currentThread().interrupt();

                    }
                }
        );
        consumer.start();
        producer.start();
    }
}
