package com.utn.api.email;


import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class UserTest extends TestCase {

    User user;
    User user1;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        user = new User();
        user.setUserName("galli12");
        user.setPass("asdasdasd");
        user.setName("Jose");
        user.setSurname("Alberti");
        user.setAdress("Libertad 3245");
        user.setPhone("2262728394");
        user.setCity("Mar del Plata");
        user.setState("Buenos Aires");
        user.setCountry("Argentina");
        user.setEmail("jose@hotmail.com");
        user.setDeleted(false);
        user.setId(67);
        user1=new User("galli12","asdasdasd","Jose","Albertii","Libertad 3245"
                ,"2262728394","Mar del Plata","Buenos Aires","Argentina","jose@hotmail.com");

    }

    @Test
    public void testAdress() {
        assertEquals("test adress", user.getAdress(), "Libertad 3245");
    }

    @Test
    public void testConstructor(){
        user1.setId(67);
        user1.getUserName();
        assertTrue(!user1.equals(user));
    }

    @Test
    public void testHashCode(){
        assertTrue(user.hashCode() != user1.hashCode());
    }

    @Test
    public void testId() {
        assertEquals("test id", user.getId(), 67);
    }


    @Test
    public void testSurname() {
        assertEquals("test surname", user.getSurname(), "Alberti");
    }


    @Test
    public void testName() {
        assertEquals("test name", user.getName(), "Jose");
    }


    @Test
    public void testPassword() {
        assertEquals("test password", user.getPass(), "asdasdasd");
    }


    @Test
    public void testPhone() {
        assertEquals("test phone", user.getPhone(), "2262728394");
    }


    @Test
    public void testEmail() {
        assertEquals("test email", user.getEmail(), "jose@hotmail.com");
    }


    @Test
    public void testCountry() {
        assertEquals("test country", user.getCountry(), "Argentina");
    }


    @Test
    public void testState() {
        assertEquals("test state", user.getState(), "Buenos Aires");
    }


    @Test
    public void testCity() {
        assertEquals("test city", user.getCity(), "Mar del Plata");
    }

    @Test
    public void testEliminado() {
        assertEquals("test deleted", user.isDeleted(), false);
    }
}






