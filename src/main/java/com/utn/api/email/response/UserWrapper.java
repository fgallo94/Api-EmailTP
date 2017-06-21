package com.utn.api.email.response;


import com.fasterxml.jackson.annotation.JsonProperty;

    public class UserWrapper {

        @JsonProperty("name")
        String name;
        @JsonProperty("surname")
        String surname;
        @JsonProperty("email")
        String email;

        //Constructor
        public UserWrapper(){}

        //Getters and Setters

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

