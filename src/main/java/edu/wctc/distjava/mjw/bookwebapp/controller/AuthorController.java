/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.mjw.bookwebapp.controller;

import edu.wctc.distjava.mjw.bookwebapp.model.Author;
import edu.wctc.distjava.mjw.bookwebapp.model.AuthorDao;
import edu.wctc.distjava.mjw.bookwebapp.model.AuthorService;
import edu.wctc.distjava.mjw.bookwebapp.model.IAuthorDao;
import edu.wctc.distjava.mjw.bookwebapp.model.MySqlDataAccess;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mitchell
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/authorController"})
public class AuthorController extends HttpServlet {

    public static final String ACTION = "action";
    public static final String LIST_ACTION = "list";

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
        String destination = "/authorList.jsp"; //defualt destination

        try {
            String action = request.getParameter(ACTION);

            IAuthorDao dao = new AuthorDao(
                    "com.mysql.jdbc.Driver", //driver
                    "jdbc:mysql://localhost:3306/book", //url
                    "root", //username
                    "admin", //password
                    new MySqlDataAccess()
            );

            AuthorService authorService = new AuthorService(dao);

            List<Author> authorList = null;

            if (action.equalsIgnoreCase(LIST_ACTION)) {
                authorList = authorService.getAuthorList();
                request.setAttribute("authorList", authorList);
            }

        } catch (Exception e) {
            destination = "/authorList.jsp";

        }
        
      RequestDispatcher view = request.getRequestDispatcher(destination);
      view.forward(request, response);
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
