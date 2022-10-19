/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author utsav
 */
public class LoginCheckServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginCheckServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            String nm = request.getParameter("txtuname");
            String pss = request.getParameter("txtpassw");
            try{
                
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaProject","root","");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from Project where email='"+nm+"' and pass='"+pss+"';");
           
            if(rs.next())
            {
                out.println("<div class=\"container col-5 mt-5\">");
                out.println("<div class=\"mb\">");
                out.println("<p class=\"fw-bold\">Login Success.</p>");
                out.println("</div>");
                out.println("</div>");
                RequestDispatcher rd = request.getRequestDispatcher("home.html");
                rd.include(request, response);
            }
            else{
                out.println("<div class=\"container col-5 mt-5\">");
                out.println("<div class=\"mb\">");
                out.println("Incarnate Email or Password");
                out.println("</div>");
                out.println("</div>");
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.include(request, response);
            }
            
            con.close();
            }
            catch (ClassNotFoundException | SQLException e){
                out.println(e);
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginCheckServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginCheckServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginCheckServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginCheckServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
