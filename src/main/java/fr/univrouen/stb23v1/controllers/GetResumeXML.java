package fr.univrouen.stb23v1.controllers;

import fr.univrouen.stb23v1.model.STB;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class GetResumeXML {


    @RequestMapping(value = "/resume/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody String getResumeinXml(){

        return null;
    }

    //faux
    @GetMapping("/resume/xml")
    public String getResumeInXML(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "description") String desc,
            @RequestParam(value = "date") String dateValidation,
            @RequestParam(value = "client.name") String clientName){

        String resume ="";
        for(int i = 0; i < id ; ++i) {
            resume += "<br> Id = " + id
                    + "<br>titre = " + title
                    + "<br> Description = " + desc
                    + "<br> Date de validation = " + dateValidation
                    + "<br> Nom de l'entité client = " + clientName;
        }
        return resume;
    }
}
