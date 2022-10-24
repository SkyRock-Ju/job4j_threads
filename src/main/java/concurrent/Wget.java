package concurrent;

import multithreading.Cache;

public class Wget {

    public static void main (String [] args) {

        System.out.println("Start loading ... ");
        Thread thread = new Thread(
                () -> {
                    for (int index = 0; index <= 100; index++){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print("\rLoading : " + index  + "%");
                    }
                }
        );
        thread.start();
        System.out.println();
        System.out.println("Loading completed.");
    }
}
