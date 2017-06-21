package com.utn.api.email;


import com.utn.api.email.dao.DaoMessages;
import com.utn.api.email.dao.DaoUsers;
import com.utn.api.email.response.LoginResponseWrapper;
import com.utn.api.email.response.UserWrapper;
import com.utn.api.email.util.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class Control {

    @Autowired
    private DaoMessages daoMessages;
    @Autowired
    private DaoUsers daoUsers;
    @Autowired
    SessionData sessionData;

    @RequestMapping(value = "/api/Message/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendMessage(@RequestBody Message message) {

        try {
            daoMessages.send(message);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/api/Message/Inbox", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity inbox(@RequestHeader("user") String userName) {
        try {
            User u = daoUsers.byUser(userName);
            daoMessages.inbox(u);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/api/Message/", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deleteMessage(@RequestBody Message message) {
        try {
            daoMessages.delete(message);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/api/Message/Trash", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity listTrash(@RequestHeader("user") String userName) {
        try {
            User u = daoUsers.byUser(userName);
            daoMessages.trash(u);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }


    //TODO trabajar con un wrapper...

    @RequestMapping(value = "/api/User/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<UserWrapper>> listUser() {
        try {
            List<User> lista = daoUsers.listUser();
            return new ResponseEntity<List<UserWrapper>>(this.convertList(lista), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/api/User/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody User user) {
        try {
            daoUsers.addUser(user);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/User/", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@RequestBody User user) {
        try {
            daoUsers.delete(user);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity getById(@RequestHeader("user") String nombreUsuario, @RequestHeader("pwd") String pwd) {
        try {
            User u = daoUsers.byUserName(nombreUsuario, pwd);
            if (null != u) {
                String sessionId = sessionData.addSession(u);
                return new ResponseEntity<LoginResponseWrapper>(new LoginResponseWrapper(sessionId), HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity getById(@RequestHeader("sessionid") String sessionId) {
        sessionData.removeSession(sessionId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    private List<UserWrapper> convertList(List<User> users) {
        List<UserWrapper> userWrapperList = new ArrayList<UserWrapper>();
        for (User u : users) {
            userWrapperList.add(this.convertUser(u));
        }
        return userWrapperList;
    }

    private UserWrapper convertUser(User user) {
        UserWrapper u = new UserWrapper();
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setEmail(user.getEmail());
        return u;
    }

}
