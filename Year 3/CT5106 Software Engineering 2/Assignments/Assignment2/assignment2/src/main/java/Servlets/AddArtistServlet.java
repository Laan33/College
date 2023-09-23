/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import data.Artist;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
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
@WebServlet(name = "AddArtistServlet", urlPatterns = {"/AddArtistServlet"})
public class AddArtistServlet extends HttpServlet {

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
       
        HttpSession session = request.getSession();
        ArrayList<Artist> artistsInfo = (ArrayList<Artist>) session.getAttribute("artistsInfo");
        if (artistsInfo == null) { //creating a list if none found
            artistsInfo = new ArrayList<>();
        }
                
        //using the parameters to create a new Artist object
        String surname = request.getParameter("surname");
        String firstName = request.getParameter("firstName");
        int yearOfBirth = Integer.parseInt(request.getParameter("yearOfBirth"));
        String nationality = request.getParameter("nationality");
        int yearOfDeath = Integer.parseInt(request.getParameter("yearOfDeath"));
        String biography = request.getParameter("biography");
        String artistImage = request.getParameter("artistImage");
        

         //check if all fields are filled in
        boolean allFieldsFilled = true;
        String[] fields = {surname, firstName, String.valueOf(yearOfBirth), nationality, biography, artistImage};
        for (String field : fields) {
            if (!checkIfFilled(field)) {
                allFieldsFilled = false;
                break;
            }
        }
        if (allFieldsFilled) {
            Artist artist = new Artist(surname, firstName, nationality, yearOfBirth, yearOfDeath, biography, artistImage);
            artistsInfo.add(artist);

            session.setAttribute("artistsInfo", artistsInfo);
            RequestDispatcher dispatcher = request.getRequestDispatcher("displayArtists.jsp");
            dispatcher.forward(request, response);
        } else {
            // Not all fields are filled in, returning to AddArtistServlet
            request.getRequestDispatcher("/addArtist.jsp").forward(request, response);
            return;
        }       
       
    }  

    private boolean checkIfFilled(String item) {        
            if (item == null || item.isEmpty()) {
                return false;
            }        
        return true;
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
