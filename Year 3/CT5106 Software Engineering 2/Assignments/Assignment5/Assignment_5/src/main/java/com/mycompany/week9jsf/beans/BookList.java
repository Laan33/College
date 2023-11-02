/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.week9jsf.beans;

import com.mycompany.week9jsf.data.Book;
import com.mycompany.week9jsf.services.BookFacade;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author 0063190S
 */
@Named("bookListBean")
@RequestScoped
public class BookList implements Serializable
{

    @EJB
    private BookFacade bookFacade;

    private List<Book> booksList;
    
    @PostConstruct
    public void postConstruct()
    {
        System.out.println("calling book facade");
        booksList = bookFacade.findAll();
        System.out.println("num books = " + booksList.size());
    }
    
       public List<Book> getBooksList() {
           System.out.println("calling getBooksList");
           System.out.println("Size of getBookslist   -   " + booksList.size() );
        return booksList;
    }
}
