package com.utn.api.email.dao;

import com.utn.api.email.Message;
import com.utn.api.email.User;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

@Repository
public class DaoMessages {
    //Genera una instancia de la clase Conexion
    private DaoConexion conn = DaoConexion.getInstancia();
    private DaoUsers daoUsers;

    public void send(Message message) throws Exception {
        String sq = "insert into Messages(id_usuario,subject,body,id_usuario_destinatario) values (?,?,?,?)";

        try {
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            st.setInt(1, message.getUser().getId());
            st.setString(2, message.getSubject());
            st.setString(3, message.getBody());
            st.setInt(4, message.getRecipient().getId());
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
    //TODO ORDER BY FECHA
    public ArrayList<Message> inbox(User user) throws Exception {
        String sq = "select * from Messages where id_usuario=?";
        ArrayList<Message> lista = new ArrayList<Message>();
        try {
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            st.setInt(1, user.getId());
            ResultSet rs = st.executeQuery();
            if (rs == null) {
                System.out.println(" No hay registros en la base de datos");
            }
            while (rs.next()) {
                Message m = new Message();
                m.setUser(daoUsers.byId(rs.getInt("id_usuario")));
                m.setRecipient(daoUsers.byId(rs.getInt("id_usuario_destinatario")));
                m.setBody(rs.getString("body"));
                Timestamp timestamp = rs.getTimestamp("fecha");
                java.util.Date date = timestamp;
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(date);
                m.setFecha(currentTime);
                m.setSubject(rs.getString("subject"));
                lista.add(m);
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

    public void delete(Message message) throws Exception{
        String sq="update Messages set deleted=true where subject=? and body=?";
        try{
            conn.conectar();
            PreparedStatement st= conn.getConn().prepareStatement(sq);
            st.setString(1,message.getSubject());
            st.setString(2,message.getBody());
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
    //TODO ORDER BY FECHA
    public ArrayList<Message> trash(User user) throws Exception {
        String sq = "select * from Messages where deleted=true and id_usuario=?";
        ArrayList<Message> lista = new ArrayList<Message>();
        try {
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            st.setInt(1,user.getId());
            ResultSet rs = st.executeQuery();
            if (rs == null) {
                System.out.println(" No hay registros en la base de datos");
            }
            while (rs.next()) {
                Message m = new Message();
                m.setUser(daoUsers.byId(rs.getInt("id_usuario")));
                m.setRecipient(daoUsers.byId(rs.getInt("id_usuario_destinatario")));
                m.setBody(rs.getString("body"));
                Timestamp timestamp = rs.getTimestamp("fecha");
                java.util.Date date = timestamp;
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(date);
                m.setFecha(currentTime);
                m.setSubject(rs.getString("subject"));
                lista.add(m);
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
}
