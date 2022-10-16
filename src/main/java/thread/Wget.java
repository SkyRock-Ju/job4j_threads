package thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidParameterException;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String filename;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
        this.filename = createFilenameFromUrl(url);
    }

    private String createFilenameFromUrl(String url) {
        int index = 0;
        for (int i = url.length() - 1; url.toCharArray()[i] != '/'; i--) {
            index = i;
        }
        return url.substring(index);
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int downloadData;
            long timeBefore = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloadData = bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (downloadData >= speed) {
                    if (System.currentTimeMillis() - timeBefore < 1000) {
                        Thread.sleep(1000 - System.currentTimeMillis() - timeBefore);
                    }
                    timeBefore = System.currentTimeMillis();
                    downloadData = 0;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (!args[0].startsWith("http")) {
            throw new InvalidParameterException("Invalid url");
        }
        if (args.length!=2){
            throw new InvalidParameterException("Correct input data as EXAMPLE: \"url speed\"");
        }
        String url = args[0];
        try {
            int speed = Integer.parseInt(args[1]);
            Thread wget = new Thread(new Wget(url, speed));
            wget.start();
            wget.join();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}


