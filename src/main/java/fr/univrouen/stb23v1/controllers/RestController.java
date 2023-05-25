package fr.univrouen.stb23v1.controllers;

import fr.univrouen.stb23v1.model.STB;
import fr.univrouen.stb23v1.model.STB23Repository;
import fr.univrouen.stb23v1.util.XmlValidator;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private STB23Repository stb23;

    @GetMapping(value = "/resume/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String getListSTBinXML() {
        List<STB> stbs = stb23.getSTB();
        String s = "<stb>\n";
        for (STB stbf : stbs) {
            s += "  <id>" + stbf.getId() + "</id>\n";
            s += "    <title>" + stbf.getTitle() + "</title>\n";
            s += "    <description>" + stbf.getDescription() + "</description>\n";
            s += "    <dateValidation>" + stbf.getDateValidation() + "</dateValidation>\n";
            s += "    <client>" + stbf.getClientName() + "</client>\n";
        }

        return s + "</stb>";
    }

    @GetMapping(value = "/resume/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public String getSTBinXML(@PathVariable int id) throws JAXBException {
        STB stb = stb23.getSTB(id);
        if (stb == null) {
            return "<response>"
                    + "  <id>" + id + "</id>\n"
                    + "  <status>ERROR</status>\n"
                    + "  <message>id not found in database !</message>\n"
                    + "</response>";
        }
        JAXBContext context = JAXBContext.newInstance(STB.class);
        StringWriter sw = new StringWriter();
        context.createMarshaller().marshal(stb, sw);
        return sw.toString();
    }

    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_XML_VALUE)
    public String insert(@RequestBody String xml) {
        STB stb = null;
        int error = -1;
        XmlValidator v = new XmlValidator();
        try {
            if (!v.validate(xml)) {
                return "XSD Validation problem : " + v.getErrorMessage();
            }
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(STB.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            stb = (STB) unmarshaller.unmarshal(new StringReader(xml));
            int max = -1;
            for (STB stbf : stb23.getSTB())
                max = max < stbf.getId() ? stbf.getId() : max;
            stb.setId(max + 1);
            List<STB> stbs = stb23.getSTB();

            for (int i = 0; i < stbs.size(); i++) {
                int id = stbs.size() + 1;
                for (STB stbf2 : stb23.getSTB()) {

                    if (stbf2.getTitle().equals(stb.getTitle())) {
                        error = -2;
                        break;
                    }
                    if (stbf2.getVersion().equals(stb.getVersion())) {
                        error = -2;
                        break;
                    }
                    if (stbf2.getDateValidation().equals(stb.getDateValidation())) {
                        error = -2;
                        break;
                    }
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        if (stb != null) {
            int s = stb23.saveSTB(stb);
            if (s == 0) {
                return "<response>\n"
                        + "  <id>" + stb.getId() + "</id>\n"
                        + "  <status>INSERTED</status>\n"
                        + "</response>";
            }
        }
        String s = "<response>\n  " + "<status>ERROR! Xml file already in our database.</status>\n<detail>";

        if (error == -2){
            s += "DUPLICATED";
        }else{
            s += "INVALID";
        }
        s += "</detail>\n</response>";
        return s;
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public String deleteSTB(@PathVariable int id) {
        if (stb23.deleteSTB(id) != 0) {
            return "<response>\n"
                    + "  <status>ERROR</status>\n"
                    + "  <message>Can't find this item in our database.</message>\n"
                    + "</response>";
        }
        return "<response>\n"
                + "  <id>" + id + "</id>\n"
                + "  <status>DELETED</status>\n"
                + "</response>";
    }
}
