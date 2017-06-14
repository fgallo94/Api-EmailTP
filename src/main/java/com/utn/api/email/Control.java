package com.utn.api.email;


import com.utn.api.email.dao.DaoMessages;
import com.utn.api.email.dao.DaoUsers;
import com.utn.api.email.response.LoginResponseWrapper;
import com.utn.api.email.util.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class Control {

    @Autowired
    private DaoMessages daoMessages;
    @Autowired
    private DaoUsers daoUsers;

    @Autowired
    SessionData sessionData;

    @RequestMapping(value = "/api/Message/Send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
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
    public ResponseEntity inbox(@RequestHeader("usuario") String userName) {
        try {
            User u =daoUsers.byUser(userName);
            daoMessages.inbox(u);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/api/Message/Delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
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
    public ResponseEntity listTrash(@RequestHeader("usuario") String userName) {
        try {
            User u =daoUsers.byUser(userName);
            daoMessages.trash(u);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/api/User/ListUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity listUser() {
        try {
            daoUsers.listUser();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/api/User/AddUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody User user) {
        try {
            daoUsers.addUser(user);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/User/Delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@RequestBody User user) {
        try {
            daoUsers.delete(user);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }




    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity getById(@RequestParam("user") String nombreUsuario, @RequestParam("pwd") String pwd){
        try {
            User u = daoUsers.byUserName(nombreUsuario, pwd);
            if (null != u) {
                String sessionId = sessionData.addSession(u);
                return new ResponseEntity<LoginResponseWrapper>(new LoginResponseWrapper(sessionId), HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.CONFLICT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }


    @RequestMapping(value="/logout",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity getById(@RequestHeader("sessionid") String sessionId) {
        sessionData.removeSession(sessionId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }





}
