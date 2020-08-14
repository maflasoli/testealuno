/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BD.conexao;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.xml.bind.DatatypeConverter;



/**
 *
 * @author Administrador
 */
@WebServlet(name = "cadaluno", urlPatterns = {"/cadaluno"})
public class cadaluno extends HttpServlet {

    conexao con = new conexao();
    ResultSet lista=null;
    
    //Connection con;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String rm = request.getParameter("txtRmAluno");
            String nome = request.getParameter("txtNomeAluno");
            String endereco = request.getParameter("txtEndAluno");
                        
            String foto = request.getParameter("foto");//Será convertido em arquivo
           
            String arquivo = foto.split(",")[1];
            
            byte[] btDataFile;
            
            //String caminhoImagem = request.getServletContext().getRealPath("fotos")+File.separator;
            //String caminhoImagem = request.getContextPath();
            //String caminhoImagem = request.getServletContext().getRealPath("fotos")+File.separator;
            
            String caminhoImagem = request.getServletContext().getRealPath("fotos")+File.separator;
            String caminhoImagem2 = request.getContextPath()+"/web/fotos/";
            
            //TROCAR AS BARRAS "\" POR "/" POIS É COM A TAG IMG ABRE O CAMINHO....
            //DEU DOR DE CABEÇA POIS, QUANDO SE USA ALGUNS CARACTERES NESSA FUNÇÃO (REPLACEALL) ELA VERIFICA SE NÃO SE TRATA DE UM "REGEX(EXPRESSÃO REGULAR- TIPO "\n", QUE É PARA SALTAR PARA LINHA DE BAIXO )"
            //caminhoImagem = caminhoImagem.replaceAll("[\"]" , "/");
            
            btDataFile = DatatypeConverter.parseBase64Binary(arquivo);
            
            //out.println(caminhoImagem);
              
            //File of = new File(caminhoImagem2+rm+".png");
            File of = new File(caminhoImagem+rm+".png");
            //File of = new File(rm+".png");
            try (FileOutputStream osf = new FileOutputStream(of)) {
                osf.write(btDataFile);
                osf.flush();
                osf.close();// SE NÃO COLOCAR O CLOSE, ELE DEIXA O ARQUIVO ABERTO...
            }
            
            
            con.cadAluno(rm, nome, endereco, rm);
                    
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet cadAluno</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println(caminhoImagem);
            
            
            lista = con.listaAluno();
            
            try {
                while(lista.next()){
                    
                    String rmaluno = lista.getString("rmaluno");
                    String nomealuno = lista.getString("nomealuno");
                    String endaluno = lista.getString("endaluno");
                    String fotoaluno = lista.getString("rmaluno");
                    
                    
                     
                    //out.println("<br><img src="+caminhoImagem+fotoaluno+".png width='100' height='100'>+<br/>");
                    out.println("<br>"+rmaluno+"<br/>");
                    out.println("<br>"+nome+"<br/>");
                    out.println("<br>"+endaluno+"<br/>");
                    out.println("<br>"+rmaluno+"<br/><br/><br/>");
                    
                    //out.println("<br>"+caminhoImagem+"<br/>");
                    
                    out.println("<br>"+caminhoImagem+fotoaluno+".png <br/>");
                    
                    //out.println("<br><img src="+caminhoImagem+fotoaluno+".png width='100' height='100'>+<br/>");
                    
                    //out.println("<img src=/fotos"+fotoaluno+".png width='100' height='100'>");
                    
                    out.println("<img src="+caminhoImagem+fotoaluno+".png width='100' height='100'>");
                    out.println("<br>"+caminhoImagem+"<br/>");
                    out.println("<br>"+caminhoImagem2+"<br/>");
                    
                    
                    
                }
                //lista.close();

                /*out.println(rm);
                out.println(nome);
                out.println(endereco);
                out.println(rm);
                out.println(foto);*/
            } catch (SQLException ex) {
                Logger.getLogger(cadaluno.class.getName()).log(Level.SEVERE, null, ex);
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