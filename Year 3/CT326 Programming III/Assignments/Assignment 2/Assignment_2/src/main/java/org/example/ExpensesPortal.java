package org.example;


import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

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
