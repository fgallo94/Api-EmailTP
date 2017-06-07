package com.utn.api.email.dao;

import com.utn.api.email.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.websocket.Session;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class DaoUsers {
    @Autowired
    private DaoConexion conn;


    public User byUserName(String user,String pwd) throws Exception {
        String sq = "select * from Users where username=? and pass=?";
        User u= new User();
        try {
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            st.setString(1, user);
            st.setString(2,pwd);
            ResultSet rs = st.executeQuery();
            if (rs == null) {
                System.out.println(" No hay registros en la base de datos");
            }
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setSurname(rs.getString("surname"));
            u.setPass(rs.getString("password"));
            u.setUserName(rs.getString("username"));
            u.setEliminado(rs.getBoolean("deleted"));
            u.setAdress(rs.getString("adress"));
            u.setPhone(rs.getString("phone"));
            u.setCity(rs.getString("city"));
            u.setState(rs.getString("state"));
            u.setCountry(rs.getString("country"));
            u.setEmail(rs.getString("email"));
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                conn.desconectar();
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
        return u;
    }

    public User byUser(String user) throws Exception {
        String sq = "select * from Users where username=?";
        User u= new User();
        try {
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            if (rs == null) {
                System.out.println(" No hay registros en la base de datos");
            }
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setSurname(rs.getString("surname"));
            u.setPass(rs.getString("password"));
            u.setUserName(rs.getString("username"));
            u.setEliminado(rs.getBoolean("deleted"));
            u.setAdress(rs.getString("adress"));
            u.setPhone(rs.getString("phone"));
            u.setCity(rs.getString("city"));
            u.setState(rs.getString("state"));
            u.setCountry(rs.getString("country"));
            u.setEmail(rs.getString("email"));
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                conn.desconectar();
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
        return u;
    }

    public User byId(int user) throws Exception {
        String sq = "select * from Users where id=?";
        User u= new User();
        try {
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            st.setInt(1, user);
            ResultSet rs = st.executeQuery();
            if (rs == null) {
                System.out.println(" No hay registros en la base de datos");
            }
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setSurname(rs.getString("surname"));
            u.setPass(rs.getString("password"));
            u.setUserName(rs.getString("username"));
            u.setEliminado(rs.getBoolean("deleted"));
            u.setAdress(rs.getString("adress"));
            u.setPhone(rs.getString("phone"));
            u.setCity(rs.getString("city"));
            u.setState(rs.getString("state"));
            u.setCountry(rs.getString("country"));
            u.setEmail(rs.getString("email"));
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                conn.desconectar();
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
        return u;
    }





    public void addUser(User user) throws Exception {
        String sq = "insert into Users(id,username,password,name,surname,deleted,adress,phone,city,state,country,email) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            st.setInt(1, user.getId());
            st.setString(2, user.getUserName());
            st.setString(3, user.getPass());
            st.setString(4, user.getName());
            st.setString(5,user.getSurname());
            st.setBoolean(6,user.isEliminado());
            st.setString(7,user.getAdress());
            st.setString(8,user.getPhone());
            st.setString(9,user.getCity());
            st.setString(10,user.getState());
            st.setString(11,user.getCountry());
            st.setString(12,user.getEmail());
            st.executeUpdate();
        }
        //Se ejecuta excepcion en caso de error
        catch (SQLException es) {
            es.printStackTrace();
        }
        //con clausula finally nos aseguramos que se cierre la conexion a la base de datos
        finally {
            try {
                conn.desconectar();
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }
    public ArrayList<User> listUser() throws Exception {
        String sq = "select * from Users where deleted=false";
        ArrayList<User> lista = new ArrayList<User>();
        try {
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            ResultSet rs = st.executeQuery();
            if (rs == null) {
                System.out.println(" No hay registros en la base de datos");
            }
            while (rs.next()) {
                User u=new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setPass(rs.getString("password"));
                u.setUserName(rs.getString("username"));
                u.setEliminado(rs.getBoolean("deleted"));
                u.setAdress(rs.getString("adress"));
                u.setPhone(rs.getString("phone"));
                u.setCity(rs.getString("city"));
                u.setState(rs.getString("state"));
                u.setCountry(rs.getString("country"));
                u.setEmail(rs.getString("email"));
                lista.add(u);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                conn.desconectar();
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
        return lista;
    }

    public void delete(User user) throws Exception{
        String sq="update Users set deleted=true where id=?";
        try{
            conn.conectar();
            PreparedStatement st= conn.getConn().prepareStatement(sq);
            st.setInt(1,user.getId());
            st.executeUpdate();
        }catch(SQLException s){
            s.printStackTrace();
        }finally {
            try{
                conn.desconectar();
            }catch(Exception x){
                x.printStackTrace();
            }
        }
    }


    public int lastInsertId() throws Exception{
        String sq="Select Last_Insert_Id() into Users";
        int id = 0;
        try{
            conn.conectar();
            PreparedStatement st= conn.getConn().prepareStatement(sq);
            ResultSet rs = st.executeQuery();
            if (rs == null) {
                System.out.println(" No hay registros en la base de datos");
            }
            while (rs.next()) {
                id = rs.getInt("id");
            }
            }catch(SQLException s){
            s.printStackTrace();
        }finally {
            try{
                conn.desconectar();
            }catch(Exception x){
                x.printStackTrace();
            }
        }
        return id;
    }


}
