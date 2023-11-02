/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.week9jsf.data;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 *
 * @author 0063190S
 */
@Entity
@Table(name="TODO")
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String category;
    private String description;
    private int priority;


    public Todo() {
    }

    public Todo(Long id, String category, String description, int priority) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.priority = priority;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category= category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description= description;
    }


    // Hashcode, equals, and toString
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Todo other)) {
            return false;
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }

        if ((this.category == null && other.category != null) || (this.category != null && !this.category.equals(other.category))) {
            return false;
        }

        if ((this.description == null && other.description != null) || (this.description != null && !this.description.equals(other.description))) {
            return false;
        }

        return this.priority != other.priority;
    }

    @Override
    public String toString() {
        return "Todo: id = " + id + ", category = " + category + ", description = " + description + ", priority = " + priority;
    }
    
}
