package org.example;


import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/*
maintains a list of expenses and provides a means
for submitting a new expense. It should also have a method called printExpenses with
the following signature:
public void printExpenses(ExpensePrinter printer)
ExpensePrinter is an interface that you must define with a single method called print
that takes a list of expenses as a parameter and prints the expenses to the console in a
particular format.
 */
public class ExpensesPortal {
    //List of expenses
    List<Expense> expenses = new ArrayList<>();


    //Method to submit a new expense
    public void submitExpense(Expense expense) {
        expenses.add(expense);
    }

    //Method to print expenses
    public void printExpenses(ExpensePrinter printer) {
        printer.print(expenses);
    }

    //Method to sum expenses
    public static Money sumExpenses(List<Expense> expenses) {
        Money total = Money.zero(CurrencyUnit.EUR);
        BigDecimal conversionRate = new BigDecimal("0.94");  // obtained from code outside Joda-Money
           
        for (Expense expense : expenses) {
            /*
            System.out.println("Expense: " + expense + " is " + total);
            if (expense.getCost().getCurrencyUnit() == CurrencyUnit.EUR) {
                //expense.getCost()
                total.plus(2);
            } */

            // convert to EUR using a supplied rate
            if (expense.getCost().getCurrencyUnit() != CurrencyUnit.EUR && expense.getCost().getCurrencyUnit() != CurrencyUnit.USD) {
                throw new IllegalArgumentException("Currency not supported");
            }
            else
            if (expense.getCost().getCurrencyUnit() == CurrencyUnit.USD) {
                total = total.plus(expense.getCost().
                convertedTo(CurrencyUnit.EUR, conversionRate, RoundingMode.HALF_UP)
                );
            } else {
                total = total.plus(expense.getCost());
            }
        }
        return total;
    }



}
