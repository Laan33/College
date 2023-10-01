/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import data.Customer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.annotation.Resource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;

/**
 *
 * @author catha
 */
@WebServlet(name = "AddCustomer", urlPatterns = {"/AddCustomer"})
public class AddCustomer extends HttpServlet {

    @PersistenceContext(unitName = "persistenceUnitAssignment3")
    private EntityManager em;
    
    @Resource
    private UserTransaction userTransaction;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, SecurityException, IllegalStateException, HeuristicRollbackException
    {
        String sid = request.getParameter("id");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String postCode = request.getParameter("postCode");
        String screditLimit = request.getParameter("creditLimit");

        int id = Integer.parseInt(sid);
        float creditLimit = Float.parseFloat(screditLimit);

        System.out.println("Creating customer with following info:\n " + id + " " + name + " " + address + " " + phone + " " + email + " " + country + " " + postCode + " " + creditLimit);

        Customer customer1 = new Customer(id, name, address, phone, email, country, postCode, creditLimit);
        System.out.println("Customer created");
        userTransaction.begin();
        em.persist(customer1);
        em.flush();
        userTransaction.commit();

        System.out.println("Customer persisted");
      
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("GetEmployees");
        dispatcher.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        } catch (NotSupportedException ex)
        {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex)
        {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex)
        {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex)
        {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex)
        {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex)
        {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex)
        {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        } catch (NotSupportedException ex)
        {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex)
        {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex)
        {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex)
        {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex)
        {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex)
        {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex)
        {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
