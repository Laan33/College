package org.example;

import java.util.List;

/**
 * Interface for printing expenses
 */
public interface ExpensePrinter {
    /**
     * Method to print expenses
     * @param expenses - List of expenses to be printed
     */
    void print(List<Expense> expenses);
}
