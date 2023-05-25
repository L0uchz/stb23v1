package fr.univrouen.stb23v1.model;

import jakarta.xml.bind.annotation.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

@XmlRootElement(name = "stb")
@XmlType(propOrder = {"title", "version","date","description","client","team","features"})
@XmlAccessorType(XmlAccessType.NONE)
public class STB implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @XmlAttribute
    private Integer id;

    @Field
    @XmlElement(name = "title", required = true)
    private String title;

    @Field
    @XmlElement(name = "version", required = true)
    private float version;

    @Field
    @XmlElement(name = "published", required = true)
    private Date published;

    @Field
    @XmlElement(name = "description", required = true)
    private String description;

    @Field
    @XmlElement(name = "validate", required = true)
    private Date dateValidation;

    @Field
    @XmlElements({ @XmlElement(name = "client", required = true) })
    private Client clientName;

    @Field
    @XmlElements({ @XmlElement(name = "team", required = true) })
    private Team team;

    @Field
    @XmlElements({ @XmlElement(name = "features", required = true) })
    private Features features;

    public STB(Integer id, String title,Float version, Date published,String description, Date dateValidation, Client clientName,
                Team team, Features features){
        super();
        this.id = id;
        this.title = title;
        this.version = version;
        this.published = published;
        this.description = description;
        this.dateValidation = dateValidation;
        this.clientName = clientName;
        this.team = team;
        this.features = features;
    }

    public STB(){
    }

    public int getId(){
        return id;
    }

    public int setId(int id){
        return this.id = id;
    }
    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public String getTitle() {
        return title;
    }

    public Float getVersion(){
        return version;
    }

    public Date getPublished() {
        return published;
    }

    public String getDescription() {
        return description;
    }

    public Client getClientName() {
        return clientName;
    }

    public Date getDateValidation(){
        return dateValidation;
    }

    public Team getTeam() {
        return team;
    }

    public Features getFeatures() {
        return features;
    }


    @Override
    public String toString(){
        return ("STB (" + id.toString() + ") du " + published + "\n" + title + "\nVersion : " + version +"\n à propos de " + description +
                "\nvalidé le" + dateValidation.toString() + "\n pour " + clientName.toString() + "\npar " + team.toString()
                + "\navec les fonctionnalités : " + features.toString());

    }
}
