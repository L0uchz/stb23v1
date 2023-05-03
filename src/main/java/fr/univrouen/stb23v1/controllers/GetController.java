package fr.univrouen.stb23v1.controllers;

import fr.univrouen.stb23v1.model.STB;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
public class GetController {

     @GetMapping("/resume")
    public String getListSTBinXML(){
         return "Envoi de la liste des STB";
     }


     @GetMapping("/stbid")
    public String getSTBinXML(
         @RequestParam(value = "id") String texte) {
         return ("Détail du contenu STB demandé" + texte);
     }

     @GetMapping("/test")
    public String getSTBtestinXML(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "titre") String title){

         return ("Test :<br> id = " + id + "<br>titre = " + title);
     }

     @RequestMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody STB getXML() {
         STB stb = new STB(123,"Test STB","2023-04-01T14:22:33","stb de test","2023-04-01T14:22:30","Monsieur Arnaud");
         return stb;
     }
}
