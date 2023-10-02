package org.example;
import org.joda.money.Money;
import java.time.LocalDate;

/**
 * Class to represent an expense
 *
 * @author catha
 *
 */
public class Expense {
    private final LocalDate date;
    private final String description;
    private final ExpenseCategory category;
    private final Money cost;

    /**
     * Expense constructor
     *
     * @param date - LocalDate from java.time - date of expense
     * @param description - String description of expense
     * @param category - ExpenseCategory from ExpenseCategory.java - category of expense
     * @param cost - Money from joda.org - cost of expense
     */
    public Expense(LocalDate date, String description, ExpenseCategory category, Money cost) {
        this.date = date;
        this.description = description;
        this.category = category;
        this.cost = cost;
    }

    //Accessor methods

    /**
     *
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return category
     */
    public ExpenseCategory getCategory() {
        return category;
    }

    /**
     *
     * @return cost
     */
    public Money getCost() {
        return cost;
    }

    /**
     * Sample expense format - using a formatted string
     * 2022-09-20: Dell 17-inch monitor - EQUIPMENT - USD 540.00
     *
     * @return a formatted string
     */

    @Override
    public String toString() {
        return String.format("%s: %s - %s - %s", date, description, category, cost);
    }


}
