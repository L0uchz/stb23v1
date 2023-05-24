package fr.univrouen.stb23v1.model;

import jakarta.xml.bind.annotation.*;

@XmlRootElement
@XmlType(name = "person")
@XmlAccessorType(XmlAccessType.NONE)
public class Person {

    @XmlAttribute(required = true)
    private String gender;
    @XmlAttribute
    private String lastname;

    @XmlElement(required = true)
    private String name;


    public Person(String gender, String lastname, String name){
        super();
        this.gender = gender;
        this.lastname = lastname;
        this.name = name;
    }
    public Person(){
    }

    public String getLastname(){
        return lastname;
    }

    public String getGender(){
        return gender;
    }

    public String getName(){
        return name;
    }

    public String setName(String name){
        return this.name = name;
    }

    @Override
    public String toString(){
        String s = "Nom : " + lastname + "\n Prénom " + name;
        if (gender != null){
            s = "Genre :" + gender + "\n" + s;
        }
        return s;
    }
}
