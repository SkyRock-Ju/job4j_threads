package pool;

import multithreading.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(5);
    private final int availableProcessors = Runtime.getRuntime().availableProcessors();

    public ThreadPool() {
        for (int i = 0; i < availableProcessors; i++) {
            Thread thread = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                tasks.poll().run();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
            thread.start();
            threads.add(thread);
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < 20; i++) {
            int taskNo = i;
            threadPool.work(() -> System.out.println(
                    Thread.currentThread().getName()
                            + ": Task " + taskNo));
        }
        threadPool.shutdown();
    }
}