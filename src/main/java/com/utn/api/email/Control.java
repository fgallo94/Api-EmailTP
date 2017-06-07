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

    @RequestMapping(value = "/Message/Send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendMessage(@RequestBody Message message){
        try {
            daoMessages.send(message);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/Message/Inbox",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<Message> inbox()
    {
        return daoMessages.inbox();
    }


    //todo golpes con la api


}
