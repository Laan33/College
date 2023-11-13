import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/*
  Responsible for a collection of customer bank accounts
  (Map<Integer, Account> where the integer refers to the account number of the account)
  and uses a LinkedBlockingQueue object, as described below, to maintain a collection of bank
  account transactions to be processed on these accounts.

  You should represent bank accounts using the Account class that is provided to you on Canvas.
  Note that you’ll need to adapt this class so that it can be used in concurrent applications.

  You should represent transactions using the Transaction class that is provided to you on Canvas.
  Note that the bank only deals in the currency EUR.
 */

/**
 * Your Bank class should contain methods for the following:
 * • adding an account to the Bank
 * • getting an account given its account number
 * • submitting a Transaction to the queue for processing
 * • getting the next Transaction from the queue for processing
 * • printing the accounts’ details (account number and balance)
 * • getting a collection of account numbers
 */


public class Bank {
    Map<Integer, Account> accounts;
    LinkedBlockingQueue<Transaction> transactions;



    void addAccount() {

    }

    Account getAccountById(int accountNum) {
        Account account = new Account();

        return account;
    }






}
