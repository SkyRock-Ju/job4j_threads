package linked;

import java.util.NoSuchElementException;

public final class Node<T> {
    private final Node<T> next;
    private final T value;

    private Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() throws NoSuchElementException {
        return this.next;
    }

    public T getValue() {
        return value;
    }
}