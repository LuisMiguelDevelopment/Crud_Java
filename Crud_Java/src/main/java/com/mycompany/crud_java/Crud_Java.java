
package com.mycompany.crud_java;

import java.sql.Connection;
import com.mycompany.crud_java.ConexionSqlServer;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class Crud_Java {

    public static void main(String[] args) {
        ConexionSqlServer con = new ConexionSqlServer();
        Connection conexion = con.obtenerConexion();
        
        Usuario usuario = new Usuario ("YeyOGz", "Yeison", "yeisong.a9701@gmail.com");
        
        insertarUsuario(conexion, usuario);
        
    }
    
    public static void insertarUsuario(Connection conexion, Usuario usuario){
        
        String sql = "INSERT INTO usuarios VALUES(?,?,?);";
        
        try {
                PreparedStatement statement = conexion.prepareStatement(sql);
                statement.setString(1, usuario.usuario);
                
                int registroAdd = statement.executeUpdate();
                
                if (registroAdd > 0 ){
                    System.out.println("Registro Exitoso!");
                    JOptionPane.showMessageDialog(null, "Registro Exitoso!");
                }
         } catch (SQLExeption e) {
            e.printStackTrace();
         }
    }
}
