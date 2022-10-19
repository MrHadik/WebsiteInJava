/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author hardik
 */
public class insert extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet insert</title>");            
            out.println("</head>");
            out.println("<body>");
            String nm = request.getParameter("txtemail");
            String npss = request.getParameter("txtpassw");
            String nn = request.getParameter("txtname");
            String ns = request.getParameter("txtsarname");
            
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaProject","root","");
                Statement stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO `Project`(`email`, `pass`, `name`, `sarname`) VALUES ('"+nm+"','"+npss+"','"+nn+"','"+ns+"')");
                out.println("<div class=\"container col-5 mt-5\">");
                out.println("<div class=\"mb\">");
                out.println("Data has been Inserted successfully");
                out.println("</div>");
                out.println("</div>");
                RequestDispatcher rd = request.getRequestDispatcher("insert.html");
                rd.include(request, response);
                
            con.close();
            }
            catch (ClassNotFoundException | SQLException e){
                out.println("<div class=\"container col-5 mt-5\">");
                out.println("<div class=\"mb\">");
                out.println("Data is Alredy in DataBase");
                out.println("</div>");
                out.println("</div>");
                RequestDispatcher rd = request.getRequestDispatcher("insert.html");
                rd.include(request, response);
            }
            
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
