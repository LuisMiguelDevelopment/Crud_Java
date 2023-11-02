
package com.mycompany.crud_java;

import java.sql.Connection;
import com.mycompany.crud_java.ConexionSqlServer;

public class Crud_Java {

    public static void main(String[] args) {
        ConexionSqlServer con = new ConexionSqlServer();
        Connection conexion = con.obtenerConexion();
    }
}
