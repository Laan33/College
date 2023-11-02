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
import java.util.List;

/**
 *
 * @author 0063190S
 */
@Named("todoListBean")
@RequestScoped
public class TodoList implements Serializable
{

    @EJB
    private TodoFacade todoFacade;

    private List<Todo> todoList;
    
    @PostConstruct
    public void postConstruct()
    {
        System.out.println("calling todo facade");
        todoList = todoFacade.findAll();
        System.out.println("num todos = " + todoList.size());
    }
    
       public List<Todo> getTodoList() {
           System.out.println("calling getTodoList");
           System.out.println("Size of getTodoList   -   " + todoList.size() );
        return todoList;
    }
}
