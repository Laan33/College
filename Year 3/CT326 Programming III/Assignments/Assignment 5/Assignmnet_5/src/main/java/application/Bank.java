package application;

import exceptions.DuplicateAccountException;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Bank class represents a bank that manages accounts and transactions.
 * It has methods for adding accounts, retrieving accounts, submitting transactions, and printing accounts.
 * It also has a method for printing a summary of all accounts in the bank.
 * It has a map of accounts and a queue of transactions.
 * It implements the BankInterface interface.
 * 
 * @author Cathal Lawlor
 */
public class Bank {
    private static final Logger logger = Logger.getLogger(Bank.class.getName());
    Map<Integer, Account> accounts = new HashMap<>();
    LinkedBlockingQueue<Transaction> transactions = new LinkedBlockingQueue<>();

    /**
     * Adds an account to the bank.
     *
     * @param account the account to be added
     * @throws DuplicateAccountException if an account with the same account number already exists
     */
    void addAccount(Account account) throws DuplicateAccountException {
        if (accounts == null) {
            throw new NullPointerException();
        }

        if (accounts.containsKey(account.getAccountNumber())) {
            throw new DuplicateAccountException();
        }

        accounts.put(account.getAccountNumber(), account);
    }

    /**
     * Retrieves an account by its account number.
     *
     * @param accountNum the account number
     * @return the account with the specified account number
     * @throws AccountNotFoundException if no account is found with the specified account number
     */
    Account getAccountById(int accountNum) throws AccountNotFoundException {
        if (accounts == null) {
            throw new NullPointerException();
        }
        Account account = accounts.get(accountNum);

        if (account == null) {
            throw new AccountNotFoundException("Account not found for account number: " + accountNum);
        }

        return account;
    }

    /**
     * Submits a transaction to the bank.
     * 
     * @param transaction the transaction to be submitted
     */
    void submitTransaction(Transaction transaction) {
        if (transactions == null) {
            throw new NullPointerException();
        }

        transactions.add(transaction);
    }

    /**
     * Retrieves and removes the next transaction from the bank.
     *
     * @return the next transaction, or null if there are no more transactions
     */
    Transaction getNextTransaction() {
        return transactions.poll();
    }

    /**
     * Prints the account numbers and balances of all accounts in the bank.
     */
    void printAccounts() {
        for (Account account : accounts.values()) {
            System.out.println(account.getAccountNumber() + " " + account.getBalance());
        }
    }

    /**
     * Retrieves the account numbers of all accounts in the bank.
     *
     * @return a set of account numbers
     */
    Set<Integer> getAccountNumbers() {
        return accounts.keySet();
    }

    /**
     * Prints a summary of all accounts in the bank.
     *
     * @return a string containing the account summaries
     */
    public String printAccountsSummary() {
        StringBuilder summaryString = new StringBuilder();
        getAccountNumbers().forEach(accountNumber -> {
            try {
                summaryString.append(getAccountById(accountNumber)).append("\n");
            } catch (AccountNotFoundException e) {
                // Log the exception with the logger
                logger.log(Level.SEVERE, "Account not found for account number: " + accountNumber);
            }
        });
        return summaryString.toString();
    }
}
