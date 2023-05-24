package fr.univrouen.stb23v1.util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XmlValidator {

    private File xsdFile = new File("src/main/resources/stb23.xsd");

    private String errorMessage;

    private boolean hasError;

    public XmlValidator() {
        hasError = false;
    }

    public boolean validate(String xmlData) throws SAXException, IOException {

        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        Schema schema = factory.newSchema(xsdFile);
        Validator validator = schema.newValidator();
        Source source = new StreamSource(new StringReader(xmlData));

        try {
            validator.validate(source);
        } catch (SAXException e) {
            errorMessage = e.getMessage();
            hasError = true;
        }

        return !hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
