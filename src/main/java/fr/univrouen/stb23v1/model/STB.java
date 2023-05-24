package fr.univrouen.stb23v1.model;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.Date;

@XmlRootElement(name = "stb")
@XmlType(propOrder = {"title", "version","date","description","client","team","features"})
@XmlAccessorType(XmlAccessType.NONE)
public class STB implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlAttribute
    private Integer id;

    @XmlElement(name = "title", required = true)
    private String title;

    @XmlElement(name = "published", required = true)
    private String published;

    @XmlElement(name = "description", required = true)
    private String description;

    @XmlElement(name = "validate", required = true)
    private String dateValidation;

    @XmlElements({ @XmlElement(name = "client", required = true) })
    private String clientName;

    @XmlElements({ @XmlElement(name = "team", required = true) })
    private String team;

    @XmlElements({ @XmlElement(name = "features", required = true) })
    private String features;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return title;
    }

    public String getPublished() {
        return published;
    }

    public String getDescription() {
        return description;
    }

    public String getClientName() {
        return clientName;
    }

    public String getDateValidation(){
        return dateValidation;
    }

    public String getTeam() {
        return team;
    }

    public String getFeatures() {
        return features;
    }


    @Override
    public String toString(){
        return ("STB (" + id.toString() + ") du " + published + "\n" + title + "\n à propos de " + description +
                "\nvalidé le" + dateValidation + "\n pour " + clientName + "\npar " + team + "\navec les fonctionnalités : " + features);

    }
}
