package fr.univrouen.stb23v1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "Stb23 \n "+ "Version 1.1\n" + "N'Sonda Charles\n" ;
    }

    //A complèter a la fin
    @GetMapping("/help")
    public String help(){
        return "";
    }
}



