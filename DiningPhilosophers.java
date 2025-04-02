import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DiningPhilosophers{
    public static void main(String[] args) throws InterruptedException {

        int numPhilosophers = 5;
        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        Chopstick[] chopsticks = new Chopstick[numPhilosophers];

        // Create Chopsticks
        for (int i = 0; i < numPhilosophers; i++) {
            chopsticks[i] = new Chopstick(i);
        }

        // Maybe wrap this in a Try/Catch? It is angry at me for not doing that but seems to run fine
        ExecutorService executorService = Executors.newFixedThreadPool(numPhilosophers);

        System.out.println("Dining Philosophers Simulation Started...");
        System.out.println("-----------------------------------------");

        // Creates Philosophers and start their threads
        for (int i = 0; i < numPhilosophers; i++) {
            Chopstick leftChopstick = chopsticks[i];
            // The mod (%) handles wrap-around
            Chopstick rightChopstick = chopsticks[(i + 1) % numPhilosophers];

            if (i == numPhilosophers - 1) {
                // The last philosopher picks up the right chopstick first (logical swap)
                // Give them chopsticks in the order: right (chopstick 0), left (chopstick i)
                philosophers[i] = new Philosopher(i, rightChopstick, leftChopstick);
            } else {
                philosophers[i] = new Philosopher(i, leftChopstick, rightChopstick);
            }

            // Submit the philosopher's task to the thread pool
            executorService.execute(philosophers[i]);
        }

        boolean allFull;
        do {
            allFull = true;
            Thread.sleep(1000); // Check every second
            for(Philosopher p : philosophers) {
                if (!p.isFull()) {
                    allFull = false;
                    break;
                }
            }
        } while (!allFull);

        System.out.println("\nAll philosophers are full!");
        executorService.shutdown(); // Signal executor to stop accepting new tasks

        // Wait for threads to finish gracefully
        if (!executorService.awaitTermination(2000, TimeUnit.MILLISECONDS)) {
            System.err.println("Tasks did not finish in time, forcing shutdown.");
            executorService.shutdownNow();
        }

        System.out.println("-----------------------------------------");
        System.out.println("Dining Philosophers Simulation Finished.");
        // Print final eat counts
        for (Philosopher p : philosophers) {
            System.out.println(p + " ate " + p.getEatCount() + " times.");
        }
    }
}