package concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        int counter = 25;
        while (!Thread.currentThread().isInterrupted()) {
            if (counter >= 100) {
                counter = 0;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            String circle = "";
            switch (counter) {
                case 0:
                    circle = "/";
                    break;
                case 25:
                    circle = "-";
                    break;
                case 50:
                    circle = "\\";
                    break;
                case 75:
                    circle = "|";
                    break;
            }
            System.out.print("\r Loading ... " + circle);
            counter += 25;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }
}
