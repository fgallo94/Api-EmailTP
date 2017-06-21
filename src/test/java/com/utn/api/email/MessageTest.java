package com.utn.api.email;


import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class MessageTest extends TestCase {

    Message message;
    User user;
    User user1;
    Message message1;
    Message message2;
    @Before
    public void setUp() throws Exception{
        user=new User();
        message=new Message();
        user1 = new User();
        message.setFecha("19/06/2017");
        message.setUser(user);
        message.setRecipient(user1);
        message.setBody("cuerpo");
        message.setSubject("asunto");
        message1=message;
        message2=new Message(user1,"23/04/2017",user,"no asunto","no cuerpo");
    }

    @Test
    public void testEquals(){
        assertTrue(message.equals(message1));
    }

    @Test
    public void testNotEquals1(){
        message2.setUser(user);
        message2.setFecha("19/06/2017");
        message2.setSubject("asunto");
        assertTrue(!message.equals(message2));
    }

    @Test
    public void testConstructor(){
        assertEquals("test constructor",message=new Message(user,"19/06/2017",user1,"asunto","cuerpo"),message);
    }
    @Test
    public void testFecha(){
        assertEquals("test fecha",message.getFecha(),"19/06/2017");
    }
    @Test
    public void testUser(){
        assertEquals("test user",message.getUser(),user);
    }
    @Test
    public void testRecipent(){
        assertEquals("test recipent",message.getRecipient(),user1);
    }
    @Test
    public void testBody(){
        assertEquals("test body",message.getBody(),"cuerpo");

    }
    @Test
    public void testSubject(){
        assertEquals("test subject",message.getSubject(),"asunto");
    }
}
