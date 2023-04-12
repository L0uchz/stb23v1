package fr.univrouen.stb23v1.controllers;

import fr.univrouen.stb23v1.model.TestSTB;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
public class PostTest {

    @RequestMapping(value = "/testpost", method = RequestMethod.POST,
            consumes = "application/xml")
    public String postTest(@RequestBody String flux) {
        return ("<result><response>Message reçu : </reponse>" + flux + "</result>");
    }

    @PostMapping(value = "/poststb", produces =  MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String postSTB(){
        TestSTB stb = new TestSTB();
        return  stb.loadFileXML();
    }
}
