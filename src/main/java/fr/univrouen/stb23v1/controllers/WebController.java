package fr.univrouen.stb23v1.controllers;

import fr.univrouen.stb23v1.model.STB;
import fr.univrouen.stb23v1.model.STB23Repository;
import fr.univrouen.stb23v1.util.TransformXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.TransformerException;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private STB23Repository stb23;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/help")
    public String help(){
        return "help";
    }


    @GetMapping("/resume/html")
    public String getListSTBinHTML(Model model) {

        TransformXML t = new TransformXML();
        List<STB> stbs = stb23.getSTB();
        String s = "<p:stb lang=\"fr-FR\" xmlns:p=\"http://univrouen.fr/stb23\" >";
        s +="<table>:\n";
        s += "  <tr>\n";
        s += "      <th> Id </th>\n";
        s += "      <th> Titre </th>\n";
        s += "      <th> Description </th>\n";
        s += "      <th> Date de Validation de la STB </th>\n";
        s += "      <th> Nom de l'entité client </th>\n";
        s += "  </tr>\n";
        for (STB stbf : stbs) {
            s += "  <tr>\n";
            s += "      <td>" + stbf.getId() + "</td>\n";
            s += "      <td>" + stbf.getTitle() + "</td>\n";
            s += "      <td>" + stbf.getDescription() + "</td>\n";
            s += "      <td>" + stbf.getDateValidation() + "</td>\n";
            s += "      <td>" + stbf.getClientName() + "</td>\n";
            s += "  </tr>\n";
        }
        s +="</table>:\n";
        s += "</p:stb>";

        try {
            t.transformXSL(s, true);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return "stb23.result";

    }

    @GetMapping("/resume/html/{id}")
    public String getSTBinHTML(Model model, @PathVariable int id) {
        STB stb = stb23.getSTB(id);
        if (stb == null) {
            throw new AssertionError("La ressource demandé n'a pas été trouvé");
        }
        TransformXML t = new TransformXML();
        String s = "<stb>\n";
        s += "<id>" + stb.getId() + "</id>\n";
        s += "<title>" + stb.getTitle() + "</title>\n";
        s += "<version>" + stb.getVersion() + "</version>\n";
        s += "<published>" + stb.getPublished() + "</published>\n";
        s += "<description>" + stb.getDescription() + "</description>\n";
        s += "<dateValidation>" + stb.getDateValidation() + "</dateValidation>\n";
        s += "<client>" + stb.getClientName() + "</client>\n";
        s += "<team>" + stb.getTeam() + "</team>\n";
        s += "<features>" + stb.getFeatures() + "</features>\n";

        s += "</stb>\n";
        System.out.println(s);
        try {
            t.transformXSL(s, false);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return "stb23.result";

    }

    @ExceptionHandler(AssertionError.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public String exception(final Throwable throwable, final Model model) {
        String errorMessage = (throwable != null ? throwable.getCause().getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        return "notFoundError";
    }

}



