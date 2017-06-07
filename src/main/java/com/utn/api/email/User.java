package com.utn.api.email;

import com.utn.api.email.dao.DaoUsers;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class User {
    private DaoUsers daoUsers;
    private String userName;
    private String pass;
    private String name;
    private String surname;
    private boolean eliminado;
    private int id;

    public User(){
        userName="";
        pass="";
        name="";
        surname="";
        eliminado=false;
        try {
            id = daoUsers.lastInsertId();
        }catch(Exception s ){
            s.printStackTrace();
        }
    }

    public User(String userName, String pass, String name, String surname){
        this.userName=userName;
        this.pass=pass;
        this.name=name;
        this.surname=surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (eliminado != user.eliminado) return false;
        if (id != user.id) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (pass != null ? !pass.equals(user.pass) : user.pass != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return surname != null ? surname.equals(user.surname) : user.surname == null;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (eliminado ? 1 : 0);
        result = 31 * result + id;
        return result;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
