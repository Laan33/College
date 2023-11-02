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
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

/**
 *
 * @author 0063190S
 */
@Named("todoBean")
@RequestScoped
public class TodoBean {
    
    @EJB
    private TodoFacade todoFacade;
    
    private String category;
    private String description;
    private int priority;
    private long id;
    private Todo todo;
    
    @PostConstruct
    public void postConstruct() {
        String todoIdParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("todoId");
        if (todoIdParam != null) {
            id = Integer.parseInt(todoIdParam);
            todo = todoFacade.find(id);

        }
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        System.out.println("in setCategory category = " + category);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        System.out.println("in setDescription description = " + description);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
        System.out.println("in setPriority priority = " + priority);
    }
    
    public String add() {
        Todo newTodo = new Todo();
        newTodo.setCategory(category);
        newTodo.setDescription(description);
        newTodo.setPriority(priority);

        todoFacade.create(newTodo);
        System.out.println("Added todo category = " + category + " description = " + description + " priority = " + priority);
        return "success";
    }

    public String update() {
        todo.setCategory(category);
        todo.setDescription(description);
        todo.setPriority(priority);
        todoFacade.edit(todo);
        return "success";
    }
    
    public String delete() {
        todoFacade.remove(todo);
        return "success";
    }
}
