package com.utn.api.email;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Control {
    @RequestMapping("/")
    @ResponseBody
    public String sayHello(){
        return "Hello world!!";
    }
    //todo golpes con la api


}
