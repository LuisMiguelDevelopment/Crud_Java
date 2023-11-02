/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_java;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


/**
 *
 * @author luism
 */
public class ConexionSqlServer {
    
    Connection conexion = null;
    
    String usuario = "info";
    String contrasena = "1234";
    String db = "CRUD_JAVA";
    String ip = "localhost";
    String puerto = "1433";
    
    public Connection obtenerConexion(){
        try{
           String cadena = "jdbc:sqlserver://"+ip+":"+puerto+";databaseName="+db+";encrypt=true;trustServerCertificate=true;";
           conexion = DriverManager.getConnection(cadena , usuario , contrasena);
           //JOptionPane.showMessageDialog(null, "Conexion exitosa");
           System.out.println("Conexion exitosa");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error conexion"+e.toString());
            e.printStackTrace();
        }
        return conexion;
    }
}
