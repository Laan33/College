package application;

import exceptions.DuplicateAccountException;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;




public class Bank {
    private static final Logger logger = Logger.getLogger(Bank.class.getName());
    Map<Integer, Account> accounts = new HashMap<>();
    LinkedBlockingQueue<Transaction> transactions = new LinkedBlockingQueue<>();


    void addAccount(Account account) throws DuplicateAccountException {
        if (accounts == null) {
            throw new NullPointerException();
        }

        if (accounts.containsKey(account.getAccountNumber())) {
            throw new DuplicateAccountException();
        }

        accounts.put(account.getAccountNumber(), account);
    }

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

    void submitTransaction(Transaction transaction) {
        if (transactions == null) {
            throw new NullPointerException();
        }

        transactions.add(transaction);
    }

    Transaction getNextTransaction() {
        return transactions.poll();
    }

    void printAccounts() {
        for (Account account : accounts.values()) {
            System.out.println(account.getAccountNumber() + " " + account.getBalance());
        }
    }

    Set<Integer> getAccountNumbers() {
        return accounts.keySet();
    }


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
