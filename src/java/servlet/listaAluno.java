/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrador
 */

import BD.conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Administrador
 */
@WebServlet(name = "listaAluno", urlPatterns = {"/listaAluno"})

public class listaAluno extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            conexao conecta = new conexao();
            ResultSet res = conecta.listaAluno();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");


            //out.println("<link rel='stylesheet' type='text/css' href='"+request.getContextPath()+"'css/bootstrap.min.css'/>");
            out.println("<link href='css/bootstrap.min.css' rel='stylesheet' type='text/css'/>");
            out.println("<link href='style.css' rel='stylesheet' type='text/css'/>");
            
            out.println("<title>Servlet listaAluno</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            
            out.println("<table class='table table-hover'>");
                out.println("<thead>");
                  out.println("<tr>");
                    out.println("<th scope='col'>Nome</th>");
                  out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");
                  
                    //<td>Mark</td>
                    
                        try {
                            while(res.next()){
                                String rm = res.getString("rmaluno");
                                String nome = res.getString("nomealuno");
                                String endereco = res.getString("endaluno");
                                String foto = res.getString("fotoaluno");
 
                                out.println("<tr>");
                                out.println("<th scope='row'>1</th>");

                                out.println("<td><img src=fotos/"+foto+".png width='100' height='100'></td>");
                                out.println("<td>"+rm+"</td>");
                                out.println("<td>"+nome+"</td>");
                                out.println("<td>"+endereco+"</td>");
                                out.println("</tr>");
                                //out.println(nome+"<br/>");
                            }
                            } catch (SQLException ex) {
                                Logger.getLogger(listaAluno.class.getName()).log(Level.SEVERE, null, ex);
                        }
                out.println("</tbody>");
            out.println("</table>");
            
            out.println("<h1>Servlet listaAluno at " + request.getContextPath() + "</h1>");
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
