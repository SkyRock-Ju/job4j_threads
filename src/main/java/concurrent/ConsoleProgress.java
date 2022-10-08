package concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        int counter = 25;
        while (!Thread.currentThread().isInterrupted()) {
            while (counter > 100) {
                counter -= 100;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.print("\r Loading ... " + circle(counter));
            counter += 25;
        }
    }

    private String circle(int counter) {
        switch (counter) {
            case 0:
                return "/";
            case 25:
                return "-";
            case 50:
                return "\\";
            case 75:
                return "|";
            case 100:
                return "/";
            default:
                return "invalid case number";
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }
}
