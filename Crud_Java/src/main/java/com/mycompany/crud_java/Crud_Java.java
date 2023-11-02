
package com.mycompany.crud_java;

import java.sql.Connection;
import com.mycompany.crud_java.ConexionSqlServer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Crud_Java {
    
    static List<Usuario> listaUsuarios = new ArrayList<Usuario>();

    public static void main(String[] args) {
        ConexionSqlServer con = new ConexionSqlServer();
        Connection conexion = con.obtenerConexion();
        
        Usuario usuario = new Usuario ("YeyOGz", "Yeison", "yeisong.a9701@gmail.com");
        
        //insertarUsuario(conexion, usuario);
        obtenerUsuarios(conexion);
        
    }
    
    public static void insertarUsuario(Connection conexion, Usuario usuario){
        
        String sql = "INSERT INTO USUARIOS VALUES(?,?,?);";
        
        try {
                PreparedStatement statement = conexion.prepareStatement(sql);
                statement.setString(1, usuario.usuario);
                statement.setString(2, usuario.nombre);
                statement.setString(3, usuario.correo);
                int registroAdd = statement.executeUpdate();
                          
                if (registroAdd > 0 ){
                    System.out.println("Registro Exitoso!");
                    JOptionPane.showMessageDialog(null, "Registro Exitoso!");
                }
         } catch (SQLException e) {
            e.printStackTrace();
         }
    }
    
    public static List<Usuario> obtenerUsuarios(Connection conexion){
        String sql = "SELECT * FROM USUARIOS";
        
        try{
          Statement statement = conexion.createStatement();
          ResultSet rs = statement.executeQuery(sql);
          
          while(rs.next()){
              listaUsuarios.add(
                new Usuario(
                        rs.getString("usuario"),
                        rs.getString("nombre"),
                        rs.getString("correo"))
              );
          }    
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        mostrarLista(listaUsuarios);
        
        return listaUsuarios;
    }
    
    public static void mostrarLista(List<Usuario>listaUsuarios ){
        String  cadena = "";
        
        for(Usuario usuario: listaUsuarios){
            cadena = cadena+"Usuario: "+usuario.usuario+", Nombre:"+usuario.nombre+", correo:"+usuario.correo+"\n";
        }
        System.out.println(cadena);
        
        JOptionPane.showMessageDialog(null , cadena);
        
    }   
    
}
