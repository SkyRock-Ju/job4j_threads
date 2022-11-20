package multithreading;


import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int temp;
        int amount = count.get();
        do {
            temp = amount + 1;
            count.set(temp);
        } while (!count.compareAndSet(amount, temp));
    }

    public int get() {
        return count.get();
    }
}
