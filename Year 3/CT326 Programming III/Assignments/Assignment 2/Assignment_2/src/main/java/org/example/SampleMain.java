/**
 *
 * Main class to test the ExpensesPortal class
 *
 * @see ExpensesPortal
 * @see Expense
 *
 * @version 1.0
 * @autor catha
 *
 */
package org.example;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.time.LocalDate;
import java.util.List;

public class SampleMain {
    public static void main(String[] args) {
        //Create ExpensesPortal instance
        ExpensesPortal expensesPortal = new ExpensesPortal();

        //Sample expenses for ExpensesPortal instance
        //Sample Insurance expense
        Expense expenseTemp = new Expense(LocalDate.of(2021,5,1), "Fire Insurance", ExpenseCategory.OTHER, (Money.of(CurrencyUnit.EUR, 1000)));
        expensesPortal.submitExpense(expenseTemp);

        //Sample Travel expense
        expenseTemp = new Expense(LocalDate.of(2023,3,27), "Flight", ExpenseCategory.TRAVEL_AND_SUBSISTENCE, (Money.of(CurrencyUnit.USD, 239)));
        expensesPortal.submitExpense(expenseTemp);

        //Sample Entertainment expense
        expenseTemp = new Expense(LocalDate.of(2022,2,23), "Film tickets", ExpenseCategory.ENTERTAINMENT, (Money.of(CurrencyUnit.USD, 25)));
        expensesPortal.submitExpense(expenseTemp);

        //Sample Equipment expense
        expenseTemp = new Expense(LocalDate.of(2021,12,8), "Server", ExpenseCategory.EQUIPMENT, (Money.of(CurrencyUnit.EUR, 13000)));
        expensesPortal.submitExpense(expenseTemp);

        //Sample Supplies expenses
        expenseTemp = new Expense(LocalDate.of(2022,8,12), "Office supplies", ExpenseCategory.SUPPLIES, (Money.of(CurrencyUnit.EUR, 47)));
        expensesPortal.submitExpense(expenseTemp);
        expenseTemp = new Expense(LocalDate.of(2022,3,29), "Coffee dock supplies", ExpenseCategory.SUPPLIES, (Money.of(CurrencyUnit.EUR, 100)));
        expensesPortal.submitExpense(expenseTemp);

        System.out.println("--------------");
        System.out.println("Part one: ");
        System.out.println("--------------\n");

        expensesPortal.printExpenses(expenses -> {
            for (Expense expense : expenses) {
                System.out.println(expense.getDate() + ": " + expense.getDescription() + " - " + expense.getCategory() + " - " + expense.getCost());
            }
        });

        System.out.println("\n--------------");
        System.out.println("Part two: ");
        System.out.println("--------------\n");

        expensesPortal.printExpenses(new ExpensePrinter() {
            @Override
            public void print(List<Expense> expenses) {
                System.out.println("There are " + expenses.size() + " expenses in the system totalling to a value of " + ExpensesPortal.sumExpenses(expenses));
            }
        });

        System.out.println("\n--------------");
        System.out.println("Part three: ");
        System.out.println("--------------\n");

        //Call the printExpenses method using a PrinterByLabel instance as a parameter
        expensesPortal.printExpenses(new PrinterByLabel());


    }
}
