package com.utn.api.email;

import com.utn.api.email.dao.DaoUsers;


public class User {
    private DaoUsers daoUsers;
    private String userName;
    private String pass;
    private String name;
    private String surname;
    private String adress;
    private String phone;
    private String city;
    private String state;
    private String country;
    private String email;
    private boolean eliminado;
    private int id;


    public User(){
        userName="";
        pass="";
        name="";
        surname="";
        adress="";
        phone="";
        city="";
        state="";
        country="";
        email="";
        eliminado=false;
        id=0;
    }

    public User(String userName, String pass, String name, String surname,
    String adress, String phone, String city, String state, String country,String email){
        this.userName=userName;
        this.pass=pass;
        this.name=name;
        this.surname=surname;
        this.adress=adress;
        this.phone=phone;
        this.city=city;
        this.state=state;
        this.country=country;
        this.email=email;
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


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
