/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.week9jsf.beans;

import com.mycompany.week9jsf.data.Book;
import com.mycompany.week9jsf.services.BookFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

/**
 *
 * @author 0063190S
 */
@Named("bookBean")
@RequestScoped
public class BookBean {
    
    @EJB
    private BookFacade bookFacade;
    
    private String title;
    private long id;
    private Book book;
    
    @PostConstruct
    public void postConstruct() {
        String bookIdParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("bookId");
        if (bookIdParam != null) {
            id = Integer.parseInt(bookIdParam);
            book = bookFacade.find(id);
            title = book.getTitle();
        }
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String add() {
        Book newBook = new Book();
        newBook.setTitle(title);
        bookFacade.create(newBook);
        System.out.println("in add title = " + title);
        return "success";
    }

    public String update() {
        book.setTitle(title);
        bookFacade.edit(book);
        return "success";
    }
    
    public String delete() {
        bookFacade.remove(book);
        return "success";
    }
}
