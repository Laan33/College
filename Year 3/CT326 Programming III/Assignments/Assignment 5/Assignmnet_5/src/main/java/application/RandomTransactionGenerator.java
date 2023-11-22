package application;
/**
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;



public class RandomTransactionGenerator implements Runnable {
    Bank bank;
    Random random = new Random();
    private static final Logger logger = Logger.getLogger(RandomTransactionGenerator.class.getName());

    private List<Integer> accountNumbers;

    public RandomTransactionGenerator(Bank bank) {
        this.bank = bank;
        this.accountNumbers = new ArrayList<>(bank.getAccountNumbers());

    }


    @Override
    public void run() {
        try {
            // While thread is not interrupted, call generateRandomTransaction() and submit the transaction to the bank
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
     * Create a RandomTransactionGenerator with the given bank
     *
     * @return a RandomTransactionGenerator with the given bank
     */
    private void generateRandomTransaction() throws InterruptedException {
        Collections.shuffle(accountNumbers);

        int sourceAccountNumber = accountNumbers.get(0);
        int destinationAccountNumber = accountNumbers.get(1);

        // Generate a random d amount between 0 and 10,000
//        float randomFloat = Math.round(random.nextFloat(10_000.0f) * 100.0f) / 100.0f;
        float randomFloat = Math.round(ThreadLocalRandom.current().nextFloat() * 10_000.0f * 100.0f) / 100.0f;

        //System.out.println(" RandFloat " + randomFloat);

        // Create two transactions, one to withdraw, and the other to deposit
        Transaction transaction1 = new Transaction(sourceAccountNumber, -randomFloat);
        // Withdrawal
        Transaction transaction2 = new Transaction(destinationAccountNumber, randomFloat);

        // Push the transactions to the bank
        bank.submitTransaction(transaction1);
        bank.submitTransaction(transaction2);
    }

    /**
     *  Sleep for random amount of time between 0 and 1 second
     *
     *  @throws InterruptedException if the thread is interrupted
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
