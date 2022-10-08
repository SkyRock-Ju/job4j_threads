package concurrent;

public class Wget {

    public static void main (String [] args) {

        System.out.println("Start loading ... ");
        for (int index = 0; index <= 100; index++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("\rLoading : " + index  + "%");
        }
        System.out.println();
        System.out.println("Loading completed.");
    }
}
