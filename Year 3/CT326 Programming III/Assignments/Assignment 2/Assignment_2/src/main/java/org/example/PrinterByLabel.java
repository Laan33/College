package org.example;

import java.util.Collections;
import java.util.List;

public class PrinterByLabel implements ExpensePrinter{
    @Override
    public void print(List<Expense> expenses) {
        //Sorting expenses by category
        Collections.sort(expenses, (e1, e2) -> e1.getCategory().compareTo(e2.getCategory()));
        for (Expense expense : expenses) {
            System.out.println(expense.getCategory());
            System.out.println(expense);
            System.out.println("");
            
        }


    }
}
