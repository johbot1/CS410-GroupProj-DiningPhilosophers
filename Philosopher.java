import java.util.Random;

public class Philosopher implements Runnable {

    private final int id;
    private final Chopstick leftChopstick;
    private final Chopstick rightChopstick;
    private final Random random;
    private int eatCount = 0;

    // Volatile helps ensure changes to state are visible across threads,
    // Useful for status checks.
    private volatile boolean isFull = false;

    // State enum for better logging (mainly for logs)
    private enum State { THINKING, HUNGRY, EATING };
    private volatile State state = State.THINKING;

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            while (!isFull) {
                think();
                pickUpChopsticks(); // This is where locking happens
                eat();
                putDownChopsticks(); // Unlocking happens here
            }
            System.out.println(this + " is full and leaving the table.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
            System.out.println(this + " was interrupted.");
        }
    }

    private void think() throws InterruptedException {
        state = State.THINKING;
        System.out.println(this + " is thinking.");
        // Simulate thinking time
        Thread.sleep(random.nextInt(1000) + 500); // Sleep 0.5 to 1.5 seconds
        state = State.HUNGRY;
        System.out.println(this + " is hungry.");
    }

    private void pickUpChopsticks() throws InterruptedException {
        // *** This approach is prone to deadlock! ***
        // We will need to modify this based on Asymmetric Locking (Reverse hierarchy)
        //Basically, we need only ONE philosopher pick up the chopsticks in the Opposite order
        // first right then left

        leftChopstick.pickUp(this, "left");
        rightChopstick.pickUp(this, "right");

        // *** Alternate strategy placeholder: ***
        // if (id == 4) { // Example: Last philosopher picks up right first
        //    rightChopstick.pickUp(this, "right");
        //    leftChopstick.pickUp(this, "left");
        // } else { // Others pick up left first
        //    leftChopstick.pickUp(this, "left");
        //    rightChopstick.pickUp(this, "right");
        // }
    }

    private void eat() throws InterruptedException {
        state = State.EATING;
        System.out.println(this + " is eating. Meal #" + (++eatCount));
        // Simulate eating time
        Thread.sleep(random.nextInt(1000) + 500); // Sleep 0.5 to 1.5 seconds

        // Probably need to set a condition to stop running eventually
        if (eatCount >= 5) { // Example: Philosopher is full after 5 meals
            isFull = true;
        }
    }

    private void putDownChopsticks() {
        // Must release locks in the reverse order of acquisition *if* using nested locking
        // within a single method, but here they were acquired sequentially.
        // Crucially, always release both locks. Using try...finally in run() is essential.

        // Release order doesn't strictly matter here as they are independent locks,
        // but consistency is good. Let's release right then left.
        rightChopstick.putDown(this, "right");
        leftChopstick.putDown(this, "left");
    }


    public boolean isFull() {
        return isFull;
    }

    public int getEatCount() {
        return eatCount;
    }

    @Override
    public String toString() {
        return "Philosopher-" + id;
    }
}