package fr.univrouen.stb23v1.model;


import jakarta.xml.bind.annotation.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;


@XmlRootElement
@XmlType(name = "features")
@XmlAccessorType(XmlAccessType.NONE)
public class Feature {

    @Field
    @XmlElement(required = true)
    private String description;

    @Field
    @XmlElement(required = true)
    private int priority;

    @Field
    @XmlElement
    private String delivery;

    @Field
    @XmlElement
    private String comment;

    @Id
    @Field
    @XmlAttribute(required = true)
    private String name;

    @Field
    @XmlAttribute(required = true)
    private int section;

    @Field
    @XmlAttribute(required = true)
    private int number;

    public Feature(String name, String description, int priority){
        super();
        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    public Feature(){
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public String getDelivery() {
        return delivery;
    }

    public String getComment() {
        return comment;
    }

    public String getName() {
        return name;
    }

    public int getSection() {
        return section;
    }

    public int getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        String s = "Nom de la fonctionnalité du projet " + name + "\nDans la section : " + section + "\nNuméro : " +
                number + "\nDescription de la fonctionnalité : " + description + "\nPriorité de niveau : " + priority;

        if(delivery != null){
            s+= "\nDate de livraison : " + delivery;
        }
        s += "\nCommentaires complémentaires : " + comment;

        return s;
    }
}
