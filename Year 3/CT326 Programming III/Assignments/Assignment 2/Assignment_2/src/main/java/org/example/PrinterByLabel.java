package org.example;

import java.util.List;

public class PrinterByLabel implements ExpensePrinter{
    @Override
    public void print(List<Expense> expenses) {
        for (Expense expense : expenses) {
            System.out.println(expense.getDate() + ": " + expense.getDescription() + " - " + expense.getCategory() + " - " + expense.getCost());
        }
    }
}
