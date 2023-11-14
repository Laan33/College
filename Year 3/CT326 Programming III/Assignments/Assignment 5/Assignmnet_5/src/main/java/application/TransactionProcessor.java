package application; /**
 *
 */

import exceptions.InsufficientFundsException;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 * • created with a name and a Bank instance
 * • takes a transaction from the bank and processes it by depositing/withdrawing from the
 * appropriate account
 * • keeps a tally of how many withdrawals and how many deposits it has made
 * • sleeps for a random amount of time (between 0 and 1 seconds) between the processing of
 * transactions
 * • finishes executing once the queue has been closed or if it has waited 5 seconds for a new
 * transaction from the queue without receiving one.
 * • when it finishes executing, it prints to the console its name and the number of deposits and
 * withdrawals it has processed.
 */

public class TransactionProcessor implements Runnable {
    String name;
    Bank bank;
    int deposits = 0;
    int withdrawals = 0;


    public TransactionProcessor(String name, Bank bank) {
        this.name = name;
        this.bank = bank;
    }

    //Process a transaction by depositing/withdrawing from the appropriate account
    public void processTransaction(Transaction transaction) throws InsufficientFundsException {
        Account account = bank.getAccountById(transaction.getAccountNumber());
        if (account == null) {
            System.out.println("Account not found");
            return;
        }
        Money amount = Money.of(CurrencyUnit.EUR, transaction.getAmount());
        if (amount.isGreaterThan(Money.of(CurrencyUnit.EUR, 0))) {
            account.makeDeposit(amount);
            deposits++;
        } else {
            account.makeWithdrawal(amount);
            withdrawals++;
        }
    }

    @Override
    public void run() {


    }

    /*
    public void SAMPLE() {
        int deposits = 0;
        int withdrawals = 0;
        while (true) {
            Transaction transaction = bank.getNextTransaction();
            if (transaction == null) {
                break;
            }
            try {
                processTransaction(transaction);
                if (transaction.getAmount() > 0) {
                    deposits++;
                } else {
                    withdrawals++;
                }
            } catch (InsufficientFundsException e) {
                System.out.println("Insufficient funds");
            }
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " " + deposits + " " + withdrawals);
    } */


}
