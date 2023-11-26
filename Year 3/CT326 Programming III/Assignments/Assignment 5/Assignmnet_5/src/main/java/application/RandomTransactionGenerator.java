package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents a random transaction generator.
 * It implements Runnable so it can be used to create a thread that generates
 * random transactions.
 * It has a bank and a list of account numbers.
 * It has a method for generating a random transaction and submitting it to the
 * bank.
 * It then sleeps for a random amount of time between 0 and 1 second after
 * submitting the transaction.
 * It has a method for submitting a poison pill to the bank to indicate that the
 * generator is closed.
 * 
 * @author Cathal Lawlor
 */

public class RandomTransactionGenerator implements Runnable {
    Bank bank;
    private static final Logger logger = Logger.getLogger(RandomTransactionGenerator.class.getName());
    private List<Integer> accountNumbers;

    /**
     * Constructs a new RandomTransactionGenerator.
     *
     * @param bank the bank where the transactions will be submitted
     */
    public RandomTransactionGenerator(Bank bank) {
        this.bank = bank;
        this.accountNumbers = new ArrayList<>(bank.getAccountNumbers());

    }

    /**
     * Runs the random transaction generator.
     * It generates random transactions and submits them to the bank until it is
     * interrupted.
     * It sleeps for a random amount of time between 0 and 1 second after
     * submitting each transaction.
     * It submits a poison pill to the bank to indicate that the generator is
     * closed.
     */
    @Override
    public void run() {
        try {
            // While thread is not interrupted, call generateRandomTransaction() and submit
            // the transaction to the bank
            while (!Thread.currentThread().isInterrupted()) {
                generateRandomTransaction();
                sleepRandomTime();
            }
        }
        // Catch the InterruptedException
        catch (InterruptedException e) {
            // Log that the thread has been interrupted
            logger.log(Level.INFO, "RandomTransactionGenerator thread has been interrupted");
        }
        // Finally, submit the poison pill to the bank
        finally {
            submitPoisonPill();
            System.out.println("RandomTransactionGenerator thread has been terminated");
        }
    }

    /**
     * Generate a random transaction and submit it to the bank
     * Generates using ThreadLocalRandom and Math.round to round to 2 decimal
     *
     * @throws InterruptedException if the thread is interrupted
     */
    private void generateRandomTransaction() throws InterruptedException {
        Collections.shuffle(accountNumbers); // Shuffle the account numbers for randomness

        int sourceAccountNumber = accountNumbers.get(0);
        int destinationAccountNumber = accountNumbers.get(1);

        // Generate a random amount between 0 and 10,000, rounded to 2 decimal places
        float randomFloat = Math.round(ThreadLocalRandom.current().nextFloat() * 10_000.0f * 100.0f) / 100.0f;


        // Create two transactions, one to withdraw, and the other to deposit
        Transaction transaction1 = new Transaction(sourceAccountNumber, -randomFloat);
        // Withdrawal
        Transaction transaction2 = new Transaction(destinationAccountNumber, randomFloat);

        // Push the transactions to the bank
        bank.submitTransaction(transaction1);
        bank.submitTransaction(transaction2);
    }

    /**
     * Sleep for random amount of time between 0 and 1 second
     *
     * @throws InterruptedException if the thread is interrupted
     */
    private void sleepRandomTime() throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextLong(0, 1000));
    }

    /**
     * Submit a poison pill to the bank to indicate that the generator is closed
     */
    private void submitPoisonPill() {
        bank.submitTransaction(new Transaction(-1, 0));
    }
}
