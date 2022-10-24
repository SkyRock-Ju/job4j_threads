package linked;

import java.util.NoSuchElementException;

public class Node<T> {
    private final Node<T> next;
    private final T value;

    private Node (Node<T> next, T value){
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() throws NoSuchElementException{
        if (next == null){
            throw new NoSuchElementException("Next node doesn't exist");
        }
        return this.next;
    }

    public Node<T> setNext(Node<T> next) {
        return new Node<T>(next, value);
    }

    public T getValue() {
        return value;
    }

    public Node<T> setValue(T value) {
        return new Node<>(next, value);
    }
}