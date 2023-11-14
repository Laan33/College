package application;
/**
 *
 *
 *
 */

/**
 * RandomTransactionGenerator:
 * • created with a Bank instance
 * • randomly generates deposit and withdrawal transactions of up to EUR 10,000 for random
 * accounts in the bank and submits them to the bank queue for processing
 * • sleeps for a random amount of time (between 0 and 1 seconds) between the generation of
 * transactions.
 * • once it has been terminated, it inserts an end-of-stream (or “Poison pill”) object to the bank
 * queue to indicate that it is closed.
 */


public class RandomTransactionGenerator implements Runnable{
    Bank bank;


    @Override
    public void run() {
        while (true) {
            Transaction transaction = generateRandomTransaction();
            bank.submitTransaction(transaction);
            try {
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    // Generate a random transaction between -10000 and 10000 for a random account in the bank
    private Transaction generateRandomTransaction() {
        int accountNum = (int) (Math.random() * bank.getAccountNumbers().size());
        float amount = (float) (Math.random() * 20000 - 10000);
        return new Transaction(accountNum, amount);
    }
}
