package org.example;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.time.LocalDate;

public class SampleMain {
    public static void main(String[] args) {
        //Create ExpensesPortal instance
        ExpensesPortal expensesPortal = new ExpensesPortal();

        //Sample expenses for ExpensesPortal instance
        //Sample Insurance expense
        Expense expense1 = new Expense(LocalDate.of(2021,5,1), "Fire Insurance", ExpenseCategory.OTHER, (Money.of(CurrencyUnit.EUR, 1000)));
        expensesPortal.submitExpense(expense1);
        //Sample Travel expense
        Expense expense2 = new Expense(LocalDate.of(2023,3,27), "Flight", ExpenseCategory.TRAVEL_AND_SUBSISTENCE, (Money.of(CurrencyUnit.USD, 239)));
        expensesPortal.submitExpense(expense2);
        //Sample Entertainment expense
        Expense expense3 = new Expense(LocalDate.of(2022,2,23), "Film tickets", ExpenseCategory.ENTERTAINMENT, (Money.of(CurrencyUnit.USD, 25)));
        expensesPortal.submitExpense(expense3);
        //Sample Equipment expense
        Expense expense4 = new Expense(LocalDate.of(2021,12,8), "Server", ExpenseCategory.EQUIPMENT, (Money.of(CurrencyUnit.EUR, 13000)));
        expensesPortal.submitExpense(expense4);
        //Sample Supplies expense
        Expense expense5 = new Expense(LocalDate.of(2022,8,12), "Office supplies", ExpenseCategory.SUPPLIES, (Money.of(CurrencyUnit.EUR, 47)));
        expensesPortal.submitExpense(expense5);

        /* Call the printExpenses method using a lambda expression to implement the
ExpensePrinter parameter. The implementation should print a list of expenses to the
console as follows:
Sample output:
2022-09-23: Flight to Glasgow - TRAVEL_AND_SUBSISTENCE - EUR 270.59
2022-09-20: Dell 17-inch monitor - EQUIPMENT - USD 540.00
2022-09-21: Java for Dummies - OTHER - EUR 17.99 */
        expensesPortal.printExpenses(expenses -> {
            for (Expense expense : expenses) {
                System.out.println(expense.getDate() + ": " + expense.getDescription() + " - " + expense.getCategory() + " - " + expense.getCost());
            }
        });

        /*
        Call the printExpenses method using an anonymous inner class to implement the
ExpensePrinter parameter. The implementation should print a summary of expenses to
the console as follows:
Sample output:
There are 3 expenses in the system totalling to a value of EUR 769.18.
Hint: create a static method in ExpensesPortal for summing the expenses. The method
should support expenses in two currencies (EUR and USD). The return type should be of type
joda.org.Money and should be in EUR, so you’ll need to convert from USD to EUR to
support USD expenses. See the joda.org link above for examples of how to do this.
         */






    }
}
