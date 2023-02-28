package task1;

import java.util.Date;
import java.util.function.Consumer;

public class Timer {
    public static final int ONE_SEC = 1_000;
    private long time;

    public void setInterval(int timeout, Consumer<Long> callback) {
        time = new Date().getTime();

        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(timeout);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                callback.accept((new Date().getTime() - time) / ONE_SEC);
            }
        });

        thread.start();
    }
}
