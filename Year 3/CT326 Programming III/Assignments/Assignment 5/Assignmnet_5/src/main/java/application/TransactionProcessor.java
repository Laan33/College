package application;

import exceptions.InsufficientFundsException;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;


public class TransactionProcessor implements Runnable {
    private final String name;
    private final Bank bank;
    private int depositsCount;
    private int withdrawalsCount;
    private long timeSinceLastTransaction;
    private static final Logger logger = Logger.getLogger(TransactionProcessor.class.getName());
    private final int MAX_TIME_SINCE_FIRST_TRANSACTION = 5000; // 5 seconds in milliseconds - max time to wait for a new transaction



    public TransactionProcessor(String name, Bank bank) {
        this.name = name;
        this.bank = bank;
        this.depositsCount = 0;
        this.withdrawalsCount = 0;
        this.timeSinceLastTransaction = System.currentTimeMillis();
    }

    @Override
    public void run() {
        // Get the first transaction from the bank
        Transaction transaction = bank.getNextTransaction();

        // While the transaction is not the poison pill or the time since the last transaction is less than 5 seconds
        while ((System.currentTimeMillis() - timeSinceLastTransaction) < MAX_TIME_SINCE_FIRST_TRANSACTION) {

            // Null check
            if (transaction != null) {

                // If the transaction is the poison pill
                if (transaction.getAccountNumber() == -1) {
                    // Break out of the loop
                    break;
                }

                // Process the current transaction
                try {
                    processTransaction(transaction);
                    sleepRandomTime();
                } catch (InsufficientFundsException | AccountNotFoundException | InterruptedException e) {
                    logger.severe("Error processing transaction: " + e.getMessage());
                }
            }
            // Get the next transaction from the bank
            transaction = bank.getNextTransaction();
        }

        // Print the name of the thread and the number of deposits and withdrawals
        printProcessorSummary();
    }

    private void printProcessorSummary() {
        System.out.println(name + " has processed " + (depositsCount + withdrawalsCount) + " transactions, including "
                + depositsCount + " deposits, and " + withdrawalsCount + " withdrawals");
    }

    private void printSummary(Transaction transaction) {
        // Print the name of the thread and either "a deposit" or "a withdrawal" of currency and amount from account number
        if (transaction.getAmount() > 0) {
            System.out.println(name + " has processed a deposit of " + transaction.getAmount() + " from account number " + transaction.getAccountNumber());
        } else {
            System.out.println(name + " has processed a withdrawal of " + transaction.getAmount() + " from account number " + transaction.getAccountNumber());
        }
    }

    //Process a transaction by depositing/withdrawing from the appropriate account
    public void processTransaction(Transaction transaction)
            throws InsufficientFundsException, AccountNotFoundException, InterruptedException {

        Account account = bank.getAccountById(transaction.getAccountNumber());

        BigDecimal amount = BigDecimal.valueOf(transaction.getAmount());
        amount = amount.setScale(CurrencyUnit.EUR.getDecimalPlaces(), RoundingMode.HALF_UP);
        Money money = Money.of(CurrencyUnit.EUR, amount);

        if (transaction.getAmount() > 0) {
            account.makeDeposit(money);
            depositsCount++;
        } else {
            money = money.negated(); // Negate the money amount for withdrawal
            account.makeWithdrawal(money);
            withdrawalsCount++;
        }

        this.timeSinceLastTransaction = System.currentTimeMillis();

        // Print transaction summary
        printSummary(transaction);
    }

    /**
     * Sleep for random amount of time between 0 and 1 second
     *
     * @throws InterruptedException if thread is interrupted
     */
    private void sleepRandomTime() throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextLong(0, 1000));
    }

}
