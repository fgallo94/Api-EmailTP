package com.utn.api.email.dao;

import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Repository
public class DaoConexion {
    private Connection conn;
    private static DaoConexion instancia;

    //Patron Singleton
    public static DaoConexion getInstancia() {
        if (instancia == null) {
            instancia = new DaoConexion();
        }
        return instancia;
    }
    //Verifica los drivers en la conexion
    public DaoConexion() {
        try {

            this.verificarDriver();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //Conecta la base de datos con getConnection y los datos de nuestra base de datos, en caso de no poder ejectura la Excepcion
    public void conectar() throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/email", "root", "fede432405");
        } catch (SQLException e) {
            System.err.println("SQLexception: " + e.getMessage());
            throw e;
        }
    }
    //verifica con el class.forName si el driver es el correcto
    private void verificarDriver() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
            throw e;
        }
    }
    //Intenta desconectar la base de datos, en caso de no poder arroja excepcion
    public void desconectar() throws Exception {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    //get que retorna la conexion
    public Connection getConn() {
        return conn;
    }

}
