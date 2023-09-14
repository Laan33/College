/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import data.ToDo;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author catha
 */
@WebServlet(name = "AddToDoServlet", urlPatterns = {"/AddToDoServlet"})
public class AddToDoServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("List NULL");
        /*
        HttpSession session = request.getSession();
        ArrayList<ToDo> todoList = (ArrayList<ToDo>) session.getAttribute("todoList");
        if (todoList == null) {
            System.out.println("List NULL");
            todoList = new ArrayList<>();
        }

        String subject = request.getParameter("subject");
        String details = request.getParameter("details");

        if (subject != null && details != null) {
            ToDo newToDo = new ToDo(subject, details);
            System.out.println("Task subject " + subject + " details " + details);
            todoList.add(newToDo);

            session.setAttribute("todoList", todoList);

            System.out.println("Task added to list - redirecting to GetToDoListSerlvet");
            response.sendRedirect("GetToDoListServlet");
            return;
        } else {
            System.out.println("Task info null - redirecting to AddToDoServlet");
            response.sendRedirect("AddToDoServlet");
            return;
        }
        */

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
            throws ServletException, IOException {
        processRequest(request, response);
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
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
