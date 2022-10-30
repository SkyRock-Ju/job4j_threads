package concurrent;

public class ThreadState {

    public static void main(String[] args) {
        Thread first = new Thread(
                () -> { }
        );
        Thread second = new Thread(
                () -> { }
        );
        System.out.println(first.getState() + " first");
        System.out.println(second.getState() + " second");
        first.start();
        second.start();
        while (!threadsAreTerminated(first, second)) {
            System.out.println(first.getState() + " first");
            System.out.println(second.getState() + " second");
        }
        System.out.println(first.getState() + " first");
        System.out.println(second.getState() + " second");
    }

    private static boolean threadsAreTerminated(Thread threadOne, Thread threadTwo) {
        return threadOne.getState().equals(Thread.State.TERMINATED)
                && threadTwo.getState().equals(Thread.State.TERMINATED);
    }
}