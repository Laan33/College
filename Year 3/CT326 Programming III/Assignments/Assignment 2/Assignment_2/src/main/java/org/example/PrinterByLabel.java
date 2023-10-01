package org.example;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrinterByLabel implements ExpensePrinter{
    @Override
    public void print(List<Expense> expenses) {
        //Sorting expenses by category
        Collections.sort(expenses, Comparator.comparing(Expense::getCategory));
        for (ExpenseCategory category : ExpenseCategory.values()) {
            System.out.println(category);
            for (Expense expense : expenses) {
                if (expense.getCategory() == category) {
                    System.out.println(expense);
                }
            }
            System.out.println("");
        }
    }
}
