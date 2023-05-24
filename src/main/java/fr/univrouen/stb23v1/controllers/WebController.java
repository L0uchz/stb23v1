package fr.univrouen.stb23v1.controllers;

import fr.univrouen.stb23v1.model.STB23Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    //@Autowired
    //private STB23Repository stb23;
    @GetMapping("/")
    public String index(){
        return "index." ;
    }

    //A complèter a la fin
    @GetMapping("/help")
    public String help(){
        return "help";
    }


    @GetMapping("/resume/xml")
    public String getListSTBinXML(Model model){

    }

    @GetMapping("/resume/html")
    public String getListItemsinHTML(Model model) {

    }
}



