package pool;

import multithreading.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool implements Runnable {

    private final List<Thread> threads = new LinkedList<>();
    private SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool(int numberOfTasks) throws InterruptedException {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < availableProcessors; i++) {
            tasks = new SimpleBlockingQueue<>(numberOfTasks);
            threads.add(new Thread(tasks.poll()));

        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        while (!tasks.isEmpty()) {
            threads.forEach(Thread::interrupt);
        }
    }

    @Override
    public void run() {
        while (!tasks.isEmpty()) {
            threads.forEach(Thread::run);
        }
    }
}