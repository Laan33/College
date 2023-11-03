/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.week9jsf.beans;

import com.mycompany.week9jsf.data.Todo;
import com.mycompany.week9jsf.services.TodoFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("todoListBean")
@RequestScoped
public class TodoList implements Serializable {

    @EJB
    private TodoFacade todoFacade;

    private List<Todo> todoList;

    // Define the column model for the table
    private List<ColumnModel> columnModel;

    @PostConstruct
    public void postConstruct() {
        // Initialize the columnModel with headers and property names
        columnModel = new ArrayList<>();
        columnModel.add(new ColumnModel("Category", "category"));
        columnModel.add(new ColumnModel("Description", "description"));
        columnModel.add(new ColumnModel("Priority", "priority"));

        // Retrieve the todo list
        todoList = todoFacade.findAll();
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    public List<ColumnModel> getColumnModel() {
        return columnModel;
    }

    // ColumnModel class to represent headers and properties
    public class ColumnModel {
        private String header;
        private String property;

        public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
        }

        public String getHeader() {
            return header;
        }

        public String getProperty() {
            return property;
        }
    }
}
