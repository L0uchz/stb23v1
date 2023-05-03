package fr.univrouen.stb23v1.model;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.Date;

@XmlRootElement(name = "stb")
@XmlAccessorType(XmlAccessType.NONE)
public class STB implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlAttribute
    private Integer id;

    @XmlElement
    private String title;

    @XmlElement
    private String published;

    @XmlElement
    private String description;

    @XmlElement
    private String dateValidation;

    @XmlElement
    private String clientName;

    public STB(Integer id, String title, String published,String description, String dateValidation, String clientName){
        super();
        this.id = id;
        this.title = title;
        this.published = published;
        this.description = description;
        this.dateValidation =dateValidation;
        this.clientName = clientName;
    }

    public STB(){
    }

    @Override
    public String toString(){
        return ("STB (" + id.toString() + ") du " + published + "\n" + title + "\n à propos de " + description + "\nvalidé le" + dateValidation + "\n pour " + clientName);
    }
}
