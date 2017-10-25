/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.mjw.bookwebapp.controller;

import static edu.wctc.distjava.mjw.bookwebapp.controller.AuthorController.ACTION;
import static edu.wctc.distjava.mjw.bookwebapp.controller.AuthorController.CREATE_NEW;
import edu.wctc.distjava.mjw.bookwebapp.model.AuthorDao;
import edu.wctc.distjava.mjw.bookwebapp.model.AuthorService;
import edu.wctc.distjava.mjw.bookwebapp.model.IAuthorDao;
import edu.wctc.distjava.mjw.bookwebapp.model.MySqlDataAccess;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mitchell
 */
@WebServlet(name = "AuthorAddAuthorController", urlPatterns = {"/authorAddAuthorController"})
public class AuthorAddAuthorController extends HttpServlet {
     public static final String ACTION = "action";
     
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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        
        response.setContentType("text/html;charset=UTF-8");
        String destination = "/addAuthor.jsp"; //defualt destination
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
              
            String action = request.getParameter(ACTION);

            IAuthorDao dao = new AuthorDao(
                    "com.mysql.jdbc.Driver", //driver
                    "jdbc:mysql://localhost:3306/book", //url
                    "root", //username
                    "admin", //password
                    new MySqlDataAccess()
            );
            
            AuthorService authorService = new AuthorService(dao);
            
            if(action.equalsIgnoreCase("add")){
                
                List<String> colNames = Arrays.asList( "author_name", "date_added" );
                
                     String authorName = request.getParameter("authorName");
                     String dateAdded  = request.getParameter("dateAdded");
                           
                     System.out.println("Test: " + authorName );
                     
                List<Object> colValues = Arrays.asList( authorName , dateAdded );
                
//                String[] authorName = request.getParameterValues("authorName");
//                String[] dateAdded = request.getParameterValues("dateAdded");
                
                    
              //  authorService.createNewAuthor( colNames ,  colValues );
                
                
                
                }
            
        }
//        catch(ClassNotFoundException e){
//            destination = "/authorList.jsp";
//        }
        
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorAddAuthorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorAddAuthorController.class.getName()).log(Level.SEVERE, null, ex);
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
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorAddAuthorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorAddAuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
