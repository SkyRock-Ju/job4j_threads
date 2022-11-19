package multithreading;


import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int temp;
        do {
            temp = count.get() + 1;
            count.set(temp);
        } while (!count.compareAndSet(count.get(), temp));
    }

    public int get() {
        return count.get();
    }
}
