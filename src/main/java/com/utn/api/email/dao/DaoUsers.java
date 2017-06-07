package com.utn.api.email.dao;

import com.utn.api.email.User;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class DaoUsers {
    private DaoConexion conn = DaoConexion.getInstancia();

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
        String sq = "insert into Users(id,username,password,name,surname,deleted) values (?,?,?,?,?,?)";
        try {
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            st.setInt(1, user.getId());
            st.setString(2, user.getUserName());
            st.setString(3, user.getPass());
            st.setString(4, user.getName());
            st.setString(5,user.getSurname());
            st.setBoolean(6,user.isEliminado());
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
