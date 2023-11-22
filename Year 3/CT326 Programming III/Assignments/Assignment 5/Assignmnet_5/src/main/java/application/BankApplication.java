package application;

import exceptions.DuplicateAccountException;
import exceptions.NegativeBalanceException;
import org.joda.money.Money;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;



public class BankApplication {
    private static final Logger logger = Logger.getLogger(BankApplication.class.getName());

    public static void main(String[] args) {
        final int RANDOM_TRANSACTION_RUNNING_TIME = 10_000;
        Bank bank = new Bank();


        // Create and add three Account instances to the bank with different starting balances
        try {
            // Create and add three Account instances to the bank with different starting balances
            Account account1 = new Account(13453, Money.parse("EUR " + "134670"));
            Account account2 = new Account(72339, Money.parse("EUR " + "100200"));
            Account account3 = new Account(38931, Money.parse("EUR " + "622010"));

            int totalBalanceStart = account1.getBalance()
                    .plus(account2.getBalance())
                    .plus(account3.getBalance())
                    .getAmountMajorInt();
            System.out.println("Total balance of all accounts: " + totalBalanceStart);

            // Add the accounts to the bank
            bank.addAccount(account1);
            bank.addAccount(account2);
            bank.addAccount(account3);

            // Declare and instantiate two TransactionProcessor threads and one RandomTransactionGenerator thread
            RandomTransactionGenerator randomTransactionGenerator = new RandomTransactionGenerator(bank);

            TransactionProcessor transactionProcessor1 = new TransactionProcessor("Transaction Processor 1", bank);
            TransactionProcessor transactionProcessor2 = new TransactionProcessor("Transaction Processor 2", bank);



            // Execute the threads using a thread pool (ExecutorService) and wait for them to complete
            ExecutorService randomTransactionGeneratorExecutorService = Executors.newFixedThreadPool(1);
            ExecutorService transactionProcessorExecutorService = Executors.newFixedThreadPool(2);

            // Add the TransactionProcessors and RandomTransactionGenerator to the ExecutorService
            randomTransactionGeneratorExecutorService.execute(randomTransactionGenerator);
            transactionProcessorExecutorService.execute(transactionProcessor1);
            transactionProcessorExecutorService.execute(transactionProcessor2);

            // Wait for the threads to complete
            transactionProcessorExecutorService.shutdown();
            randomTransactionGeneratorExecutorService.shutdown();

            try {
                // Wait for the threads to complete

                if (!transactionProcessorExecutorService.awaitTermination(RANDOM_TRANSACTION_RUNNING_TIME, TimeUnit.MILLISECONDS)) {

                    randomTransactionGeneratorExecutorService.shutdownNow();

                    if (transactionProcessorExecutorService.awaitTermination(25_000, TimeUnit.MILLISECONDS)) {
                        // Print out the details of the accounts after the transactions have finished
                        System.out.println(bank.printAccountsSummary());
                        System.out.println("Total balance of all accounts at the start: " + totalBalanceStart);
                        System.out.println("Total balance of all accounts at the end: "
                                + account1.getBalance()
                                .plus(account2.getBalance())
                                .plus(account3.getBalance()));
                    }
                }
            } catch (InterruptedException e) {
                logger.severe("Error waiting for threads to complete: " + e.getMessage());
            }
        } catch (NegativeBalanceException | DuplicateAccountException e ) {
            throw new RuntimeException(e);
        }
    }
}
