/**
 * TransactionProcessor class for CT326 Assignment 5 (23/24)
 * This class is responsible for processing transactions from the bank.
 * It implements the Runnable interface to be executed in a separate thread.
 * 
 * The TransactionProcessor class receives transactions from the Bank class and processes them.
 * It keeps track of the number of deposits and withdrawals made, as well as the time since the last transaction.
 * 
 * The class uses the Joda Money library to handle currency and money operations.
 * 
 * @author Cathal Lawlor
 */
package application;

import exceptions.InsufficientFundsException;
import exceptions.NegativeBalanceException;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;


/**
 * The TransactionProcessor class represents a thread that processes transactions for a bank.
 * It implements the Runnable interface.
 */
public class TransactionProcessor implements Runnable {
    private final String name;
    private final Bank bank;
    private int depositsCount, withdrawalsCount;
    private long timeSinceLastTransaction;
    private static final Logger logger = Logger.getLogger(TransactionProcessor.class.getName()); // Logger for TransactionProcessor
    private final int MAX_TIME_SINCE_FIRST_TRANSACTION = 5000; // 5 seconds in milliseconds - max time to wait for a new transaction

    /**
     * Constructs a TransactionProcessor object with the specified name and bank.
     *
     * @param name The name of the transaction processor.
     * @param bank The bank for which the transactions will be processed.
     */
    public TransactionProcessor(String name, Bank bank) {
        this.name = name;
        this.bank = bank;
        this.depositsCount = 0;
        this.withdrawalsCount = 0;
        this.timeSinceLastTransaction = System.currentTimeMillis();
    }

    /**
     * Runs the transaction processing logic.
     * It retrieves transactions from the bank and processes them until a poison pill transaction is encountered
     * or the time since the last transaction exceeds the maximum time limit.
     */
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
                } catch (InsufficientFundsException | AccountNotFoundException | InterruptedException |
                         NegativeBalanceException e) {
                    logger.severe("Error processing transaction: " + e.getMessage());
                }
            }
            // Get the next transaction from the bank
            transaction = bank.getNextTransaction();
        }

        // Print the name of the thread and the number of deposits and withdrawals
        printProcessorSummary();
    }

    /**
     * Prints a summary of the transaction processor's activity, including the number of deposits and withdrawals processed.
     */
    private void printProcessorSummary() {
        System.out.println(name + " has processed " + (depositsCount + withdrawalsCount) + " transactions, including "
                + depositsCount + " deposits, and " + withdrawalsCount + " withdrawals");
    }

    /**
     * Prints a summary of the given transaction, including the type (deposit or withdrawal), amount, and account number.
     *
     * @param transaction The transaction to be summarized.
     */
    private void printSummary(Transaction transaction) {
        // Print the name of the thread and either "a deposit" or "a withdrawal" of currency and amount from account number
        if (transaction.getAmount() > 0) {
            System.out.println(name + " has processed a deposit of " + transaction.getAmount() + " from account number " + transaction.getAccountNumber());
        } else {
            System.out.println(name + " has processed a withdrawal of " + transaction.getAmount() + " from account number " + transaction.getAccountNumber());
        }
    }

    /**
     * Processes a transaction by depositing/withdrawing from the appropriate account.
     *
     * @param transaction The transaction to be processed.
     * @throws InsufficientFundsException If the account has insufficient funds for a withdrawal.
     * @throws AccountNotFoundException If the account specified in the transaction is not found.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     * @throws NegativeBalanceException If the account balance becomes negative after a withdrawal.
     */
    public void processTransaction(Transaction transaction)
            throws InsufficientFundsException, AccountNotFoundException, InterruptedException, NegativeBalanceException {

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
     * Sleeps for a random amount of time between 0 and 1 second.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    private void sleepRandomTime() throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextLong(0, 1000));
    }
}
