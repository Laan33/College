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
@WebServlet(name = "GetToDoListServlet", urlPatterns = {"/GetToDoListServlet"})
public class GetToDoListServlet extends HttpServlet {

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
        
        HttpSession session = request.getSession(); //retrieving list     
        ArrayList<ToDo> todoList = (ArrayList<ToDo>) session.getAttribute("todoList");
        
        if (todoList == null || todoList.isEmpty()) { //List NULL or Empty - redirecting to AddToDoSerlvet            
            request.getRequestDispatcher("/addToDo.html").forward(request, response);
            return;
        } 
        
        try (PrintWriter out = response.getWriter()) {
            /* Page output  */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ToDo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Todo Tasks</h1>");
            out.println("<table><tr><th>Subject</th><th>Details</th></tr>");
            for (ToDo task : todoList) { //printing table
                out.println("<tr><td><b>" + task.getTaskSubject() + "</b></td><td>" + task.getTaskDetails() + "</td></tr><br>");
            }
            out.println("<table>");
            out.println("<br><br><h3><a href=\"addToDo.html\">Add more ToDo tasks to the list</a></h3>");
            out.println("</body>");
            out.println("</html>");
        }
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
