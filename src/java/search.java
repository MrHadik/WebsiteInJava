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
 * @author hardik
 */
public class search extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\" crossorigin=\"anonymous\">\n");
            out.println("<title>Servlet LoginCheckServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            String nm = request.getParameter("txtuname");
            try{
                
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaProject","root","");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from Project where email='"+nm+"'");
                
                if(rs.next()){
                    out.println("<div class=\"container mt-5\">");
                    out.println("<div class=\"mb\">");
                    out.println("<table class=\"table\">");
                    out.println("<thead><t><th scope=\"col\">email</th><th scope=\"col\">pass</th><th scope=\"col\">name</th><th scope=\"col\">sarname</th></thead>");
                    out.println("<tbody>");
                    
                    out.println("<tr>");
                    out.println("<td>"+rs.getString("email")+"</td>");
                    out.println("<td>"+rs.getString("pass")+"</td>");
                    out.println("<td>"+rs.getString("name")+"</td>");
                    out.println("<td>"+rs.getString("sarname")+"</td>");
                    
                    out.println("</tr>");
                    out.println("<tbody>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</table>");
                    RequestDispatcher rd = request.getRequestDispatcher("search.html");
                    rd.include(request, response);
                
                }
                else
                {
                out.println("<div class=\"container col-5 mt-5\">");
                out.println("<div class=\"mb\">");
                out.println("No Data Found!");
                out.println("</div>");
                out.println("</div>");
                RequestDispatcher rd = request.getRequestDispatcher("search.html");
                rd.include(request, response);
                }
                
                out.println("</div>");
                out.println("</div>");
                out.println("</table>");
                
        
            con.close();
            }
            catch (ClassNotFoundException | SQLException e){
                out.println(e);
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
