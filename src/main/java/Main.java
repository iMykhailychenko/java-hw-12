import task1.Timer;
import task2.FizzBuzz;

public class Main {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz();
        fizzBuzz.run(20);


        Timer timer = new Timer();
        timer.setInterval(Timer.ONE_SEC, t -> {
            System.out.print("Seconds from start: ");
            System.out.println(t);
        });

        timer.setInterval(Timer.ONE_SEC * 5, t -> {
            System.out.println("Минуло 5 секунд");
        });
    }
}