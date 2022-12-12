package pool;

import model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final int availableProcessors = Runtime.getRuntime().availableProcessors();
    private final ExecutorService pool = Executors.newFixedThreadPool(availableProcessors);

    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = "Notification " + user.getUsername() + " to email " + user.getEmail() + ".";
            String body = "Add a new event to " + user.getUsername();
            send(subject, body, user.getEmail());
        });

    }

    public void send(String subject, String body, String email) {

    }
}