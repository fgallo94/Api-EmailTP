package com.utn.api.email;


import com.utn.api.email.dao.DaoMessages;
import com.utn.api.email.dao.DaoUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Enumeration;

@Controller
public class Control {
    @Autowired
    private DaoMessages daoMessages;
    @Autowired
    private DaoUsers daoUsers;

// Federido: los endpoints no se diferencian por agregar una expresion  regular mas...
// la idea es que la peticion base sea api/Message... si le pegas por get, trae los mensajes... si le pegas por post, envia un mensaje
// y si le pegas por DELETE, enviando un id de mensaje, lo borra... 
// tenes que hacer asi con todos los request solicitados...    
    
    @RequestMapping(value = "/api/Message/Send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendMessage(@RequestBody Message message){
        try {
            daoMessages.send(message);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/Message/Inbox",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity inbox(){
        try {
            //TODO falta poder traer el user
            daoMessages.inbox();
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/api/Message/Delete",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deleteMessage(@RequestBody Message message){
        try {
            daoMessages.delete(message);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/api/Message/Trash",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity listTrash(){
        try {
            //TODO falta poder traer el user
            daoMessages.trash();
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/api/User/ListUser",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity listUser(){
        try {
            daoUsers.listUser();
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/api/User/AddUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody User user){
        try {
            daoUsers.addUser(user);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/User/Delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@RequestBody User user){
        try {
            daoUsers.delete(user);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }


    //TODO /Login
    //TODO /Logout





}
