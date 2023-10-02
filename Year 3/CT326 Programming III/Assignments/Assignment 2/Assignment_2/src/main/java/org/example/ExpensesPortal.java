package org.example;


import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
/*
 * Cathal Lawlor
 * 21325456
 */

/**
 * Class to represent an expense portal
 * Holds a list of expenses, allows submitting of expenses
 *
 * Method to print out expenses with summaries for the expense categories @see ExpenseCategory
 * Including summing expenses
 *
 * @author catha
 * @version 1.0
 */
public class ExpensesPortal {
    /**
     * List for holding the expenses
     */
    List<Expense> expenses = new ArrayList<>();


    /**
     * Method for submitting expenses to the list
     *
     * @param expense
     */
    public void submitExpense(Expense expense) {
        expenses.add(expense);
    }

    /**
     * Method to print out expenses
     * Takes an ExpensePrinter as a parameter
     *
     * @param printer
     */
    public void printExpenses(ExpensePrinter printer) {
        printer.print(expenses);
    }

    /**
     * Sums all expenses
     * Only permitted currencies are USD & EUR
     * Will throw an exception if any other currency is submitted
     *
     * @param expenses
     * @return a total in joda.org.Money with the currency being euro
     */
    public static Money sumExpenses(List<Expense> expenses) {
        Money total = Money.zero(CurrencyUnit.EUR);
        BigDecimal conversionRate = new BigDecimal("0.94");  // obtained from code outside Joda-Money
           
        for (Expense expense : expenses) {
            // if neither USD nor EUR, throw exception
            if (expense.getCost().getCurrencyUnit() != CurrencyUnit.EUR && expense.getCost().getCurrencyUnit() != CurrencyUnit.USD) {
                throw new IllegalArgumentException("Currency not supported");
            }
            else if (expense.getCost().getCurrencyUnit() == CurrencyUnit.USD) { //converting if it is USD
                total = total.plus(expense.getCost().
                convertedTo(CurrencyUnit.EUR, conversionRate, RoundingMode.HALF_UP)
                );
            } else { //Add euro to total
                total = total.plus(expense.getCost());
            }
        }
        return total;
    }



}
