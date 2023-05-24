package fr.univrouen.stb23v1.model;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement
@XmlType(name = "client")
@XmlAccessorType(XmlAccessType.NONE)
public class Client {

    @XmlElement(required = true)
    private String entity;

    @XmlElements({@XmlElement (name = "person", required = true)})
    private Person person;

    @XmlElement
    private List<String> mail;

    @XmlElement
    private List<String> tel;

    public Client(String entity, Person person, List<String> mail){
        super();
        this.entity = entity;
        this.person = person;
        this.mail = mail;
    }

    public Client(){
    }

    public String getEntity() {
        return entity;
    }

    public Person getPerson() {
        return person;
    }

    public List<String> getMail() {
        return mail;
    }

    public List<String> getTel() {
        return tel;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    @Override
    public String toString(){
        String s = "Nom de l'entité client : " + entity + "\nNom de l'interlocuteur : " + getPerson().toString();
        if (mail != null){
            for (int i = 0; i < mail.size(); i++) {
                s += "- Adresse mail de contact " + (i+1) + " :" + mail.get(i).toString() + "\n";
            }
        }else{
            for (int i = 0; i < tel.size(); i++) {
                s += "- Téléphone de contact " + (i+1) + " :" + tel.get(i).toString() + "\n";
            }
        }
        return s;
    }
}

