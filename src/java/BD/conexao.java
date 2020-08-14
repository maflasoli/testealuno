/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;
/**
 *
 * @author Administrador
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class conexao {
    
    private Connection conecta;
    
    public conexao(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException cnfe){
            System.out.println("Classe não encontrada..");
        }
        try{
            conecta=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/quim1","root","19twostars");
            //conecta=DriverManager.getConnection("jdbc:mysql://192.168.106.21/quim1","root","root");
        }catch(SQLException sqle){
            System.out.println("Deu pau...");
        }
    }

    public void cadAluno(String rmaluno, String nomealuno, String endaluno, String fotoaluno){
        //conexao con = new conexao();
        //System.out.println("Entrou no cadastro");
        try{
           PreparedStatement pst = conecta.prepareStatement("INSERT INTO testealuno(rmaluno,nomealuno,endaluno,fotoaluno) VALUES ("+rmaluno+",'"+nomealuno+"','"+endaluno+"','"+fotoaluno+"')"); 
           pst.executeUpdate();
           pst.close();
           //conecta.close();
       }catch(SQLException sqle){
            System.out.println("Deu pau... cadAluno");
       }
    }
    
    public ResultSet listaAluno(){
        //conexao con = new conexao();
        ResultSet lista=null;
        try{
            PreparedStatement ps = conecta.prepareStatement("select * from testealuno");
            lista = ps.executeQuery();
            //lista.close();
            //conecta.close();
        }catch(SQLException sqle){
            System.out.println("Deu pau... listaAluno");
        }
        return lista;
    }   
    
    
    /*public static void main(String[] args ){
        try{
            conexao con = new conexao();
            ResultSet res = con.listaAluno();
            while(res.next()){
                String nome = res.getString("nomealuno");
                System.out.println(nome);
            }
            //res.close();
            
        }catch(Exception e){
            System.out.println("Não conectado..");
        }
    }*/
}
