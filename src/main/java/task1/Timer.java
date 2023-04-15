package task1;

import java.util.Date;
import java.util.function.Consumer;

public class Timer {
    public static final int ONE_SEC = 1_000;
    private long time;

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.setInterval(Timer.ONE_SEC, t -> {
            System.out.print("Seconds from start: ");
            System.out.println(t);
        });

        timer.setInterval(Timer.ONE_SEC * 5, t -> {
            System.out.println("Минуло 5 секунд");
        });
    }

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
