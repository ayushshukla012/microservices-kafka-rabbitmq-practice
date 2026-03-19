package net.javaguides.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody
@RestController
public class HelloWorldController {

    //GET Mapping method
    //To access: http://localhost:8080/hello-world
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "hello World";
    }

}
