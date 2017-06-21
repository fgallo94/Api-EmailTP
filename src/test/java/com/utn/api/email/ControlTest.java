package com.utn.api.email;

import com.utn.api.email.dao.DaoMessages;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControlTest extends TestCase {
    DaoMessages daoMessages;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    User user;
    @Before
    public void setUp() throws Exception{

        conn= mock(Connection.class);
        ps= mock(PreparedStatement.class);
        rs =mock(ResultSet.class);
        daoMessages=new DaoMessages();
        user=mock(User.class);
    }

    @Test
    public void testSendMessage(){
        try{
            when(conn.prepareStatement("\"insert into Messages(id_usuario,subject,body,id_usuario_destinatario) values (?,?,?,?)\"")).thenReturn(ps);
            ps.setInt(1, 1);
            ps.setString(2, "asd");
            ps.setString(3, "asd");
            ps.setInt(4, 1);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
        }catch(Exception e){
            fail();
        }
    }

   /* @Test
    public void testTraerIdNull() {
        try {
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(false);
            ArrayList<Message> m = daoMessages.inbox(user);
            assertNull(m);

        } catch(Exception e){
            fail();
        }
    }
*/
    @Test
    public void testTraerException() {
        try {
            when(conn.prepareStatement(anyString())).thenThrow(new Exception());
            ArrayList<Message> m= daoMessages.inbox(user);
            fail();
        } catch(Exception e){
            assertTrue(true);
        }

    }

}
