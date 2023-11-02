
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
        // Creamos una instancia de la clase ConexionSqlServer para establecer la conexión a la base de datos.
        ConexionSqlServer con = new ConexionSqlServer();
        Connection conexion = con.obtenerConexion(); // Obtenemos la conexión a la base de datos.

        // Creamos un objeto de la clase Usuario con algunos datos.
        Usuario usuario = new Usuario ("YeyOGz", "Yeison", "yeisong.a9701@gmail.com");
        
        // Llamamos a la función insertarUsuario para insertar el usuario en la base de datos.
        // insertarUsuario(conexion, usuario);
        
        // Llamamos a la función obtenerUsuarios para obtener y mostrar los usuarios de la base de datos.
        obtenerUsuarios(conexion);
    }
    
    public static void insertarUsuario(Connection conexion, Usuario usuario){
        // Definimos una consulta SQL para insertar un usuario en la tabla USUARIOS.
        String sql = "INSERT INTO USUARIOS VALUES(?,?,?);";
        
        try {
            // Creamos un objeto PreparedStatement para ejecutar la consulta SQL.
            PreparedStatement statement = conexion.prepareStatement(sql);
            // Establecemos los valores de los parámetros en la consulta.
            statement.setString(1, usuario.usuario);
            statement.setString(2, usuario.nombre);
            statement.setString(3, usuario.correo);
            // Ejecutamos la consulta y obtenemos el número de registros afectados.
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
        // Definimos una consulta SQL para seleccionar todos los usuarios de la tabla USUARIOS.
        String sql = "SELECT * FROM USUARIOS";
        
        try{
            // Creamos un objeto Statement para ejecutar la consulta SQL.
            Statement statement = conexion.createStatement();
            // Ejecutamos la consulta y obtenemos un conjunto de resultados.
            ResultSet rs = statement.executeQuery(sql);
            
            while(rs.next()){
                // Recorremos los resultados y creamos objetos Usuario con los datos obtenidos.
                listaUsuarios.add(
                    new Usuario(
                        rs.getString("usuario"),
                        rs.getString("nombre"),
                        rs.getString("correo"))
                );
            }    
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        // Llamamos a la función mostrarLista para mostrar la lista de usuarios.
        mostrarLista(listaUsuarios);
        
        return listaUsuarios;
    }
    
    public static void mostrarLista(List<Usuario> listaUsuarios){
        String cadena = "";
        
        for(Usuario usuario: listaUsuarios){
            // Construimos una cadena con los datos de cada usuario.
            cadena = cadena + "Usuario: " + usuario.usuario + ", Nombre: " + usuario.nombre + ", correo: " + usuario.correo + "\n";
        }
        System.out.println(cadena);
        
        JOptionPane.showMessageDialog(null, cadena);
    }   
}