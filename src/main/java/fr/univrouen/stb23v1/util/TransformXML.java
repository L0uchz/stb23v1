package fr.univrouen.stb23v1.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TransformXML {

    public void transformXSL(String s, boolean multiple) throws TransformerException {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("src/main/resources/tmp.xml", "UTF-8");
            writer.println(s);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(new StreamSource(
                multiple ? "src/main/resources/stb23.resume.xslt" : "src/main/resources/stb23.single.xslt"));

        transformer.transform(new StreamSource("src/main/resources/tmp.xml"),
                new StreamResult("src/main/resources/templates/stb23.result.html"));

    }

}