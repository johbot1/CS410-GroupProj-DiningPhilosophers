# Checkpoints - Dining Philosophers (Java)

This document outlines the checkpoints for the Dining Philosophers project

## 1) Project Core Structure and Initial Implementation

* 1.1) Create a basic project structure with necessary files.✅
    * 1.1.1) Create `Philosopher.java` for philosopher behavior.✅
    * 1.1.2) Create `Chopstick.java` for chopstick management.✅
    * 1.1.3) Create `DiningTable.java` or `Main.java` for overall program control.✅
* 1.2) Implement basic thread creation for each philosopher.✅
    * 1.2.1) Create a `Philosopher` class that extends ~~`Thread`~~ `Runnable`.✅
    * 1.2.2) Create a function in `DiningTable` or `Main` to initialize philosopher threads.✅
    * 1.2.3) Verify successful thread creation and start.✅
* 1.3) Define data structures for philosophers and chopsticks.
    * 1.3.1) Create a `Philosopher` class with necessary attributes.✅
    * 1.3.2) Create a `Chopstick` class with necessary attributes.✅
    * 1.3.3) Implement any required shared data structures (e.g., an array of chopsticks).

## 2) Chopstick Management and Basic Eating

* 2.1) Implement methods for philosophers to pick up and put down chopsticks.
    * 2.1.1) `pickupChopsticks()` method in `Philosopher.java`.
    * 2.1.2) `putdownChopsticks()` method in `Philosopher.java`.
* 2.2) Ensure mutual exclusion for chopstick access using `synchronized` blocks or `ReentrantLock`.
    * 2.2.1) Implement locking and unlocking of chopsticks within `pickupChopsticks()` and `putdownChopsticks()`.
    * 2.2.2) Choose and implement either synchronized blocks or ReentrantLocks.
* 2.3) Implement a basic eating method that simulates the philosopher eating when both chopsticks are available.
    * 2.3.1) `eat()` method in `Philosopher.java` that holds chopsticks and simulates eating.
    * 2.3.2) Add print statements to show the eating process.
* 2.4) Test the basic eating functionality with simple scenarios.
    * 2.4.1) Run the program with a small number of iterations.
    * 2.4.2) Verify that philosophers eat when both chopsticks are available.

## 3) Deadlock Prevention and Detection

* 3.1) Implement a strategy to prevent or detect deadlocks.
    * 3.1.1) Choose and implement a deadlock prevention/detection algorithm (e.g., resource hierarchy, timeout mechanisms).
    * 3.1.2) Document the chosen strategy.
* 3.2) Thoroughly test the implementation for deadlock scenarios.
    * 3.2.1) Create test cases that induce deadlock.
    * 3.2.2) Verify the deadlock prevention/detection mechanism works.
* 3.3) Add logging to observe and debug the deadlock prevention/detection mechanisms.
    * 3.3.1) Implement logging of chopstick acquisition and release.
    * 3.3.2) Implement logging of deadlock detection/prevention actions.

## 4) Starvation Prevention and Fairness

* 4.1) Implement a strategy to prevent starvation, ensuring fairness among philosophers.
    * 4.1.1) Choose and implement a starvation prevention method.
* 4.2) Test the implementation for starvation scenarios, ensuring all philosophers get to eat.
    * 4.2.1) Verify that all philosophers get to eat regularly.
* 4.3) Implement tests to measure fairness.
    * 4.3.1) Track the number of times each philosopher eats.
    * 4.3.2) Verify that the distribution of eating is relatively even.

## 5) Refactoring and Optimization

* 5.1) Refactor the code to improve readability and maintainability.
    * 5.1.1) Separate concerns into well-defined methods and classes.
    * 5.1.2) Use descriptive variable names.
    * 5.1.3) Add comments where needed.
* 5.2) Optimize the synchronization mechanisms for performance.
    * 5.2.1) Analyze the performance of the chosen synchronization mechanisms.
    * 5.2.2) Explore and implement optimizations if necessary (e.g., using `ReentrantLock` for finer control).
* 5.3) Implement robust error handling and logging.
    * 5.3.1) Add error checks and handle exceptions appropriately.
    * 5.3.2) Implement detailed logging using `java.util.logging` or `Log4j`.
* 5.4) Add comments and documentation to the code.
    * 5.4.1) Add comments to all methods.
    * 5.4.2) Document classes and their attributes.

## 6) Testing and Documentation

* 6.1) Write comprehensive unit tests for all components using JUnit or similar.
    * 6.1.1) Test `Chopstick` class methods.
    * 6.1.2) Test `Philosopher` class methods.
    * 6.1.3) Test synchronization mechanisms.
* 6.2) Perform integration testing to ensure the entire system works correctly.
    * 6.2.1) Test the system with varying numbers of philosophers.
    * 6.2.2) Test for edge cases and potential race conditions.
* 6.3) Create a `README.md` file with project description, usage instructions, and design details.
    * 6.3.1) Describe the problem and solution.
    * 6.3.2) Provide instructions for building and running the program.
    * 6.3.3) Document the design choices and synchronization mechanisms.
* 6.4) Document the project's architecture and synchronization mechanisms.
    * 6.4.1) create a design document, or add to README.md
    * 6.4.2) explain synchronization choices made, and their consequences.