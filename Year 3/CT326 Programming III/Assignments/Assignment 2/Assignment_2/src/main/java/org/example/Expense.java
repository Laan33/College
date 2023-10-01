package org.example;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import java.time.LocalDate;

public class Expense {
    private LocalDate date;
    private String description;
    private ExpenseCategory category;
    private Money cost;


    public Expense(LocalDate date, String description, ExpenseCategory category, Money cost) {
        this.date = date;
        this.description = description;
        this.category = category;
        this.cost = cost;
    }

    //Accessor methods
    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public Money getCost() {
        return cost;
    }

//Sample expense format:
//2022-09-20: Dell 17-inch monitor - EQUIPMENT - USD 540.00
    @Override
    public String toString() {
        return date + ": " + description + " - " + category + " - " + cost;
    }


    /*
    @Override
    public String toString() {
        return date + ": " + description + " - " + category + " - " +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", cost=" + cost +
                '}';
    } */


}
